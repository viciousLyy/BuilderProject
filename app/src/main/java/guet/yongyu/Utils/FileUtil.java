package guet.yongyu.Utils;

import java.io.*;
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

    /**
     * 读取文件中的所有内容
     * @param file 目标文件
     * @return 以列表的形式保存的文件内容
     */
    public static List<String> getContents(File file){
        String charSet = getCharSet(file);
        try(
                InputStream ins =new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(ins,charSet);
                BufferedReader br = new BufferedReader(isr);

        ) {
            List<String> contents = null;
            String s = br.readLine();
            while(s!=null){
                contents.add(s);
                s = br.readLine();
            }
            return contents;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 读取文件的编码方式
     * @param file 目标文件
     * @return 编码
     */
    public static String getCharSet(File file){
        byte[] head = new byte[3];
        try(
                FileInputStream stream = new FileInputStream(file);
        ) {
            stream.read(head);
            String code = "gb2312";
            if (head[0] == -1 && head[1] == -2 )
                code = "UTF-16";
            else if (head[0] == -2 && head[1] == -1 )
                code = "Unicode";
            else if(head[0]==-17 && head[1]==-69 && head[2] ==-65)
                code = "UTF-8";
            return code;
        } catch (FileNotFoundException e) {
            return "gb2312";
        } catch (IOException e) {
            return "gb2312";
        }
    }

}
