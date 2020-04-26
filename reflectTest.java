package com.company.Reflect;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**1.获取功能
 *      1.获取成员变量
 *          -Field[] getFields() 获取所有public修饰的全部变量
 *          -Field getField(String name) 获取指定名称public修饰的某一个变量
 *
 *          --Field[] getDeclaredFields() 获取所有成员变量,不论修饰符
 *          --Field[] getDeclaredField(String name) 获取某一个成员变量,不论修饰符
 *      2.获取构造方法
 *           -Constructor<?>[] getConstructors() 获取所有构造方法
 *           -Constructor<T> getConstructor(类<?>...ParameterTypes) 获取某一个构造方法
 *
 *           --Constructor<?>[] getDeclaredConstructors()
 *           --Constructor<?>[] getDeclaredConstructor(类<?>...ParameterTypes)
 *      3.获取成员方法
 *              -Method[] getMethods()
 *              -Method getMethod(String name,类<?>...ParameterTypes)
 *
 *              --Method[] getDeclaredMethods()
 *              --Method getDeclaredMethod(String name,类<?>...ParameterTypes)
 *      4.获取类名
 *              getName()
 *
 *
 * */
public class reflectTest {
    @Test
    public void test1() throws Exception{
        //1.Class.forName("全类名")--->多用于配置文件
        Class cls1 = Class.forName("com.company.Reflect.Person");
        System.out.println(cls1);
        //2.类名.class---->多用于参数传递
        Class cls2 = Person.class;
        System.out.println(cls2);
        //3.对象.getClass---->多用于对象的获取字节码对象
        Person p = new Person();
        Class cls3 =p.getClass();
        System.out.println(cls3);
        System.out.println("--------------");
        System.out.println(cls1==cls2);
        System.out.println(cls2==cls3);
        System.out.println(cls1==cls3);
        /**同一个字节码文件(*.class)再一次程序运行过程中,只会被加载一次,3种方式获取的是同一个*/
    }
    @Test//使用字节码对象
    public void test2() throws Exception{
        /**
         *  ***Field:成员变量
         *  *          1.设置值
         *  *              set(Object o, Object value);
         *  *          2.获取值
         *  *              get(Object o)
         *  *               //私有忽略权限修饰符的安全检查
         *  *                setAccessible(true);//暴力反射//Constructor  , Method 都有
         *  *
         */
        //1.获取Class对象
        Class personClass = Class.forName("com.company.Reflect.Person");
        Person p1 = new Person();
        System.out.println("-Field[] getFields() 获取所有public修饰的全部变量");
        Field[] fields = personClass.getFields();
        for (Field f:fields){
            System.out.println(f);
        }
        System.out.println("---------------------------------------------");

        System.out.println(" -Field getField(String name) 获取指定名称public修饰的某一个变量");
        Field field = personClass.getField("a");
        System.out.println(field);
        field.set(p1,"王杰");
        Object value = field.get(p1);
        System.out.println(value);
        System.out.println("---------------------------------------------");
        System.out.println("--Field[] getDeclaredFields() 获取所有成员变量,不论修饰符 ");
        Field[] fields1 =  personClass.getDeclaredFields();
        for (Field f:fields1){
            System.out.println(f);
        }
        System.out.println("-----------------------------------------------");
        System.out.println(" --Field[] getDeclaredField(String name) 获取某一个成员变量,不论修饰符");
        Field d = personClass.getDeclaredField("d");
        System.out.println(d);
        //私有忽略权限修饰符的安全检查
        d.setAccessible(true);//暴力反射
        Object value1 = d.get(p1);
        System.out.println(value1);
    }
    @Test//获取构造方法
    public void test3() throws Exception {
        /**-Constructor构造方法
         * 1.创建对象
         *        -T newInstance(Object... initargs)
         */
        //1.获取Class对象
        Class personClass = Class.forName("com.company.Reflect.Person");
        Person p1 = new Person();
        //获取构造方法
        Constructor constructor = personClass.getConstructor();
        System.out.println(constructor);
        Object object1 = constructor.newInstance();
        System.out.println(object1);
        System.out.println("--------------------------------------------");
        //创建对象
        Constructor constructor1 = personClass.getConstructor(String.class,String.class);
        System.out.println(constructor1);
        Object object = constructor1.newInstance("王杰","12岁");
        System.out.println(object);

        Object object2 = personClass.newInstance();
        System.out.println(object2);
    }
    @Test//获取成员方法
    public void test4() throws Exception {
        /***
         * Method
         *      执行方法    object invoke(object o)
         */
        //1.获取Class对象
        Class personClass = Class.forName("com.company.Reflect.Person");
        Person p1 = new Person();
        Method method = personClass.getMethod("aaa");
        method.invoke(p1);
        Method method2 = personClass.getMethod("bbb", int.class);
        method2.invoke(p1,100);
        System.out.println("-----------------------");
        //获取所有public修饰方法
        Method[] methods = personClass.getMethods();
        for (Method m:methods){
//            m.setAccessible(true);//暴力反射
            System.out.println(m);
            //获取方法名称
            System.out.println(m.getName());
        }
        //4.获取类名
        System.out.println("------------------------------------------\n"+personClass.getName());
    }


    @Test//实力框架类
    public void test5() throws Exception{
        //创建任意类对象,执行任意类方法

//        Student student = new Student();
//        student.sleep();
        /***
         * 不能改变该类的任意代码,创建类对象,并执行方法
         * 实现:
         *      1.配置文件
         *      2.反射
         * 步骤:
         *      1.需要创建的对象全类名,和执行方法放到配置文件中
         *      2.在程序中加载配置文件
         *      3.使用反射技术加载类文件进内存
         */

        //1.加载配置文件
        //1.1创建Properties对象
        Properties pro = new Properties();
        //1.2加载配置文件
        //1.2.1获取class目录下配置文件
        ClassLoader classLoader = reflectTest.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("com/company/Reflect/pro.properties");
        pro.load(in);

        //2.获取配置文件中配置的定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3.加载该类进内存
        Class cls = Class.forName(className);
        //4.创建对象
        Object o =  cls.newInstance();
        //5.获取方法
        Method method = cls.getMethod(methodName);
        //6.执行方法
        method.invoke(o);

    }
}
