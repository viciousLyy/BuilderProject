package guet.yongyu.Specify;

import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.InterpreteCommand;

import java.util.ArrayList;
import java.util.List;

public class GccInterpreter extends Interpreter {

    public GccInterpreter(){
        List<String> ext = new ArrayList<>();
        ext.add("exe");
        this.setSrcFileExt(ext);
        this.setInterpreterName("GccInterpreter");

        List<String> cmd = new ArrayList<>();
        cmd.add("cmd");
        cmd.add("/k");
        cmd.add("start");
//        cmd.add(InterpreteCommand.libPath);
        cmd.add(InterpreteCommand.main);
        this.setCommandWithWindow(cmd);

    }

    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolders, List<String> cmd, Project project) {

    }
}
