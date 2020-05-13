package com.ke;

import com.ke.serivce.IDogService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 模拟SPI功能
 */
public class Test {

//    public static void main(String[] args) {
//        ServiceLoader<IDogService> dogServices = ServiceLoader.load(IDogService.class);
//        Iterator<IDogService> dogServiceIterator = dogServices.iterator();
//
//        while (dogServiceIterator.hasNext()) {
//            IDogService dogService = dogServiceIterator.next();
//            dogService.sleep();
//        }
//    }


    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keke", "keke");
    }
}
