package guet.yongyu.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtil {

    /**
     * 返回compiler配置文件中相应属性对应的值
     * @param key  属性
     * @return  属性对应的值
     */
    public static String getParameterOfCompiler(String key){
        String value = null;
        Properties properties = new Properties();
        FileInputStream in = null;
        try{
            //路径问题
            in = new FileInputStream("app/src/main/resources/compiler.properties");
            properties.load(in);
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 返回project配置文件中相应属性对应的值
     * @param key  属性
     * @return  属性对应的值
     */
    public static String getParameterOfProject(String key){
        String value = null;
        Properties properties = new Properties();
        FileInputStream in = null;
        try{
            //路径问题
            in = new FileInputStream("app/src/main/resources/project.properties");
            properties.load(in);
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
