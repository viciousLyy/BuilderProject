package guet.yongyu.Builder;

import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.TextFile;

public abstract class Builder {

    /*
    无输入的项目运行
     */
    public abstract void run(Project project);

    /*
    有输入的项目运行
     */
    public abstract TextFile run(Project project, TextFile srcFile);
}
