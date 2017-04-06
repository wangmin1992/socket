package com.wang.socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP Socket服务端。
 * @author wangmin
 * @since 2017年4月6日-下午4:02:44
 * @version V1.0
 */
public class Server {

	public static void main(String[] args) {
		try 
		{
			//服务端在15000端口监听客户端TCP的连接。
			ServerSocket serverSocket = new ServerSocket(15000);
			Socket client = null;
			boolean flag = true;
			while (flag)
			{
				//等待客户端的连接 
				client = serverSocket.accept();
				System.out.println("与客户端连接成功");
				new Thread(new ServerThread(client)).start();;
			}
			serverSocket.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
