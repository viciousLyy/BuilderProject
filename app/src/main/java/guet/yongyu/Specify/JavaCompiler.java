package guet.yongyu.Specify;

import guet.yongyu.Impl.Compiler;
import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.CompileCommand;
import guet.yongyu.Utils.FileUtil;
import guet.yongyu.Utils.ListUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaCompiler extends Compiler{

    /**
     * 对于java源代码的编译，命令行的格式如下
     * javac -encoding {coding} -cp {libPath} {sourceFiles} -d {targetPath}
     * coding:源代码中的字符编码
     * libPath:程序所引用的类库，jar包之类的文件
     * sourceFiles:源文件
     * targetPath:把生成的文件的存放在该路径下
     */

    public JavaCompiler(){
        List<String> cmd = new ArrayList<>();
        List<String> ext = new ArrayList<>();
        ext.add("java");
        cmd.add("javac");
        cmd.add("-encoding");
        cmd.add(CompileCommand.coding);
        cmd.add("-cp");
        cmd.add(CompileCommand.libPath);
        cmd.add(CompileCommand.sourceFiles);
        cmd.add("-d");
        cmd.add(CompileCommand.targetPath);
        setCommand(cmd);
        setSrcFileExt(ext);
        setCompilerName("JavaCompiler");
        setTargetFileExt("class");
    }


    /**
     * java项目中比一般的命令行格式多了{libPath}、{coding}和{targetPath}占位符，
     * 所以对该三种的占位符进行额外的填充
     * @param extraPlaceHolder 额外占位符列表
     * @param cmd 命令行对象
     * @param project 项目
     */
    @Override
    protected void populateExtraPlaceHolder(List<String> extraPlaceHolder, List<String> cmd, Project project) {
        List<File> result = project.getExtFiles("jar");
        List<String> files = new ArrayList<>();
        System.out.println(result);
        if(!result.isEmpty()){
            for(File file:result){
                files.add(file.getAbsolutePath()+";");
            }
            ListUtil.replaceFirst(cmd,CompileCommand.libPath,ListUtil.list2Str(files));
        }

        String charset= FileUtil.getCharSet(project.getSrcFiles().get(0));
        ListUtil.replaceFirst(cmd,CompileCommand.coding,charset);

        String targetPath = project.getOutputDir().getAbsolutePath();
        ListUtil.replaceFirst(cmd,CompileCommand.targetPath,targetPath);
    }
}
