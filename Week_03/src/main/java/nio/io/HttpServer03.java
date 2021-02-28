package io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer03 {
  public static void main(String[] args) {
    try {
      ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
      ServerSocket serverSocket = new ServerSocket(8803);
      while(true){
        Socket socket = serverSocket.accept();
        executors.execute(()->service(socket));
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
