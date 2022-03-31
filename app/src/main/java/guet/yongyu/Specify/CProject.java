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
     * c语言的获取生成的可运行文件
     * @return main类的文件名
     */
    @Override
    public String resolveMain() {
        String[] strings = {};
        List<File> result = getMainFiles(getOutputDir().getAbsolutePath());
        String targetfile = getProjectName()+".exe";
        for(File file:result){
            if(file.getName().matches(targetfile))
            {

                return file.getAbsolutePath();
            }
        }
        return null;
    }

}
