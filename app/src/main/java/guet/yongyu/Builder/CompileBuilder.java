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
    public void run(Project project,String ... args) {
        try {
            List<String> res =compiler.compile(project);
            interpreter.executeWithWindow(project,args);
        } catch (CompilerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TextFile run(Project project, TextFile textFile,String ... args){
        try {
            List<String> res = compiler.compile(project);
            TextFile result = interpreter.executeWithoutWindow(project,textFile,args);
            return result;
        } catch (CompilerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
