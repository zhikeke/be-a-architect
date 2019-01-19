package com.ke.zkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

public class MasterChooseTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i ++) {
            ZkClient zkClient = new ZkClient("localhost:2181",
                    5000,
                    5000,new SerializableSerializer());

            ClientCenter client = new ClientCenter();
            client.setClientId(Long.valueOf(i));
            client.setClientName("client_" + i);

            MasterElection masterElection = new MasterElection(client, zkClient);
            masterElection.start();
        }
    }
}
