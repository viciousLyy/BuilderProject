package guet.yongyu.Impl;

import guet.yongyu.MyException.CompilerException;
import guet.yongyu.Specify.*;
import guet.yongyu.Utils.CompileCommand;
import guet.yongyu.Utils.InterpreteCommand;
import guet.yongyu.Utils.ListUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {

    /**
     * 测试编译函数
     */
    @Test
    void compile() {
        Project project = new CProject("E:\\BuilderProject\\testProject\\c\\Error",
                "c");
        Compiler compiler = new CCompiler();
        List<String> result = null;
        try {
            result = compiler.compile(project);
        } catch (CompilerException e) {
            e.printStackTrace();
        }
//        assertEquals("E:\\BuilderProject\\testProject\\c\\Cproject\\OutputFile\\Cproject.exe",
//                result.get(0));
    }

    /**
     * 测试填充命令行函数
     */
    @Test
    void populatePlaceHolder() {
        List<String> cmd = new ArrayList<>();
//        cmd.add("javac");cmd.add("-encoding");cmd.add(CompileCommand.coding);cmd.add("-cp");cmd.add(CompileCommand.libPath);
//        cmd.add(CompileCommand.sourceFiles);cmd.add("-d");cmd.add(CompileCommand.targetPath);
//        compiler.populatePlaceHolder(cmd,project);
//        String[] array = {"javac","-encoding","UTF-8","-cp",
//                "E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava\\bin\\chardet-1.0.jar;E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava\\bin\\commom-lang3\\common-lang3.jar;E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava\\bin\\cpdetector_1.0.10.jar;E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava\\bin\\ext\\antlr-2.7.4.jar;E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava\\bin\\ext\\jargs-1.0.jar;"
//                ,"E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava\\src\\Main.java","-d","E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CharSetgetJava\\OutputFile"};
//        List<String> result = ListUtil.array2List(array);
//        assertEquals(result,cmd);

//        cmd.add("g++");cmd.add("-I");cmd.add(CompileCommand.libPath);
//        cmd.add(CompileCommand.sourceFiles);cmd.add("-o");
//        cmd.add(CompileCommand.targetFile);
//        compiler.populatePlaceHolder(cmd,project);
//        String[] array = {"g++","-I","{classpath}","E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CppTest\\src\\main.cpp","E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CppTest\\src\\function.cpp"
//        ,"-o","E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\CppTest\\OutputFile\\CppTest.exe"};
//        List<String> result = ListUtil.array2List(array);
//        assertEquals(result,cmd);
//
//        cmd.add("python");
//        cmd.add(InterpreteCommand.main);
//        String[] array = {"python","E:\\BuilderProject\\app\\src\\main\\resources\\testProject\\PythonTest\\Tank09.py"};
//        List<String> result = ListUtil.array2List(array);
//        ByteArrayInputStream in = new ByteArrayInputStream("9".getBytes());
//        System.setIn(in);
//        try {
//            interpreter.populatePlaceholders(cmd,project);
//            assertEquals(result,cmd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Test
    void populateExtraPlaceHolder() {

    }

    @Test
    void getExtraPlaceHolder() {

    }
}