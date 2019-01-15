package zookeeper_native;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


/**
 * @author  keke
 * @create  2019/1/15 0015 下午 8:36
 * @desc 获取zookeeper连接
 **/
public class ZookeeperClient {
    private static ZooKeeper zooKeeper = null;

    public static ZooKeeper getInstance(){
        if (zooKeeper == null) {
            synchronized (ZookeeperClient.class) {
                if (zooKeeper == null) {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);

                    try {
                        zooKeeper = new ZooKeeper("localhost:2181", 5000, new Watcher() {
                            public void process(WatchedEvent watchedEvent) {
                                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                                    countDownLatch.countDown();
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("创建连接成功!!!");
                }
            }
        }
        return zooKeeper;
    }


}
