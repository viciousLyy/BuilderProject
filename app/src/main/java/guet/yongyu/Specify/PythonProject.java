package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.ListUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PythonProject extends Project {

    public PythonProject(String path,String ext){
        super(path,ext);
    }

    /**
     * 获取项目的主函数入口，比如main.py，可能存在多个
     * @return 包含主函数入口所在的源文件
     */
    @Override
    public String resolveMain() {
        return getMainFunctions().get(MainIndex);
    }
}
