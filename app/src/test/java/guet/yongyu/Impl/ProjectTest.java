package guet.yongyu.Impl;

import guet.yongyu.Specify.CProject;
import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.TextFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {



    @Test
    void getOutputDir() {

    }

    @Test
    void getSrcFiles(){
        Project project = new CProject("E:\\codeblockProject\\BuilderTest","c");
        List<File> allfiles = project.getSrcFiles();
        for(File f:allfiles)
            System.out.println(f.getName());
    }

    @Test
    void getContents(){
        TextFile textFile = new TextFile("E:\\codeblockProject\\BuilderTest");
        List<String> srcExt = new ArrayList<>();
        srcExt.add("c");
        List<File> files = new ArrayList<>();
        FileUtil.findFiles(files,textFile.getFile(),srcExt);
        for(File file:files){
            List<String> contents = FileUtil.getContents(file);
            for(String str:contents){
                if(str.matches("(.*)main(.*)"))
                {
                    System.out.println("this is the main class"+file.getName());
                    break;
                }
            }
        }
    }

    @Test
    void resolveMain(){

    }
}

