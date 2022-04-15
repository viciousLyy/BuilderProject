package guet.yongyu.Specify;

import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.CompileCommand;
import guet.yongyu.Utils.InterpreteCommand;

import java.util.ArrayList;
import java.util.List;

public class CInterpreter extends Interpreter {

    /**
     * c语言运行的外部命令 {mainFile}
     * {mainFile}:所要运行的源文件
     */

    public CInterpreter(){
        List<String> cmd = new ArrayList<>();
        List<String> ext = new ArrayList<>();
        ext.add("exe");
//        cmd.add("cmd");
//        cmd.add("/k");
//        cmd.add("start");
        cmd.add(InterpreteCommand.main);
        this.setCommandWithWindow(cmd);
        this.setSrcFileExt(ext);
        this.setInterpreterName("Gcc解释器");

        cmd.clear();
        cmd.add(InterpreteCommand.main);
        this.setCommandWithoutWindow(cmd);

    }
    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolders, List<String> cmd, Project project) {

    }

    /**
     * 对应于c语言项目，工作目录可以在根目录下，也可以在OutputFile目录下
     * @param project 项目
     * @return 工作目录
     */
    @Override
    protected String getWorkDir(Project project) {
        return project.getPath();
    }

}
