package io;

import sun.net.www.content.image.png;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {
  public static void main(String[] args) {
    try (RandomAccessFile randomAccessFile =
           new RandomAccessFile(File.separator + "Users"+File.separator+"zyc"+File.separator+"Desktop"+ File.separator+"1. txt","rw")) {
//      FileChannel channel = randomAccessFile.getChannel();
//      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE,0,10);
//      System.out.println(buffer.getClass());
//      buffer.put(0,(byte) 'A');
//      buffer.put(2,(byte) 'B');
//      buffer.put(3,(byte) 'C');
      //创建通道
      FileChannel fileChannel = randomAccessFile.getChannel();
      /**
       * 参数1：FileChannel.MapMode.READ_WRITE 使用的读写模式
       * 参数2：0：可以直接修改的其实位置
       * 参数3：5：是映射到内存的大小（不是索引位置），将1.txt的多少个字节映射到内存
       * 可以直接修改的范围是0-5
       * 如果此时修改第5个索引位置的字节 mappedByteBuffer.put(5, (byte) 'P'); 则会报越界异常
       * 所以参数3的意思是最多修改5个字节
       * mappedByteBuffer的实际类型是java.nio.DirectByteBuffer
       */
      MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
      System.out.println(mappedByteBuffer.getClass());
      mappedByteBuffer.put(0, (byte) 'H');
      mappedByteBuffer.put(2, (byte) 's');
      mappedByteBuffer.put(4, (byte) 'P');
      //关闭
      randomAccessFile.close();
      System.out.println("修改成功～～");
    } catch (IOException e) {
;      e.printStackTrace();
    }
  }
}
