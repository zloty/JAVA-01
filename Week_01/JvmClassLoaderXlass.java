package jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JvmClassLoaderXlass extends ClassLoader  {
  private File file =
          new File(File.separator + "Users"+File.separator+"zyc"+File.separator+"Desktop"+File.separator+"geek" +
          File.separator+"Hello"+File.separator+"Hello.xlass");
  public static void main(String[] args) {
    try {
      Class clazz = new JvmClassLoaderXlass().findClass("Hello");
      Method method = clazz.getMethod("hello");
      method.invoke(clazz.newInstance());
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) {
    try {
      FileInputStream in = new FileInputStream(file);
      byte[] bytes = new byte[(int) file.length()];
      in.read(bytes);
      for (int i = 0;i < bytes.length ;i ++){
        bytes[i] = (byte)(255 - (int) bytes[i]);
      }
      return defineClass(name,bytes,0,bytes.length);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}

