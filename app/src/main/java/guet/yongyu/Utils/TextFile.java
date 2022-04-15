package guet.yongyu.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFile  {

    private String path;
    private File file;

    public TextFile(){}

    public TextFile(String path){
        this.path = path;
        this.file = new File(path);
    }

    public File getFile(){
        return file;
    }

    /**
     * 读取文件中的所有内容
     * @return 以列表的形式保存的文件内容
     */
    public List<String> getContents(){
        File file = new File(path);
        String charSet = FileUtil.getCharSet(file);
        try(
                InputStream ins =new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(ins,charSet);
                BufferedReader br = new BufferedReader(isr);

        ) {
            List<String> contents = new ArrayList<>();
            String s = br.readLine();
            if(s==null)
                return null;
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
}
