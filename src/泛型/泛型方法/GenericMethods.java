package 泛型.泛型方法;

import sun.net.www.content.text.Generic;

/**
 * <p>文件名称: GenericMethods </p>
 * <p>功能  [功能描述]</p>
 * <p>创建时间：2020/12/1</p>
 *
 * @author qiang.jin
 */
public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('1');
        gm.f(true);
        gm.f("");
        gm.f(gm);
    }
}

// static 方法无法访问该类的泛型类型参数
class GG<T> {
    public <U> void f(T u, U uu) {

    }
}
