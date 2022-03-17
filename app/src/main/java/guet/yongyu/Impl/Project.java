package guet.yongyu.Impl;

import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.PropUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Project {
    private static String path;
    private static String srcExt;

    public static List<File> allFiles = new ArrayList<File>();

    public Project(){}

    public static String getSrcExt() {
        return srcExt;
    }

    public static void setSrcExt(String srcExt) {
        Project.srcExt = srcExt;
    }

    public Project(String path, String srcExt){
        this.path = path;
        this.srcExt = srcExt;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Project.path = path;
    }

    /*
        获得所有的文件
         */
    public List<File> getMainFiles(String path){
        File[] files = new File(path).listFiles();
        for(File file:files){
            if(file.isDirectory())
                getMainFiles(file.getAbsolutePath());
            else
                allFiles.add(file);
        }
        return allFiles;
    }


    public void setMainFileIndex(int index){

    }

    /**
     * 指定项目生成文件的路径
     * @return 文件路径
     */
    public File getOutputDir(){
        File file = new File(path+File.separator+"OutputFile");
        if(!file.exists())
            file.mkdir();
        return file;
    }

    /**
     * 得到当前项目下所有以某一个后缀结尾的文件（比如c,cpp）
     * @return  一个文件列表（以某一后缀结尾）
     */
    public List<File> getSrcFiles(){
        List<String> srcExtSingle = new ArrayList<>();
        srcExtSingle.add(getSrcExt());
        File file = new File(getPath());
        FileUtil.findFiles(allFiles,file,srcExtSingle);
        return allFiles;
    }

    /**
     * 得到该项目的项目名字
     * @return 项目名字
     */
    public String getProjectName() {
        File file = new File(path);
        String rolePath = "";
        String projectName = "";
        try {
            rolePath = file.getCanonicalPath();
            projectName = rolePath.substring(rolePath.lastIndexOf("\\") + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectName;
    }

    /**
     * 找到文件的main类,每一种不同语言有不同的主类，通过读取配置文件实现
     * @return main类的名字
     */
    public abstract String resolveMain() ;



}
