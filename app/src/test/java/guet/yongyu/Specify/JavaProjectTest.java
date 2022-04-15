package guet.yongyu.Specify;

import guet.yongyu.Impl.Project;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaProjectTest {

    @Test
    void resolveMain() {
        Project project = new JavaProject("E:\\BuilderProject\\testProject\\Java\\packageTest",
                "java");
        assertEquals("comA.Main",project.resolveMain());
    }
}