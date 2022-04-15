package guet.yongyu.Specify;

import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.InterpreteCommand;

import java.util.ArrayList;
import java.util.List;

public class CppInterpreter extends Interpreter {

    /**
     * c++外部运行命令 {mainFile}
     * {mainFile}:所要运行的文件
     */

    public CppInterpreter(){
        List<String> ext = new ArrayList<>();
        ext.add("exe");
        this.setSrcFileExt(ext);
        this.setInterpreterName("GccInterpreter");

        List<String> cmd = new ArrayList<>();
//        cmd.add("cmd");
//        cmd.add("/k");
//        cmd.add("start");
//        cmd.add(InterpreteCommand.libPath);
        cmd.add(InterpreteCommand.main);
        this.setCommandWithWindow(cmd);
        cmd.clear();
        cmd.add(InterpreteCommand.main);
        this.setCommandWithoutWindow(cmd);
    }

    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolders, List<String> cmd, Project project) {

    }

    /**
     * c++运行工作目录没有太多要求，所以我们这里设置为根目录，设置为OutputDir目录也可以
     * @param project 项目名
     * @return 工作目录
     */
    @Override
    protected String getWorkDir(Project project) {
        return project.getPath();
    }
}
