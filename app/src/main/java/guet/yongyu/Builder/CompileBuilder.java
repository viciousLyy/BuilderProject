package guet.yongyu.Builder;

import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Impl.Compiler;
import guet.yongyu.MyException.CompilerException;
import guet.yongyu.Utils.TextFile;

import java.util.List;

public class CompileBuilder extends Builder{

    private final Compiler compiler;
    private final Interpreter interpreter;

    public CompileBuilder(Compiler compiler,Interpreter interpreter) {
        this.compiler = compiler;
        this.interpreter = interpreter;
    }

    @Override
    public void run(Project project) {
        try {
            List<String> res =compiler.compile(project);
            interpreter.executeWithWindow(project,"");
        } catch (CompilerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TextFile run(Project project, TextFile textFile){
        return null;
    }
}
