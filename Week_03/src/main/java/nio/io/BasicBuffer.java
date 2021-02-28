package io;

import java.nio.IntBuffer;

public class BasicBuffer {
  public static void main(String[] args) {
    IntBuffer intBuffer = IntBuffer.allocate(5);
    for (int x = 0;x < intBuffer.capacity() ;x ++) {
      intBuffer.put(x);
    }

    IntBuffer readonlyByffer = intBuffer.asReadOnlyBuffer();
    for (int x = 0;x < intBuffer.capacity();x ++) {
      System.out.print(readonlyByffer.get(x) + " ");
    }

    intBuffer.flip();
    while(intBuffer.hasRemaining()){
      System.out.println(intBuffer.get());
    }

    System.out.println();

    intBuffer.clear();

    for(int x=0;x < intBuffer.capacity();x++) {
      intBuffer.put(x * 2);
    }

    for (int x = 0;x < intBuffer.capacity();x ++) {
      System.out.print(readonlyByffer.get(x) + " ");
    }

  }
}
