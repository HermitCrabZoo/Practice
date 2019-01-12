package com.zoo.main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
	public static void main(String[] args) {
		System.out.println("客户端启动");
		Socket s = null;
		
		BufferedInputStream bis = null;
		PrintWriter pw = null;
		
		try {
			s = new Socket("localhost", 8888);
			pw = new PrintWriter(s.getOutputStream());
			pw.print("Hello Server");
			pw.flush();
			bis = new BufferedInputStream(s.getInputStream());
			System.out.println("已接收到服务端发来的数据！");
			int x = bis.read();
			while(x != -1){
				System.out.print((char)x);
				x = bis.read();
			}
			
			System.out.println("客户端完");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
			if(bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(pw != null) {
				pw.close();
			}
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
