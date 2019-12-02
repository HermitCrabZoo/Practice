package com.zoo.nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Slf4j
public class BIOTest {

    private static Logger log = LoggerFactory.getLogger(BIOTest.class);

    private static final int NCPU = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

    }


    @Test
    public void client() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(NCPU,
                NCPU * 3,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(NCPU * 20),
                new ThreadPoolExecutor.CallerRunsPolicy());
        log.info("启动客户端");
        for(int i = 0;i<10;i++){
            int num = i;
            executor.execute(()->{
                try(Socket socket = new Socket("127.0.0.1", 6666);
                    OutputStream os = socket.getOutputStream()) {
                    String msg = String.format("Client【%d】-%s\n", num, Thread.currentThread().getName());
                    log.info(msg);
                    os.write(msg.getBytes());
                } catch (IOException e) {
                    log.error("BIO客户端连接服务端异常：", e);
                }
            });
        }
        executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }


    @Test
    public void server() throws IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(NCPU,
                NCPU * 3,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(NCPU * 20),
                new ThreadPoolExecutor.CallerRunsPolicy());
        ServerSocket serverSocket = new ServerSocket(6666);
        log.info("BIO服务器已启动");
        while (true) {
            //监听，等待客户端连接，有一个客户端连接过来则该方法会返回一个socket对象，此处会阻塞
            Socket socket = serverSocket.accept();
            log.info("BIO连接到一个客户端：{}", socket.getInetAddress().getHostAddress());
            executor.execute(()->{
                try(InputStream is = socket.getInputStream()) {
                    //is.read会阻塞
                    is.transferTo(System.out);
                } catch (IOException e) {
                    log.error("BIO服务端获取输入流异常：", e);
                }
            });
        }
    }
}
