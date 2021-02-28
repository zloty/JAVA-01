package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class NioFileChannel {
  public static void main(String[] args) {

//    File file1 = new File(File.separator+"Users"+File.separator+"zyc"+File.separator+"geek"+File.separator+"JAVA-01"+File.separator+"Week_03"+File.separator+"a.txt");
//    File file2 = new File(File.separator+"Users"+File.separator+"zyc"+File.separator+"geek"+File.separator+"JAVA-01"+File.separator+"Week_03"+File.separator+"b.txt");

    try (FileInputStream inputStream =
           new FileInputStream(File.separator+"Users"+File.separator+"zyc"+File.separator+"geek"+File.separator+"JAVA-01"+File.separator+"Week_03"+File.separator+"a.txt");
         FileOutputStream outputStream =
           new FileOutputStream(File.separator+"Users"+File.separator+"zyc"+File.separator+"geek"+File.separator+"JAVA-01"+File.separator+"Week_03"+File.separator+"b.txt")) {
      CopyPic.copy(inputStream, outputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
