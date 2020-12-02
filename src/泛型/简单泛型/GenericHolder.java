package 泛型.简单泛型;

import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;

/**
 * <p>文件名称: GenericHolder </p>
 * <p>功能  使用泛型”占位“</p>
 * <p>创建时间：2020/11/30</p>
 *
 * @author qiang.jin
 */
public class GenericHolder<T> {
    private T a;
    public GenericHolder() {}

    public void set(T a) {
        this.a = a;
    }
    public T get() {
        return  a;
    }

    public static void main(String[] args) {
        GenericHolder<String> g = new GenericHolder<String>();
        g.set("str");   // 此处会有数据类型校验。”g.set(11);“ 报错，因为数据类型不匹配
        String s = g.get();     // 也不需要类型转换
        System.out.print(s);

        GenericHolder<Integer> gInt = new GenericHolder<Integer>();
        gInt.set(11);
        Integer i = gInt.get();
        System.out.print(i);

        // GenericHolder<int> gInt = new GenericHolder<int>();   // 报错 ”Type argument cannot be of primitive type（类型参数不能是原始类型）“
    }
}
