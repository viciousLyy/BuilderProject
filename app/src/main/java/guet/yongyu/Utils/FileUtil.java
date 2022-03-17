package guet.yongyu.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 找到目标路径下的所有特定后缀名的文件
     * @param result  存放结果文件的列表
     * @param targetPath 目标路径
     * @param singletonList 特定后缀名
     */
    public static void findFiles(List<File> result, File targetPath, List<String> singletonList) {
        if(targetPath.isDirectory()){
            File[] fs = targetPath.listFiles();
            for(int i=0;i<fs.length;i++){
                findFiles(result,new File(fs[i].getPath()),singletonList);
            }
        }else if(targetPath.getName().endsWith("."+singletonList.get(0))){
            result.add(targetPath);
        }
    }

    /**
     * 返回目标文件集对应的路径
     * @param srcFiles 目标文件集
     * @return 对应路径
     */
    public static List<String> getPathsOfFiles(List<File> srcFiles) {
        List<String> result = new ArrayList<>();
        for(File f:srcFiles){
            result.add(f.getAbsolutePath());
        }
        return result;
    }


}
