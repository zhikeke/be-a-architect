package com.ke.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * curator master 选举
 */
public class MasterElection {
    private static final String CURATOR_PATH = "/curator_master_path";

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("localhost:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, CURATOR_PATH, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                // 成功获取curator_path
                System.out.println("获得 leader 成功");
                TimeUnit.SECONDS.sleep(2);
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        });

        leaderSelector.autoRequeue();
        // 开始选举
        leaderSelector.start();
    }

}
