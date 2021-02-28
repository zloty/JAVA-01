package io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer02 {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8802);
      while(true){
        Socket socket = serverSocket.accept();
        new Thread(()->{
          service(socket);
        }).start();
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
