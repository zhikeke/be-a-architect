package zookeeper_native;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author  keke
 * @create  2019/1/15 0015 下午 8:46
 * @desc
 **/
public class DistributeLock {
    // 根节点
    private static final String ROOT_LOCKS_PAHT = "/LOCKS";

    private ZooKeeper zooKeeper;

    //节点的数据
    private final static byte[] data = "keke".getBytes();

    // 记录锁节点id
    private String lockID;

    // 会话超时时间
    private int sessionTimeout;

    private CountDownLatch countDownLatch=new CountDownLatch(1);

    public DistributeLock() {
        this.zooKeeper = ZookeeperClient.getInstance();
        this.sessionTimeout = 5000;
    }

    //获取锁的方法
    public boolean lock(){
        try {
            // 创建一个临时有序节点
            lockID = zooKeeper.create(
                    ROOT_LOCKS_PAHT + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);

            System.out.println(Thread.currentThread().getName() + "->成功创建了节点["+lockID+"], 开始去竞争锁");

            // 获取根节点下所有的子节点
            List<String> childNodes = zooKeeper.getChildren(ROOT_LOCKS_PAHT, true);

            // 将子节点从小到大排序
            SortedSet<String> childNodeSort = new TreeSet<String>();
            for (String childNode : childNodes) {
                childNodeSort.add(ROOT_LOCKS_PAHT + "/" + childNode);
            }

            // 获取当前最小节点
            String firstNode = childNodeSort.first();
            // 若当前建立的节点等于最小节点,那么它将获得锁
            if (lockID.equals(firstNode)) {
                System.out.println(Thread.currentThread().getName() + "->成功获得锁，lock节点为:["+lockID+"]");
                return true;
            }

            // 若当前建立的节点不为最小节点，获取比他小的节点
            SortedSet<String> lessThanLockId = childNodeSort.headSet(lockID);

            if (!lessThanLockId.isEmpty()) {
                // 拿到刚好小于当前建立节点的节点
                String prevLockID = lessThanLockId.last();
                zooKeeper.exists(prevLockID, new LockWatcher(countDownLatch));
                countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);

                // 如果会话超时或者节点被删除（释放）了打印
                System.out.println(Thread.currentThread().getName() + " 成功获取锁：["+lockID+"]");
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 释放锁
    public boolean unlock(){
        System.out.println(Thread.currentThread().getName() + "->开始释放锁:["+lockID+"]");
        try {
            zooKeeper.delete(lockID,-1);
            System.out.println("节点["+lockID+"] 成功被删除");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        Random random=new Random();

        for (int i = 0; i < 10; i ++) {
            new Thread(() -> {
                DistributeLock distributeLock = null;
                try {
                    distributeLock = new DistributeLock();
                    latch.countDown();
                    latch.await();
                    Thread.sleep(1000);
                    distributeLock.lock();
                    Thread.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (distributeLock != null) {
                        distributeLock.unlock();
                    }
                }

            }).start();
        }
    }

}
