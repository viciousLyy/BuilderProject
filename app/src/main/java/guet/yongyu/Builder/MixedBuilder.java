package guet.yongyu.Builder;

import guet.yongyu.Impl.Compiler;
import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.TextFile;

import java.util.List;

public class MixedBuilder extends Builder{
    private final Compiler compiler;
    private final Interpreter interpreter;

    public MixedBuilder(Compiler c, Interpreter interpreter){
        this.compiler=c;
        this.interpreter=interpreter;
    }

    /*
    没有输入输出的运行
     */
    @Override
    public void run(Project project)  {
        try {
            List<String> res=compiler.compile(project);
            interpreter.executeWithWindow(project);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    有输入输出的运行
     */
    @Override
    public TextFile run(Project project, TextFile srcFile) {
        return null;
    }
}
