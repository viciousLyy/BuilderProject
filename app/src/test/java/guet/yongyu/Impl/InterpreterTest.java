package guet.yongyu.Impl;

import guet.yongyu.Specify.*;
import guet.yongyu.Utils.TextFile;
import org.junit.jupiter.api.Test;

import javax.xml.soap.Text;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    @Test
    void executeWithWindow() {
        Project project = new PythonProject(
                "E:\\BuilderProject\\testProject\\python\\Tank","py"
        );
        Interpreter interpreter = new PythonInterpreter();
        try {
            project.setMainIndex(12);
            interpreter.executeWithWindow(project);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkSyntaxErr() {

    }

    @Test
    void executeWithoutWindow() {

    }
}