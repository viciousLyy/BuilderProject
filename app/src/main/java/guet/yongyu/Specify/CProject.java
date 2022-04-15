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
     * c语言的获取生成的可运行文件，只有一个说明不需要进行选择
     * @return main类的文件名
     */
    @Override
    public String resolveMain() {
        List<File> result = getMainFiles(getOutputDir().getAbsolutePath());
            for(File file:result){
                if(file.getName().endsWith(".exe"))
                {
                    return file.getAbsolutePath();
                }
            }
        return null;
    }

}
