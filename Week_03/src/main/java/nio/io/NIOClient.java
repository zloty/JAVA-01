package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
  public static void main(String[] args) {
    try {
      SocketChannel socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
      InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);
      if (!socketChannel.connect(inetSocketAddress)) {
        while (!socketChannel.finishConnect()) {
          System.out.println("蓝上了");
        }
      }
      String str = "hello,server";
      ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
      socketChannel.write(buffer);
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
