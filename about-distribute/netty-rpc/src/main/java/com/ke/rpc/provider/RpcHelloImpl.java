package com.ke.rpc.provider;

import com.ke.rpc.api.IRpcHello;

public class RpcHelloImpl implements IRpcHello {

    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}
