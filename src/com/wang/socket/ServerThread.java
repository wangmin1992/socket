package com.wang.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 服务端需要用到多线程，所以单独写一个线程类。
 * @author wangmin
 * @since 2017年4月6日-下午4:03:27
 * @version V1.0
 */
public class ServerThread implements Runnable{

	private Socket client = null;
	
	/**
	 * 构造函数。
	 * @param client
	 */
	public ServerThread(Socket client)
	{
		this.client = client;
	}
	
	
	@Override
	public void run() 
	{
		try 
		{
			//获得Socket输出流，用于向客户端发送数据。
			PrintStream out = new PrintStream(client.getOutputStream());
			//获得Socket输入流，用于接收客户端发送过来的数据。
			BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
			boolean flag = true;
			while(flag)
			{
				//接收从客户端发送过来的数据。
				String str = buf.readLine();
				if (str == null && "".equals(str))
				{
					flag = false;
				}
				else
				{
					if ("bye".equals(str))
					{
						flag = false;
					}
					else
					{
						//将接收到的数据拼接上“服务端接收的数据为：”，然后再发送给客户端
						out.println("服务端接收的数据为：" + str);
					}
				}
			}
			out.close();
			client.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
