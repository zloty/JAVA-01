package io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer04 {
  public static void main(String[] args) {
    try {
      //使用ServerSocketChannel和SocketChannel 网络连接
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      InetSocketAddress inetSocketAddress = new InetSocketAddress(8804);

      //绑定端口到socket，并启动
      serverSocketChannel.socket().bind(inetSocketAddress);

      //创建Buffer数组
      ByteBuffer[] byteBuffers = new ByteBuffer[2];
      //第一个Buffer分配5个字节空间
      byteBuffers[0] = ByteBuffer.allocate(5);
      //第二个Buffer分配3个字节空间
      byteBuffers[1] = ByteBuffer.allocate(3);

      //等待客户端连接
      SocketChannel socketChannel = serverSocketChannel.accept();
      int messageLength = 8;//假定从客户端读取8个字节
      //循环读取
      while(true) {
        long byteRead = 0;
        while(byteRead < messageLength) {
          Long l = socketChannel.read(byteBuffers);
          System.out.println(l);
          byteRead += l;
          System.out.println("byteRead = " + byteRead);
          Arrays.asList(byteBuffers).stream().map(buffer -> "position = " + buffer.position() + "、limit = " + buffer.limit()).forEach(System.out::println);
        }
        //将buffer进行反转
        Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

        //将数据读出显示到客户端
        long byteWrite = 0;
        while(byteWrite < messageLength) {
          long l = socketChannel.write(byteBuffers);
          byteWrite += l;
        }
        //清除Buffer
        Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());
        System.out.println("byteRead = " + byteRead + "、byteWrite = " + byteWrite + "、messageLength = " + messageLength);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private static void service(Socket socket) {
    try(PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true)){
      printWriter.println("HTTP/1.1 200 OK");
      printWriter.println("Content-Type:text/html;charset=utf-8");
      String str = "hello,nio";
      printWriter.println("Content-length:" + str.getBytes().length);
      printWriter.println();
      printWriter.println(str);
      socket.close();
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
