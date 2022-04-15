package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CppProject extends Project {

    public CppProject(String path,String srcExt){
        super(path,srcExt);
    }

    /**
     * 对于c语言、c++语言，主函数只能有一个，所以直接在项目中寻找以exe结尾的函数就可以了
     * @return 主函数的路径
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
