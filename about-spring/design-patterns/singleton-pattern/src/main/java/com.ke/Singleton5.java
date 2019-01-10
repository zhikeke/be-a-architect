package com.ke;

public class Singleton5 implements java.io.Serializable {
	public static Singleton5 INSTANCE = new Singleton5();

	protected Singleton5() {

	}
	private Object getInstance() {
		return INSTANCE;   
	}
}
