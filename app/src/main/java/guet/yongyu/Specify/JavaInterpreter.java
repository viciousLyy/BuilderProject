package guet.yongyu.Specify;

import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.CompileCommand;
import guet.yongyu.Utils.InterpreteCommand;
import guet.yongyu.Utils.ListUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaInterpreter extends Interpreter {

    /**
     * java语言的解释的外部命令 java -cp {classpath} {mainFiles}
     * {classpath}:源代码引用的外部文件，比如jar包等等
     * {mainFile}:所要运行的外部命令
     */

    public JavaInterpreter(){
        List<String> cmd = new ArrayList<>();
//        cmd.add("cmd");
//        cmd.add("/k");
//        cmd.add("start");
        cmd.add("java");
        cmd.add("-cp");
        cmd.add(InterpreteCommand.jarPath);
        cmd.add(InterpreteCommand.main);
        setCommandWithWindow(cmd);
        cmd.clear();
        cmd.add("java");
        cmd.add("-cp");
        cmd.add(InterpreteCommand.jarPath);
        cmd.add(InterpreteCommand.main);
        setCommandWithoutWindow(cmd);
        setInterpreterName("java解释器");
        List<String> ext = new ArrayList<>();
        ext.add("class");
        setSrcFileExt(ext);
    }

    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolders, List<String> cmd, Project project) {
        List<File> result = project.getExtFiles("jar");
        if(result.size() == 0){
            return ;
        }
        List<String> files = new ArrayList<>();
        System.out.println(result);
        if(!result.isEmpty()){
            for(File file:result){
                files.add(file.getAbsolutePath()+";");
            }
            ListUtil.replaceFirst(cmd, InterpreteCommand.jarPath,ListUtil.list2Str(files));
        }
    }

    /**
     * java中的运行工作目录，必须要在包的上一级上运行该主类class文件
     * @param project 项目
     * @return 工作目录
     */
    @Override
    protected String getWorkDir(Project project) {
        return project.getOutputDir().getAbsolutePath();
    }
}
