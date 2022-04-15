package guet.yongyu.Impl;

import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.PropUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Project {
    /**
     * 项目在内存中所处的路径
     */
    private static String path;

    /**
     * 项目的后缀名
     */
    private static String srcExt;

    /**
     * 用于返回返回主入口函数的序号，初始化为0意味着默认只有一个主入口函数
     */
    public int MainIndex = 0;

    public static List<File> allFiles = new ArrayList<File>();

    public Project(){}

    public String getSrcExt() {
        return srcExt;
    }

    public void setSrcExt(String srcExt) {
        Project.srcExt = srcExt;
    }

    public Project(String path, String srcExt){
        this.path = path;
        this.srcExt = srcExt;
    }

    public String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Project.path = path;
    }

    /**
     * 获得目录下所有得文件
     * @param path 目录
     * @return 文件集合
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

    /**
     * 为项目创建一个存放输出的专门目录，名为OutputFile
     * @return 文件路径
     */
    public File getOutputDir(){
        File file = new File(path+File.separator+"OutputFile");
        if(!file.exists())
            file.mkdir();
        return file;
    }

    /**
     * 得到当前项目下所有以扩展名结尾的文件（比如c,cpp）
     * @return  一个文件集合
     */
    public List<File> getSrcFiles(){
        List<File> result = new ArrayList<>();
        List<String> srcExtSingle = new ArrayList<>();
        srcExtSingle.add(getSrcExt());
        File file = new File(getPath());
        FileUtil.findFiles(result,file,srcExtSingle);
        if(result.isEmpty()){
            FileUtil.write2File(getOutputDir()+File.separator+"error.txt",
                    "找不到任何源文件，该项目不是目标项目");
            try {
                throw new Exception("找不到任何源文件");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 得到当前项目下所有以ext后缀结尾的文件（比如jar）
     * @param ext 特定后缀
     * @return  以特定后缀结尾的文件集合
     */
    public List<File> getExtFiles(String ext){
        List<File> result = new ArrayList<>();
        List<String> srcExtSingle = new ArrayList<>();
        srcExtSingle.add(ext);
        File file = new File(getPath());
        FileUtil.findFiles(result,file,srcExtSingle);
        return result;
    }

    /**
     * 获得到该项目的项目名字
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

    /**
     * 读取编译器过后生成的error文件内容，如果没有内容，则返回null
     * @return error文件中的内容
     */
    public List<String> getErrorTxt(){
        File file = new File(getOutputDir().getAbsolutePath()+
                File.separator+"error.txt");
        if(!file.exists())
        {
            return null;
        }
        if(FileUtil.getContents(file) != null){
            List<String> result = FileUtil.getContents(file);
            return result;
        }

        return null;
    }

    /**
     * 读取解释器生成的err文件内容
     * @return err.txt内容
     */
    public List<String> getErrTxt(){
        File file = new File(getOutputDir().getAbsolutePath()+
                File.separator+"err.txt");
        if(!file.exists())
        {
            return null;
        }
        if(FileUtil.getContents(file) != null){
            List<String> result = FileUtil.getContents(file);
            return result;
        }
        return null;
    }

    /**
     * 获取项目中主入口的函数名，有可能有多个
     * java中判断主入口函数的正则表达式为：".*public static void main\\(String\\[\\] args\\).*"
     * python中判断主入口函数正则表达式为："^(?!(def)).*" && "^(?!\\s).*"
     * @return 主入口函数名集合，如果没有，则返回null
     */
    public List<String> getMainFunctions(){
        /**
         * 用于判断java主入口的正则表达式
         */
        String regxJava = ".*public static void main\\(String\\[\\] args\\).*";

        /**
         * 用于判断python主函数入口的正则表达式
         */
        String regxPython = "^(?!(def)).*" ;
        String regxPython1= "^(?!\\s).*";

        List<String> results = new ArrayList<>();
        List<File> srcFiles = getSrcFiles();

        /**
         * 如果是java和python，需要进行判断是否有多个，如果是c或者c++则不需要进行判断，直接将
         * results的大小设置为1就可以了，即添加一个 “” 元素。
         */
        if(getSrcExt().equals("java")){
            for(File file:srcFiles){
                List<String> contents = FileUtil.getContents(file);
                if(contents == null)
                    continue;
                for(String content:contents){
                    if(content.matches(regxJava)){
                        results.add(file.getAbsolutePath());
                        break;
                    }
                }
            }
        }else if(getSrcExt().equals("py")){
            for(File file:srcFiles){
                List<String> contents = FileUtil.getContents(file);
                if(contents == null)
                    continue;
                for(String content:contents){
                    if( content.matches(regxPython) && content.matches(regxPython1) ){
                        results.add(file.getAbsolutePath());
                        break;
                    }
                }
            }
        }else {
            results.add("");
        }

        return results;
    }

    /**
     * 如果项目中有多个主入口函数，则调用此函数可以选择使用哪一个主函数
     * @param index
     */
    public void setMainIndex(int index){
        this.MainIndex = index;
    }
}
