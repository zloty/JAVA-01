package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class NIOServer {
  public static void main(String[] args) {
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      Selector selector = Selector.open();
      serverSocketChannel.socket().bind(new InetSocketAddress(6666));
      serverSocketChannel.configureBlocking(false);
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      while (true) {
        if (selector.select(1000) == 0) {
//          System.out.println("服务器等待了1秒");
          continue;
        }

        Set<SelectionKey> selectionKeys = selector.selectedKeys();
//        System.out.println(selectionKeys);
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while(iterator.hasNext()) {
          SelectionKey key = iterator.next();
          if (key.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
          }
          if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer)key.attachment();
            socketChannel.read(buffer);
//            System.out.println(new String(buffer.array()));
          }
//          if () {
//
//          }

          iterator.remove();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
