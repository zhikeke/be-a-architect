package com.ke;

/**
 * 懒汉模式(静态内部类)
 */
public class Singleton4 {
	// 必须创建私有化构造函数，如果不写，相当于有一个默认的public的无参构造方法，意味着在代码中可以随时new出来
	private Singleton4() {};

	// 1. 先声明一个静态内部类
	// 2. private 私有化保证不被他人修改
	// 3. static 保证全局唯一
	private static class LazyHolder {
		// final 防止内部误操作
		private static final Singleton4 INSTANCE = new Singleton4();
	}

	// 提供静态方法获取实例， final 化防止被他人修改
	public static final Singleton4 getInstance() {
		return LazyHolder.INSTANCE;
    }

}

