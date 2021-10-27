package com.example.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

/**
 * File类的使用
 *
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 *    并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
 * 4. 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
 *
 *
 *
 *
 * @author shkstart
 * @create 2019 下午 4:05
 */
@Controller
@RequestMapping("/file")
public class FileController {

 /*
    1.如何创建File类的实例
        File(String filePath)
        File(String parentPath,String childPath)
        File(File parentFile,String childPath)

    2.
    相对路径：相较于某个路径下，指明的路径。
    绝对路径：包含盘符在内的文件或文件目录的路径

    3.路径分隔符
     windows:\\或/
            [其实第一个\就是windows下的路径分隔符。但直接写\的话，如果后边遇到字符'n'会转义。所以实际写的时候，需要再加上一个\]
            在windows中，/没有转义的意思，所以写一个/即可。
     unix:/
     File. separator:代表路径分隔符【windows和linuxs都通用的写法】
     个人推荐:使用/即可。windows和linux都通用的写法
     */






    //http://localhost:8080/file/test
    @RequestMapping("/test")
    public void test() throws IOException {
        //1.创建file对象
            //绝对路径
            File file = new File("D:/axis.txt");//D:\axis.txt
            //相对路径
            File file1=new File("aa.txt");//aa.txt

        //1.5路径分隔符
            //windows下的分隔符
                File filea=new File("D:\\axis.txt");
                File fileb=new File("D:/axis.txt");
                File filec = new File("D:" + File.separator + "atguigu.txt");

            //linux下的分隔符
                File filed=new File("D:/axis.txt");
                File filee = new File("D:" + File.separator + "atguigu.txt");

        //2.获取功能
            /*
                 public String getAbsolutePath()：获取绝对路径
                 public String getPath() ：获取路径
                 public String getName() ：获取名称
             */
                System.out.println(file.getAbsolutePath());//D:\axis.txt
                System.out.println(file.getPath());//D:\axis.txt
                System.out.println(file.getName());//axis.txt

                System.out.println(file1.getAbsolutePath());//D:\atguigu\javase\day26-iostream\aa.txt
                System.out.println(file1.getPath());//aa.txt
                System.out.println(file1.getName());//aa.txt


        //3.判断功能
        /*

            public boolean isDirectory()：判断是否是文件目录
            public boolean isFile() ：判断是否是文件
            public boolean exists() ：判断是否存在
         */
        System.out.println(file.exists());//true
        System.out.println(file1.exists());//false


        //4.创建功能
        /*
        public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false[该文件的上级目录必须存在]
        public boolean mkdir() ：创建文件目录。如果此文件目录存在，则不创建。 如果此文件目录的上层目录不存在，则不创建。
        public boolean mkdirs() ：创建文件目录。如果此文件的上层目录不存在，则一并创建
            注意事项：如果你创建文件或者文件目录没有写盘符路径，那么，默认在项当前项目下创建文件/目录。
         */
        //创建文件
        File file2=new File("create1.txt");
        File file3=new File("D:/atguigu/javase/day26-iostream/demo1/create2.txt");
        File file4=new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/static/create3.txt");
        //File file5=new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/newDirect/create4.txt");错误写法
        if(!file2.exists()){
            file2.createNewFile();//在当前项目下创建文件/目录[并非在当前模块下创建]
            System.out.println("创建create1.txt文件成功");
        }
        if(!file3.exists()){
            file3.createNewFile();//在当前模块下创建文件/目录
            System.out.println("创建create2.txt文件成功");
        }
        if(!file4.exists()){
            file4.createNewFile();//在当前模块的resources目录下static目录下创建文件/目录
            System.out.println("创建create3.txt文件成功");
        }
        /*
            错误写法
            if(!file5.exists()){
                file5.createNewFile();//在当前模块的resources目录下static目录下创建文件/目录
                System.out.println("创建create4.txt文件成功");
            }
        */

            //创建目录
            File file6=new File("D:/atguigu/javase/day26-iostream/demo1/src/main/resources/newDirect");
            file6.mkdir();



        //5.删除功能
        /*
         public boolean delete()：删除文件或者文件夹
        删除注意事项：
        Java中的删除不走回收站。 要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录
         */

        boolean delete = file4.delete();
        if(delete==true){
            System.out.println("resources目录的static下的create3.txt文件删除成功");
        }


    }




}






