package com.zoo.main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	public static void main(String[] args) {
		System.out.println("服务端启动！");
		ServerSocket ss = null;
		Socket s = null;

		//InputStreamReader isr = null;
		//BufferedReader br = null;

		BufferedInputStream bis = null;
		PrintWriter pw = null;

		try {
			ss = new ServerSocket(8888);
			s = ss.accept();

			bis = new BufferedInputStream(s.getInputStream());
			//isr = new InputStreamReader(bis);
			//br = new BufferedReader(isr);

			int x = bis.read();
			while(x!= -1) {
				System.out.print((char)x);
				x = (int)(bis.read());
			}

			System.out.println("服务端收到数据并准备发送回去！");
			pw = new PrintWriter(s.getOutputStream());
			pw.print("HelloClient");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{

			if(pw != null) {
				pw.close();
			}
			if(bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if( s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
