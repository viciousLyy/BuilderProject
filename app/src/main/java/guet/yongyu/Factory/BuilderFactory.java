package guet.yongyu.Factory;

import guet.yongyu.Builder.Builder;
import guet.yongyu.Builder.CompileBuilder;
import guet.yongyu.Builder.InterpreteBuilder;
import guet.yongyu.Builder.MixedBuilder;
import guet.yongyu.Impl.Interpreter;
import guet.yongyu.Impl.Compiler;
import guet.yongyu.MyException.CompilerException;
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
     * 使用同步锁 synchronized (Singleton.class) 防止多线程同时进入造成 instance 被多次实例化。
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
    public Builder getProjectBuilder(String srcExt) {
        Builder builder = null;
        /**
         * 根据后缀名读取到配置文件中的相应参数
         */
        String value = PropUtil.getParameterOfCompiler(srcExt);
        if(value == null){
            System.out.println("找不到该参数对应的构建器");
            return null;
        }
        /**
         * 将读取到的参数进行字符串分割
         */
        String[] builders = ListUtil.strSplit(value);

        /**
         * 各个实现类的具体包名
         */
        String pathOfSpecify = "guet.yongyu.Specify.";

        /**
         * 根据包名参数最后的一个值进行判断，该后缀名对应的构建器类型
         */
        switch(builders[builders.length-1]){
            case "CompileBuilder":
                Compiler compiler = null;
                Interpreter interpreter = null;
                try {
                    compiler = (Compiler) Class.forName(pathOfSpecify+builders[1]).newInstance();
                    interpreter = (Interpreter) Class.forName(pathOfSpecify+builders[3]).newInstance();
                    builder  = new CompileBuilder(compiler,interpreter);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case "MixedBuilder":
                Compiler compiler2 = null;
                Interpreter interpreter2 = null;
                try {
                    compiler2 = (Compiler) Class.forName(pathOfSpecify+builders[1]).newInstance();
                    interpreter2 = (Interpreter) Class.forName(pathOfSpecify+builders[3]).newInstance();
                    builder = new MixedBuilder(compiler2,interpreter2);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;

            case "InterpreteBuilder":
                Interpreter interpreter3 = null;
                try {
                    interpreter3 = (Interpreter) Class.forName(pathOfSpecify+builders[1]).newInstance();
                    builder = new InterpreteBuilder(interpreter3);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("找不到解析字符串后对应的构造器");
                try {
                    throw new CompilerException("找不到解析字符串后对应的构造器");
                } catch (CompilerException e) {
                    e.printStackTrace();
                }
                return null;
        }
        return builder;
    }

}
