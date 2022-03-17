package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.PropUtil;

import java.io.File;
import java.util.List;

public class CProject extends Project {

    private static String path;
    private static String ext;

    public CProject(String path,String ext){
        super(path,ext);
        this.path = path;
        this.ext = ext;
    }

    /**
     * c语言的获取main类
     * @return main类的文件名
     */
    @Override
    public String resolveMain() {
        String mainName = null;
        allFiles = getMainFiles(getOutputDir().getAbsolutePath());
//        String regx = PropUtil.getParameterOfProject(ext);
//        for(File file:allFiles){
//            List<String> contents = FileUtil.getContents(file);
//            for(String content:contents){
//                if(content.matches(regx))
//                {
//                    mainName = file.getName();
//                    break;
//                }
//            }
//        }

        String targetfile = getProjectName()+".exe";
        for(File file:allFiles){
            if(file.getName().matches(targetfile))
                return file.getAbsolutePath();
        }
        return null;
    }

}
