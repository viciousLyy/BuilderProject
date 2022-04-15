package guet.yongyu.Utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void getCharSet() {
        String path = "E:\\codeblockProject\\CppTest\\字符测试.txt";
        File file = new File(path);
        System.out.println(FileUtil.getCharSet(file));
    }


    @Test
    void write2File(){
        FileUtil.write2File("E:\\IdealProject\\cmdTest\\OutputFile\\error.txt",
                "can't get any file in the project!");
    }
}