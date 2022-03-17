package guet.yongyu.Impl;

import guet.yongyu.Specify.CProject;
import org.junit.jupiter.api.Test;

import java.io.File;
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
}

