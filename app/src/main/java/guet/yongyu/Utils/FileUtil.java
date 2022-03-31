package guet.yongyu.Utils;

import info.monitorenter.cpdetector.io.*;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
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
        System.out.println(charSet);
        try(
                InputStream ins =new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(ins,charSet);
                BufferedReader br = new BufferedReader(isr);

        ) {
            List<String> contents = new ArrayList<>();
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
    public static String getChar(File file){
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

    /**
     * 利用cpdetector获取文件的编码类型
     * @param file  源文件
     * @return  源文件的编码类型
     */
    public static String getCharSet(File file){
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//        detector.add(new ParsingDetector(false));
        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
        detector.add(UnicodeDetector.getInstance());

        Charset charset = null;
        try{
            charset = detector.detectCodepage(file.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(charset != null)
            return charset.name();
        else
            return null;
    }

    /**
     * 将字符串内容写入到文件中去
     * @param path 所要写入文件的路径
     * @param contents 所要写入的内容
     */
    public static void write2File(String path,String contents){
        File file = new File(path);
        try{
            FileWriter fw = new FileWriter(path);
            fw.write(contents);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
