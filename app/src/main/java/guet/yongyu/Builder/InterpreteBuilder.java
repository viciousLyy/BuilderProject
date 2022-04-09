package guet.yongyu.Builder;

import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.TextFile;

import java.util.List;

public class InterpreteBuilder extends Builder{

    private final Interpreter interpreter;

    public InterpreteBuilder(Interpreter interpreter){
        this.interpreter = interpreter;
    }

    /**
     * 需要窗口展示运行
     * @param project 所要运行的项目
     */
    @Override
    public void run(Project project,String ... args) {
        try{
            interpreter.executeWithWindow(project,args);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不需要展示运行窗口的运行，如果文件有输入输出，则使用读取文件和输出文件的方式完成
     * @param project  所要运行的项目
     * @param srcFile  源文件
     * @return  返回存放结果的文件
     */
    @Override
    public TextFile run(Project project, TextFile srcFile,String ... args) {
        try {
            TextFile result = interpreter.executeWithoutWindow(project,srcFile,args);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
