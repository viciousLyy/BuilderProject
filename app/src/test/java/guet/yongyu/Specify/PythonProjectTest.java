package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PythonProjectTest {

    @Test
    void resolveMain() {

        Project project = new PythonProject("E:\\BuilderProject\\testProject\\python\\Tank",
                "py");
        assertEquals("E:\\BuilderProject\\testProject\\python\\Tank\\Tank13.py",
                project.resolveMain());

    }


}