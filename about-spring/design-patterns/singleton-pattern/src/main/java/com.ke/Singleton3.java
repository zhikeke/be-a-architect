package com.ke;

public class Singleton3 {
	private static Singleton3 single = null;

	public static Singleton3 getInstance() {  
	  if (single == null) {
	      synchronized (Singleton3.class) {
	          if (single == null) {    
	              single = new Singleton3();
	          }    
	      }    
	  }    
	   return single;   
	}
}
