package com.wang.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * TCP Socket客户端。
 * @author wangmin
 * @since 2017年4月6日-下午4:01:53
 * @version V1.0
 */
public class Client {

	public static void main(String[] args) {
		try 
		{
			//客户端请求与本机的15000端口建立TCP连接
			Socket client = new Socket("127.0.0.1", 15000);
			client.setSoTimeout(10000);
			//获取键盘的输入
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			//Socket输出流，用于发送数据到服务端
			PrintStream out = new PrintStream(client.getOutputStream());
			//Socket输入流，用于接收服务器发送过来的数据
			BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
			boolean flag = true;
			while (flag)
			{
				System.out.println("输入信息：");
				String str = input.readLine();
				//发送数据到服务端
				out.println(str);
				if ("bye".equals(str))
				{
					flag = false;
				}
				else
				{
					try 
					{
						//从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
						String echo = buf.readLine();
						System.out.println(echo);
					} 
					catch (SocketTimeoutException e) 
					{
						e.printStackTrace();
					}
				}
			}
			input.close();
			if (client != null)
			{
				/*如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭  
				   只关闭socket，其关联的输入输出流也会被关闭 */
	            client.close(); 
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
