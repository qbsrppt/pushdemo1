package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）                               缓冲流（处理流的一种）
 * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
 * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
 * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
 *
 *
 *
 * @author shkstart
 * @create 2019 上午 10:40
 */


@Controller
@RequestMapping("/file1")
public class FileReaderWriterIOStream {

//-----Read-----------------------------------------------------------------------------

    /*
   将day26-iostream8下的hello.txt文件内容读入程序中，并输出到控制台

   说明点：
   1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
   2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
   3. 读入的文件一定要存在，否则就会报FileNotFoundException。

    */
    //http://localhost:8080/file1/test
    @RequestMapping("/test")
    public void FileReader ()  {
        //File file1 = new File("demo1/src/main/resources/static/hello.txt");//相较于当前项目（这是相对路径。即路径前省略了当前项目的路径）
        FileReader fr = null;
        try {
            //1.File类的实例化
            File file2 = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/hello.txt");//绝对路径
            //2.FileReader流的实例化
            fr = new FileReader(file2);
            //3.定义数组
            char[] cbuf = new char[1024];
            //4.读入的操作
            //read(char[] cbuf):返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1
            int len;
            while((len = fr.read(cbuf)) != -1){//将数据读到数组cbuf中
                //对读取到的数据进行操作
                for(int i = 0;i < len;i++){
                    System.out.print(cbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.资源关闭
            try {
                if(fr!=null){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }









//----Wwrite-----------------------------------------------------------------------------------------------
    /*
    从内存中写出数据到硬盘的文件里。

    说明：
    1. 输出操作，对应的File可以不存在的。并不会报异常
    2.
         File对应的硬盘中的文件如果不存在，则在输出的过程中，会自动创建此文件。
         File对应的硬盘中的文件如果存在：
                如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖
                如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容

     */

    //http://localhost:8080/file1/test1
    @RequestMapping("/test1")
    public void FileWriter ()  {
        FileWriter fw = null;
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/111.txt");

            //2.提供FileWriter的对象，用于数据的写出
            //fw = new FileWriter(file,false);多次执行write操作时，则会覆盖掉原有的文件
            fw = new FileWriter(file,true);//多次执行write操作时，会在原有文件内容后追加内容

            //3.写出的操作
            fw.write("you need to have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            if(fw != null){

                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
//----FileReader和FileWriter-----------------------------------------------------------------------------------------------------

    /*
    将某个文件的内容，写入到另一个文件中
     */
    //http://localhost:8080/file1/test2
    @RequestMapping("/test2")
    public void FileReadAndWriter ()  {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1.创建File类的对象，指明读入和写出的文件
            File srcFile = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/hello.txt");
            File destFile = new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/111.txt");

            //不能使用字符流来处理图片等字节数据
//            File srcFile = new File("爱情与友情.jpg");
//            File destFile = new File("爱情与友情1.jpg");


            //2.创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);


            //3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while((len = fr.read(cbuf)) != -1){
                //每次都将cbuf数组中的len个字符写出到文件中
                fw.write(cbuf,0,len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
                /*
                    fw.close();
                    fr.close();
                 */
                //关闭输出流
                try {
                    if(fw != null)
                        fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //关闭输入流
                try {
                    if(fr != null)
                        fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

    }


}
