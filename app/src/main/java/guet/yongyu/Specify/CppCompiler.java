package guet.yongyu.Specify;

import guet.yongyu.Impl.Compiler;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.CompileCommand;

import java.util.ArrayList;
import java.util.List;

public class CppCompiler extends Compiler {

    /**
     * 对于c++项目的编译，命令行的格式如下
     * g++ -I {classpath} {sourceFile} -o {targetFile}
     * classpath:源程序中涉及到的头文件
     * sourceFile:所要进行编译的源文件
     * targetFile:源文件编译后生成的文件名
     */

    public CppCompiler(){
        List<String>exts=new ArrayList<>();
        exts.add("c");
        exts.add("cpp");
        setSrcFileExt(exts);
        setTargetFileExt("exe");
        setCompilerName("CPP编译器");
        List<String> cmd=new ArrayList<>();
        cmd.add("g++");
        cmd.add("-I");
        cmd.add(CompileCommand.libPath);
        cmd.add(CompileCommand.sourceFiles);
        cmd.add("-o");
        cmd.add(CompileCommand.targetFile);
        setCommand(cmd);



    }
    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolder, List<String> cmd, Project project) {

    }
}
