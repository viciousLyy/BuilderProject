package guet.yongyu.Impl;

import guet.yongyu.Specify.CProject;
import guet.yongyu.Specify.CppProject;
import guet.yongyu.Specify.JavaProject;
import guet.yongyu.Specify.PythonProject;
import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.TextFile;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {


    Project Cppproject = new CppProject("","");
    Project Javaproject = new JavaProject("","");
    Project Pythonproject = new PythonProject("","");

    @Test
    void getOutputDir() {

    }

    @Test
    void getSrcFiles(){
        Project project = new PythonProject("E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\PythonTest"
                ,"py");
        List<File> allfiles = project.getSrcFiles();
        assertEquals(
                "E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\PythonTest\\Tank10.py",
                allfiles.get(9).getAbsolutePath());
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

    @Test
    void getMainFiles(){
        JavaProject Javaproject = new JavaProject("E:\\codeblockProject\\CppTest","java");
        Javaproject.getSrcFiles();
    }

    @Test
    void getErrTxt(){
        Project p = new JavaProject("E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava",
                "java");
        System.out.println(p.getErrTxt());
    }

    @Test
    void getMainFunctions(){
        Project project = new PythonProject(
                "E:\\BuilderProject\\testProject\\python\\errorPython",
                "py"
        );
        List<String> results = new ArrayList<>();
        results.add("E:\\BuilderProject\\testProject\\python\\errorPython\\error.py");
        assertEquals(results.get(0),project.getMainFunctions().get(0));
    }
}

