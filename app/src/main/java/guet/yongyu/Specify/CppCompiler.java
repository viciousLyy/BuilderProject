package guet.yongyu.Specify;

import guet.yongyu.Impl.Compiler;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.CompileCommand;

import java.util.ArrayList;
import java.util.List;

public class CppCompiler extends Compiler {

    public CppCompiler(){
        List<String>exts=new ArrayList<>();
        exts.add("c");
        exts.add("cpp");
        this.setSrcFileExt(exts);
        this.setTargetFileExt("exe");
        this.setCompilerName("CPP编译器");
        List<String> cmd=new ArrayList<>();
        cmd.add("g++");
        cmd.add("-I");
        cmd.add(CompileCommand.libPath);
        cmd.add(CompileCommand.sourceFiles);
        cmd.add("-o");
        cmd.add(CompileCommand.targetFile);
        this.setCommand(cmd);


    }
    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolder, List<String> cmd, Project project) {

    }
}
