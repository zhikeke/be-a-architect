package com.ke;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;


public class ZookeeperServer {
    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();


    public static void main(String[] args) {

        try {
            // 连接zookeeper
             zooKeeper = new ZooKeeper(
                     "localhost:2181", 5000, new Watcher() {
                 public void process(WatchedEvent watchedEvent) {
                     System.out.println("-----------------------------------");
                     System.out.println("watchedEvent type --> " + watchedEvent.getType());
                     System.out.println("watchedEvent path --> " + watchedEvent.getPath());


                     System.out.println("state-->" + watchedEvent.getState() +
                             " ;type-->" + watchedEvent.getType() +
                             " ;path-->" + watchedEvent.getPath());
                 }
             });

            // 创建节点 /ke ; value：keke ; CreateMode.EPHEMERAL 创建的节点为临时节点（session）
            zooKeeper.create("/ke", "keke".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("创建节点 /ke 成功");

            String res = new String(zooKeeper.getData("/ke", true, stat));
            System.out.println("获取节点 /ke 数据成功, 结果为: " + res);

            Stat stat = zooKeeper.exists("/ke", true);
            System.out.println("判断节点 /ke 是否存在: " + stat.toString());

            zooKeeper.delete("/ke", -1);
            System.out.println("删除节点 /ke 数据成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
