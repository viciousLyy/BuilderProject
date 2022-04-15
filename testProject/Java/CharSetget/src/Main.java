
import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.UnicodeDetector;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String getCharSet(File file){
        CodepageDetectorProxy d = CodepageDetectorProxy.getInstance();
        d.add(JChardetFacade.getInstance());
        d.add(ASCIIDetector.getInstance());
        d.add(UnicodeDetector.getInstance());

        Charset charset = null;

        try{
            charset = d.detectCodepage(file.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(charset!=null)
            return charset.name();
        else
            return null;
    }

    public static void ArrayTest(){
        int[] testInt = {1,2,3,4};
        ArrayUtils.reverse(testInt);
        for(int i:testInt){
            System.out.print(i);
        }
    }

    public static List<String> getExtraPlaceHolder(List<String> cmd) {
        List<String>result=new ArrayList<>();
        String reg="\\{.+}|\\{.+};";
        for(String s:cmd){
            if (s.matches(reg)) {
                result.add(s);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String path = null;
        Scanner sc = new Scanner(System.in);
        path = sc.next();
        String charset = getCharSet(new File(path));
        System.out.println(charset);
    }

}



