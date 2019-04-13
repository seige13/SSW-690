package com.hobbymatcher.util;

import java.io.*;

public class SerializableUtil {
    //对象转byte数组
    public static byte[] serializableUtil(Object obj) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bais = new ByteArrayOutputStream();
            ObjectOutputStream ois = new ObjectOutputStream(bais);
            ois.writeObject(obj);
            bytes = bais.toByteArray();
            ois.close();
            bais.close();
            return bytes;
        } catch (IOException e) {
            System.out.print("序列化失败");

        }
        return bytes;
    }

    //byte数组转对象
    public static Object unSerialzableUtil(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Object) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
