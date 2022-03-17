package guet.yongyu.Utils;

import java.util.Arrays;
import java.util.List;

public class ListUtil {

    /**
     * 将字符串数组转换为字符串列表
     * @param srcExts 字符串数组
     * @return   字符串列表
     */
    public static List<String> array2List(String[] srcExts) {
        return Arrays.asList(srcExts);
    }

    /**
     * 将列表中的目标字符替换成特定字符
     * @param cmd 列表
     * @param target 目标字符
     * @param source 特定字符
     */
    public static void replaceFirst(List<String> cmd, String target, String source) {
        for(int i=0;i<cmd.size();i++){
            if(cmd.get(i).equals(target))
                cmd.set(i,source);
        }
    }

    /**
     * 将列表转换成字符串
     * @param errs 源字符串列表
     * @return   目标字符串
     */
    public static String list2Str(List<String> errs) {
        String [] tempString = errs.toArray(new String[errs.size()]);
        StringBuffer sb = new StringBuffer();
        for(String str:tempString)
            sb.append(str);
        return sb.toString();
    }

    public static String[] strSplit(String source){
        String reg = ":|,";
        String[] strings = source.split(reg);
        return strings;
    }
}
