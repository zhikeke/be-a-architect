package com.ke.rpc.consumer;

import com.ke.rpc.api.IRpcHello;
import com.ke.rpc.consumer.proxy.RpcProxy;

public class RpcConsumer {

    public static void main(String[] args) {
        IRpcHello rpcHello = RpcProxy.create(IRpcHello.class);
        System.out.println(rpcHello.sayHello("科科老师"));
    }
}
