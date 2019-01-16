package com.ke.zkClient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import sun.awt.geom.AreaOp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * master 选举
 */
public class MasterElection {

    private ZkClient zkClient;

    // master 选举需要争夺的节点
    private static final String MASTER_ELECTION_PATH = "/master";

    // 注册节点内容变化
    private IZkDataListener dataListener;

    // 客户端
    private ClientCenter client;

    // master
    private ClientCenter masterClient;

    // 当前client 是否处于运行状态
    private Boolean isRunning = false;

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public MasterElection(ClientCenter client) {
        this.client = client;

        this.dataListener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                // 如果节点被删除， 发起选主操作
                chooseMaster();
            }
        };
    }

    // 开始选举
    public void start() {
       if (!isRunning) {
           isRunning = true;
           // 注册节点事件
           zkClient.subscribeDataChanges(MASTER_ELECTION_PATH, dataListener);
           chooseMaster();
       }
    }

    // 停止
    public void stop() {
        if (isRunning) {
            isRunning = false;
            scheduledExecutorService.shutdown();
            zkClient.unsubscribeDataChanges(MASTER_ELECTION_PATH, dataListener);
            releaseMaster();
        }
    }

    // master 选举
    private void chooseMaster() {
        if (!isRunning) {
            System.out.println("当前服务尚未启动，无法参与选举");
            return;
        }

        try {
            // 尝试创建master 争取的 path
            zkClient.createEphemeral(MASTER_ELECTION_PATH);
            // 创建成功, 当前client 即为 master
            masterClient = client;

            System.out.println("master info:" + masterClient.toString());

            // 创建定时任务，模拟master 出现故障
            scheduledExecutorService.schedule(()->{
                releaseMaster();
            }, 5, TimeUnit.SECONDS);
        } catch (ZkNodeExistsException e) { // master 已经存在
            ClientCenter clientCenter = zkClient.readData(MASTER_ELECTION_PATH, true);
            if (clientCenter == null) {
                chooseMaster();
            } else {
                masterClient = clientCenter;
            }
        }
    }

    // 释放master
    private void releaseMaster() {
      if (isMaster()) {
          zkClient.delete(MASTER_ELECTION_PATH, -1);
      }
    }

    // 当前client 是否为master
    private boolean isMaster() {
        ClientCenter clientCenter = zkClient.readData(MASTER_ELECTION_PATH, true);

        // 再次判断当前节点是否为master
        if (clientCenter != null && clientCenter.getClientId().equals(client.getClientId()) &&
                clientCenter.getClientName().equals(client.getClientName())) {
            return true;
        }

        return false;
    }
}
