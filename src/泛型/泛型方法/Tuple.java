package 泛型.泛型方法;

import 泛型.简单泛型.Tuple2;
import 泛型.简单泛型.Tuple3;

/**
 * <p>文件名称: Tuple </p>
 * <p>功能  [功能描述]</p>
 * <p>创建时间：2020/12/1</p>
 *
 * @author qiang.jin
 */
public class Tuple {
    public static <A, B> Tuple2<A, B> tuple(A a, B b) {
        return new Tuple2<>(a, b);
    }

    public static <A, B, C> Tuple3<A, B, C> tuple(A a, B b, C c) {
        return new Tuple3<>(a, b, c);
    }
}
