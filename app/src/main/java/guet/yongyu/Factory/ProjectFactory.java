package guet.yongyu.Factory;

import guet.yongyu.Impl.Project;
import guet.yongyu.Utils.PropUtil;

import java.lang.reflect.InvocationTargetException;

public class ProjectFactory {
    private static ProjectFactory factory;

    /**
     * 单例类，私有化构造器
     */
    private ProjectFactory(){}

    /**
     * 返回工厂实例，懒汉模式会出现面临多线程问题，所以这里使用异步加锁解决
     * @return
     */
    public static ProjectFactory getInstance(){
        synchronized (BuilderFactory.class){
            if(factory == null)
                factory = new ProjectFactory();
        }
        return factory;
    }

    public Project getProject(String path,String ext){
        Project project = null;

        /**
         * 读取project.properties配置为文件
         */
        String value = PropUtil.getParameterOfProject(ext);
        if(value == null){
            System.out.println("系统没有对应类型的项目");
            return null;
        }

        /**
         * 拼接各项目类的前面的包名
         */
        String packet = "guet.yongyu.Specify.";

        /**
         * 利用反射技术实现项目类对象的创建，创建需要传入参数,所以使用反射技术中的有参构造进行创建对象
         */
        try {
             Class clazz = Class.forName(packet + value);
             project = (Project) clazz.getDeclaredConstructor(
                     String.class,String.class).newInstance(path,ext);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return project;
    }
}
