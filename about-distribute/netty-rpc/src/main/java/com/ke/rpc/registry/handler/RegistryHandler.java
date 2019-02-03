package com.ke.rpc.registry.handler;

import com.ke.rpc.core.InvokeEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务注册中心业务处理
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter{
    // 服务注册中心服务管理容器
    private ConcurrentHashMap<String, Set<Object>> registryMap = new ConcurrentHashMap<String, Set<Object>>();

    private List<String> classNameCache = new ArrayList<String>();



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Object res = new Object();

        // 客户端传过来的调用信息
        InvokeEntity entity = (InvokeEntity) msg;

        if (registryMap.containsKey(entity.getClassName())) {
            Set<Object> providerList = registryMap.get(entity.getClassName());

            Random random = new Random();

            int start = 0;
            int end = random.nextInt(providerList.size());

            Iterator<Object> it = providerList.iterator();

            Object  clazz = null;

            while (it.hasNext()) {
                if (start == end) {
                    clazz = it.next();
                    break;
                } else {
                    it.next();
                }
            }

            if (clazz == null) {
                throw new RuntimeException("cannot find class instance");
            }

            Method m = clazz.getClass().getMethod(entity.getMethodName(), entity.getParams());
            res = m.invoke(clazz, entity.getValues());
        }

        ctx.writeAndFlush(res);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // 约定: com.ke.rpc.provider 包下的所有类都为服务提供者
    public RegistryHandler() {
        scannerClass("com.ke.rpc.provider");
        doRegistry();
    }

    /**
     * 扫描指定包下所有的类
     * @param packageName 包名
     */
    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            } else {
                classNameCache.add((packageName + "." + file.getName()).replace(".class", "").trim());
            }
        }
    }

    /**
     * 将扫描到的类实例化，放入map中
     * 服务名称为接口名称
     * 约定优于配置
     */
    private void doRegistry() {
        if (classNameCache.isEmpty()) {
            return;
        }

        for (String className : classNameCache) {
            try {
                Class<?> clazz = Class.forName(className);
                String providerName = clazz.getInterfaces()[0].getName();

                if (null == providerName || "".equals(providerName)) {
                    throw new RuntimeException("not a provider");
                }

                Set<Object> providerList  = registryMap.get(providerName);

                if (providerList == null) {
                    providerList = new HashSet<Object>();
                }

                providerList.add(clazz.newInstance());
                registryMap.put(providerName, providerList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
