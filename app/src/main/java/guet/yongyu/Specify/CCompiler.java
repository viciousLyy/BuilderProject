package guet.yongyu.Specify;

import guet.yongyu.Impl.Compiler;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.CompileCommand;

import java.util.ArrayList;
import java.util.List;

public class CCompiler extends Compiler {

    /**
     * c语言编译的命令行命令为 gcc {sourceFiles} -o {targetFile}
     * {sourceFiles}:所要编译的源文件
     * {targetFile}:生成的目标文件
     */

    public CCompiler(){
        List<String> cmd = new ArrayList<>();
        List<String> ext = new ArrayList<>();
        cmd.add("gcc");
        cmd.add(CompileCommand.sourceFiles);
        cmd.add("-o");
        cmd.add(CompileCommand.targetFile);
        this.setCommand(cmd);
        this.setCompilerName("Gcc编译器");
        this.setTargetFileExt("exe");
        ext.add("c");
        this.setSrcFileExt(ext);
    }

    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolder, List<String> cmd, Project project) {

    }
}
