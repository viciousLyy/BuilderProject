package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.ListUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaProject extends Project {

    private static String path;
    private static String srcExt;

    public JavaProject(String path,String srcExt){
        super(path,srcExt);
        this.path = path;
        this.srcExt = srcExt;
    }

    /**
     *获取java项目中主入口所在的文件的名字，比如Main.class，可能有多个，所以给出用户选择的权力
     * @return  包名+文件名+".class"，如果没有包名则直接返回文件名+后缀
     */
    @Override
    public String resolveMain() {
        String mainFileName = getMainFunctions().get(MainIndex);
        File mainFile = new File(mainFileName);
        List<String> contents = FileUtil.getContents(mainFile);
        String packName = "";

        /**
         * 获取java文件的包名
         */
        for(String str:contents){
            if(str.matches("\\s*package\\s.*")){
                packName = str.split("\\s|;")[1];
                break;
            }
        }

        if(packName != ""){
            return packName+"."+mainFile.getName().replaceFirst(
                    ".java","");
        }else{
            return mainFile.getName().replaceFirst(".java","");
        }
    }
}
