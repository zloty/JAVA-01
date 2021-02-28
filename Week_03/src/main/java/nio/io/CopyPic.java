package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyPic {
  public static void main(String[] args) {
    try(FileInputStream inputStream = new FileInputStream(File.separator + "Users"+File.separator+"zyc"+File.separator+
      "Desktop"+ File.separator+"1.png");
        FileOutputStream outputStream = new FileOutputStream(File.separator + "Users"+File.separator+"zyc"+File.separator+
          "Desktop"+ File.separator+"2.png"))
    {
      copy(inputStream, outputStream);
    }catch (IOException e){
      e.printStackTrace();
    }

  }

  static void copy(FileInputStream inputStream, FileOutputStream outputStream) throws IOException {
    FileChannel fromChannel = inputStream.getChannel();
    FileChannel toChannel = outputStream.getChannel();
    toChannel.transferFrom(fromChannel,0,fromChannel.size());
//    ByteBuffer buffer = ByteBuffer.allocate(10);
//    while (true) {
//      buffer.clear();
//      int read = fromChannel.read(buffer);
//      if (read == -1) break;
//      buffer.flip();
//      toChannel.write(buffer);
//    }
  }
}
