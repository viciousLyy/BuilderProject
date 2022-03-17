package guet.yongyu.Impl;

import guet.yongyu.Utils.InterpreteCommand;
import guet.yongyu.Utils.ListUtil;
import guet.yongyu.Utils.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Interpreter {
    private final ProcessBuilder processBuilder;

    /**
     * 解释器的显示名称
     */
    private String interpreterName;
    /**
     * 用于单独窗口运行程序的命令
     */
    private final List<String> cmdLineWithWindow;
    /**
     * 用于后台方式运行程序，通常用于检查语法错误，测试程序
     */
    private final List<String> cmdLineWithoutWindow;
    /**
     * 用于解释的源程序文件名，对于java，是java，对于python是py
     */
    private final List<String> srcFileExt;
    /**
     * 超时时间
     */
    private long timeout=5;

    public Interpreter() {
        processBuilder = new ProcessBuilder();
        interpreterName = "interpreter";
        cmdLineWithWindow = new ArrayList<>();
        cmdLineWithoutWindow = new ArrayList<>();
        srcFileExt = new ArrayList<>();
    }

    /**
     * 以独立的窗口运行项目,该方法是假定程序没有语法错误的
     *
     * @param project 项目
     * @param args    命令行参数
     * @throws Exception 解释器执行时异常
     * @throws Exception 项目异常
     */
    public void executeWithWindow(Project project, String... args) throws Exception {
        System.out.println("----------exe begin--------------");
        List<String> cmd = resetCmdLine(cmdLineWithWindow);
        populatePlaceholders(cmd,project);
        System.out.println("----------exe ing--------------");
        cmd.addAll(Arrays.asList(args));
        Process p;
        try {
            p = processBuilder.start();
            p.waitFor(timeout, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 重置命令行参数为新的命令行
     * @param newCmdLine 新的命令行
     * @return 重置后的命令行参数
     */
    private List<String> resetCmdLine(List<String> newCmdLine) {
        List<String> cmd = processBuilder.command();
        cmd.clear();
        cmd.addAll(newCmdLine);
        return cmd;
    }

    /**
     * 根据项目对象，命令行程序运行参数，填充命令行中的占位符
     * @param cmd 包含占位符的命令行，
     * @param project 项目对象
     * @throws Exception 项目异常
     */
    protected  void populatePlaceholders(List<String> cmd, Project project) throws Exception
    {
        ListUtil.replaceFirst(cmd, InterpreteCommand.libPath,
                project.getOutputDir().getAbsolutePath());
        ListUtil.replaceFirst(cmd,InterpreteCommand.main,
                project.resolveMain());
        List<String>extraPlaceHolders= Compiler.getExtraPlaceHolder(cmd);
        if(extraPlaceHolders.size() >0){
            populateExtraPlaceHolder(extraPlaceHolders,cmd,project);
        }
    }
    /**
     * 填充命令行中的除CompileCommand外的占位符(以{}表示的字符串）
     * @param extraPlaceHolders 额外占位符列表
     * @param cmd 命令行对象
     * @param project 项目
     */
    protected abstract void populateExtraPlaceHolder(List<String> extraPlaceHolders, List<String> cmd, Project project);

    /**
     * 检查项目是否有语法错误，该方法针对的是纯解释型语言,若有语法错误，则返回不为null,否则，返回null
     *
     * @param project 待检查的项目
     * @throws Exception 操作执行不成功时
     * @throws Exception 项目异常
     * @return 语法错误
     */
    public List<String> checkSyntaxErr(Project project) throws Exception, Exception {
        processBuilder.redirectInput(ProcessBuilder.Redirect.PIPE);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        List<String> cmd = resetCmdLine(cmdLineWithoutWindow);
        populatePlaceholders(cmd,project);
        String targetPath = project.getOutputDir().getAbsolutePath();

        TextFile errFile = new TextFile(targetPath + File.separator + "err.txt");
        processBuilder.redirectError(errFile.getFile());
        Process p;
        try {
            p = processBuilder.start();
            p.waitFor(5, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            throw new Exception(e.getMessage());
        }
        return errFile.getContents();
    }



    /**
     * 返回解释器解释执行的原始源文件的扩展名
     * @return 解释器支持的源文件扩展名列表
     */
    public List<String> getSrcExts() {
        return srcFileExt;
    }

    /**
     * 设置用于运行编译器的命令行命令（后台运行，无窗口），这种命令通常用于检查语法错误，使用测试用例测试程序
     *
     * @param cmd 待设置的参数
     */
    public void setCommandWithoutWindow(List<String> cmd) {
        cmdLineWithoutWindow.clear();
        cmdLineWithoutWindow.addAll(cmd);
    }

    /**
     * 设置编译器的显示名称
     *
     * @param interpreterName 编译器显示名称
     */
    public void setInterpreterName(String interpreterName) {
        this.interpreterName = interpreterName;
    }

    /**
     * 设置源文件扩展名列表
     *
     * @param srcExt 待设置的列表
     */
    public void setSrcFileExt(List<String> srcExt) {
        srcFileExt.clear();
        srcFileExt.addAll(srcExt);
    }

    /**
     * 设置带窗口执行程序的命令行字符串列表，该列表中将包含{main}占位符，它会被实际需要替换
     *
     * @param cmd 命令行参数对象
     */
    public void setCommandWithWindow(List<String> cmd) {
        cmdLineWithWindow.clear();
        cmdLineWithWindow.addAll(cmd);

    }

    /**
     * 返回解释器进程构建器对象
     * @return  进程构建器对象
     */
    public ProcessBuilder getProcessBuilder() {
        return processBuilder;
    }

    /**
     * 以输入、输出重定向且后台运行的方式执行项目
     *
     * @param project 项目
     * @param input 输入重定向文本文件
     * @param args 命令行参数
     * @throws Exception 解释过程中的异常
     * @throws Exception 项目异常
     * @return 程序输出内容存放的文件
     */
    public TextFile executeWithoutWindow(Project project, TextFile input, String... args) throws Exception, Exception {
        processBuilder.redirectInput(input.getFile());
        File targetPath = project.getOutputDir();
        TextFile output = new TextFile(targetPath.getPath() + File.separator + "output.txt");
        processBuilder.redirectOutput(output.getFile());
        TextFile errFile = new TextFile(targetPath.getPath()+File.separator+"err.txt");
        processBuilder.redirectError(errFile.getFile());
        List<String>cmd= resetCmdLine(cmdLineWithoutWindow);
        populatePlaceholders(cmd,project);
        cmd.addAll(Arrays.asList(args));
        Process p;
        try {
            p = processBuilder.start();
            p.waitFor(timeout, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            throw new Exception(e.getMessage());
        }
        List<String>errs = errFile.getContents();
        if(errs==null)
            return output;
        String errMsg=ListUtil.list2Str(errs);
        throw new Exception(errMsg);
    }

    /**
     * 返回超时实际设置（单位秒）
     * @return 超时时间（秒）
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * 设置执行程序的超时时间（秒）
     * @param n 时间，秒
     */
    public void setTimeout(long n) {
        timeout = n;
    }

    /**
     * 返回解释器的显示名称
     * @return 解释器显示名称
     */
    public String getInterpreterName(){
        return this.interpreterName;
    }
}
