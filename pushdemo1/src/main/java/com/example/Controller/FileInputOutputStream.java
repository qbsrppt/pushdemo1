package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试FileInputStream和FileOutputStream的使用
 *
 * 结论：
 * 1. 对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
 * 2. 对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 *
 *
 *
 * @author shkstart
 * @create 2019 下午 2:13
 */
@Controller
@RequestMapping("/file2")
public class FileInputOutputStream {

    //使用字节流FileInputStream将文本文件的数据打印到控制台时，可能出现乱码。
    /*utf-8编码下，一个中文占3个字节。gbk编码下，一个中文占2个字节。在将中文写入字节数组中时，有可能数组刚好只有2个位置，但一个中文占三个字节，因此就会将中文的前两个字节写入数组中，
    读取该数组时，就会出现乱码。
    但使用该流进行文件的复制/上传与下载时，则不会有问题，因为操作结束后，所有数据都是完整展示的。
    */


    public void testFileInputStreamError() {
        FileInputStream fis = null;
        try {
            //1. 造文件
            File file = new File("hello.txt");

            //2.造流
            fis = new FileInputStream(file);

            //3.读数据
            byte[] buffer = new byte[5];
            int len;//记录每次读取的字节的个数
            while((len = fis.read(buffer)) != -1){

                String str = new String(buffer,0,len);
                System.out.print(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                //4.关闭资源
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

//---FileInputStream------------------------------------------------------------------------------------

public void testFileInputStream()  {
    FileInputStream fis = null;
    try {
        //创建file对象
        File srcFile = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/爱情与友情.jpg");

        //创建流
        fis = new FileInputStream(srcFile);

        //创建字节数组
        byte[] buffer = new byte[1024];
        //读取文件内容·
        int len;
        while((len = fis.read(buffer)) != -1){//将文件读取到字节数组buffer中
            System.out.println("正在读取...");
        }

    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if(fis!=null){
                fis.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//---FileOutputStream------------------------------------------------------------------------------------
public void testFileOutputStream()  {

    FileOutputStream fos = null;
    try {
        //创建file对象
        File srcFile = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/222.txt");
        //创建流
        fos = new FileOutputStream(srcFile);
       //输出数据
       fos.write(97);
        //关闭流
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
}



    /*
    实现对图片的复制操作
     */
//http://localhost:8080/file2/test
    @RequestMapping("/test")
    public void FileReader(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/爱情与友情.jpg");
            File destFile = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/爱情与友情2.jpg");

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //复制的过程
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);//将buffer数组中的len个字节数据，写出到文件中
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                //
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    //指定路径下文件的复制
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //复制的过程
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                //
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }


    public void testCopyFile(){

        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\Administrator\\Desktop\\01-视频.avi";
        String destPath = "C:\\Users\\Administrator\\Desktop\\02-视频.avi";


//        String srcPath = "hello.txt";
//        String destPath = "hello3.txt";

        copyFile(srcPath,destPath);


        long end = System.currentTimeMillis();

        System.out.println("复制操作花费的时间为：" + (end - start));//618

    }

}
