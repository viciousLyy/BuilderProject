package guet.yongyu.Builder;

import guet.yongyu.Impl.Compiler;
import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.MyException.CompilerException;
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
    public void run(Project project,String ... args)  {
        try {
            List<String> res=compiler.compile(project);
            interpreter.executeWithWindow(project,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    有输入输出的运行
     */
    @Override
    public TextFile run(Project project, TextFile srcFile,String ... args) {
        try {
            List<String> res = compiler.compile(project);
            TextFile result = interpreter.executeWithoutWindow(project,srcFile,args);
            return result;
        } catch (CompilerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
