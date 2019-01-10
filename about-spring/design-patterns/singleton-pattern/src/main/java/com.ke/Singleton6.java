package com.ke;

import java.util.HashMap;
import java.util.Map;

public class Singleton6 {  
    private static Map<String,Singleton6> map = new HashMap<String,Singleton6>();  
    static {
        Singleton6 single = new Singleton6();
        map.put(single.getClass().getName(), single);
    }

    public static Singleton6 getInstance(String name) {  
        if(name == null) {  
             name = Singleton6.class.getName();  
        }  
        if(map.get(name) == null) {  
           try {
               map.put(name, (Singleton6) Class.forName(name).newInstance());
           } catch (InstantiationException e) {
               e.printStackTrace();
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
        }
        return map.get(name);
    }
}
