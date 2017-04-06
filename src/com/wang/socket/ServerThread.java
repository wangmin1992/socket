package com.wang.socket;

import java.net.Socket;

/**
 * 服务端需要用到多线程，所以单独写一个线程类。
 * @author wangmin
 * @since 2017年4月6日-下午4:03:27
 * @version V1.0
 */
public class ServerThread implements Runnable{

	private Socket client = null;
	
	@Override
	public void run() {
		
	}

}
