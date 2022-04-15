package guet.yongyu.Specify;

import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.InterpreteCommand;

import java.util.ArrayList;
import java.util.List;

public class PythonInterpreter extends Interpreter {

    /**
     * python语言解释的外部命令 py {mainFile}
     * {mainFile}:所要运行的外部命令
     */

    public PythonInterpreter(){
        List<String> cmd = new ArrayList<>();
        List<String> ext = new ArrayList<>();
//        cmd.add("cmd");
//        cmd.add("/k");
//        cmd.add("start");
        ext.add("py");
        cmd.add("python");
        cmd.add(InterpreteCommand.main);
        setCommandWithWindow(cmd);
        cmd.clear();
        ext.add("py");
        cmd.add("python");
        cmd.add(InterpreteCommand.main );
        setCommandWithoutWindow(cmd);
        setInterpreterName("PythonInterpreter");
        setSrcFileExt(ext);
    }

    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolders, List<String> cmd, Project project) {

    }

    /**
     * 由于python不需要经过编译，所以源文件没出现在OutputDir目录下，所以将工作目录设置为
     * 根目录
     * @param project 项目名
     * @return pyton源文件工作目录
     */
    @Override
    protected String getWorkDir(Project project) {
        System.out.println(project.getPath());
        return project.getPath();
    }
}
