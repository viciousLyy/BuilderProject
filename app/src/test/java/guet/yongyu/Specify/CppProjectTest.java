package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CppProjectTest {

    @Test
    void resolveMain(){
        Project CppProject = new CppProject("E:\\BuilderProject\\testProject\\CppTest",
                "cpp");
        assertEquals("E:\\BuilderProject\\testProject\\CppTest\\OutputFile\\CppTest.exe",
                CppProject.resolveMain());
    }

}