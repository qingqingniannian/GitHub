import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	/**
	 * 14 * Socket服务端 15
	 */
	/*
	 * Socket通信步骤：（简单分为4步）
			1.建立服务端ServerSocket和客户端Socket
			2.打开连接到Socket的输出输入流
			3.按照协议进行读写操作
			4.关闭相对应的资源
	 * 
	 */
	/*服务端Server.java
		1.创建ServerSocket对象，绑定并监听端口
		2.通过accept监听客户端的请求
		3.建立连接后，通过输出输入流进行读写操作
		4.关闭相关资源
	 * */
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("服务端已启动，等待客户端连接..");
			Socket socket = serverSocket.accept();// 侦听并接受到此套接字的连接,返回一个Socket对象

			// 根据输入输出流和客户端连接
			InputStream inputStream = socket.getInputStream();// 得到一个输入流，接收客户端传递的信息
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);// 提高效率，将自己字节流转为字符流
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);// 加入缓冲区
			String temp = null;
			String info = "";
			while ((temp = bufferedReader.readLine()) != null) {
				info += temp;
				System.out.println("已接收到客户端连接");
				System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为：" + socket.getInetAddress().getHostAddress());
			}

			OutputStream outputStream = socket.getOutputStream();// 获取一个输出流，向服务端发送信息
			PrintWriter printWriter = new PrintWriter(outputStream);// 将输出流包装成打印流
			printWriter.print("你好，服务端已接收到您的信息");
			printWriter.flush();
			socket.shutdownOutput();// 关闭输出流

			// 关闭相对应的资源
			printWriter.close();
			outputStream.close();
			bufferedReader.close();
			inputStream.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
