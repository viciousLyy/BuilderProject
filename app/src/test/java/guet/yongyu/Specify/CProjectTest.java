package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class CProjectTest {

    @Test
    void resolveMain() {
        Project project = new CProject("","c");
        String mainName = project.resolveMain();
        assertEquals("E:\\codeblockProject\\BuilderTest\\OutputFile\\BuilderTest.exe",mainName);
    }
}