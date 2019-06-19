package classloaderTest;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/6/3 20:23
 **/
public class classLoaderTest加载 {
    public static void main(String[] args) throws Exception {
        load();
    }

    /**
     *
     * @throws Exception
     */
    private static void load() throws Exception {
        Class<A> classA = (Class<A>) Class.forName("classloaderTest.A");
        A instanceA = classA.newInstance();
        instanceA.print();
    }

    public static void printClassName() {
        ClassLoader c = classLoaderTest加载.class.getClassLoader();  //获取Test类的类加载器
        System.out.println(c);
        ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器
        System.out.println(c1);
        ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器
        System.out.println(c2);
    }
}











