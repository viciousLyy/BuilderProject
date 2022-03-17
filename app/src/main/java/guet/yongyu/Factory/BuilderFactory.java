package guet.yongyu.Factory;

import guet.yongyu.Builder.Builder;
import guet.yongyu.Builder.CompileBuilder;
import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Compiler;
import guet.yongyu.Utils.ListUtil;
import guet.yongyu.Utils.PropUtil;

public class BuilderFactory {
    private static BuilderFactory factory;

    /**
     * 单例类，构造函数私有化
     */
    private BuilderFactory(){}

    /**
     * 返回工厂实例
     * @return
     */
    public static BuilderFactory getInstance(){
        synchronized (BuilderFactory.class){
            if(factory == null)
                factory = new BuilderFactory();
        }
        return factory;
    }

    /**
     * 根据传入的后缀参数返回一个相对应的构建器
     * @param srcExt 后缀参数
     * @return  对应的构建器
     */
    public Builder getProjectBuilder(String srcExt) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Builder builder = null;
        String value = PropUtil.getParameter(srcExt);
        if(value == null){
            System.out.println("找不到该参数对应的构建器");
            return null;
        }

        String[] builders = ListUtil.strSplit(value);           //读取配置文件

        String pathOfSpecify = "guet.yongyu.Specify.";        //各个实现类的具体包名
        switch(builders[builders.length-1]){
            case "CompileBuilder":
                Compiler compiler = (Compiler) Class.forName(pathOfSpecify+builders[1]).newInstance();
                Interpreter interpreter = (Interpreter) Class.forName(pathOfSpecify+builders[3]).newInstance();
                builder  = new CompileBuilder(compiler,interpreter);
                break;

            case "MixedBuilder":
                break;

            case "InterpreteBuilder":
                break;

            default:
                System.out.println("找不到解析字符串后对应的构造器");
                return null;
        }
        return builder;
    }

}
