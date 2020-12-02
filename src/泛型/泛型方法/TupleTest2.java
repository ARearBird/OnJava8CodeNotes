package 泛型.泛型方法;

import 泛型.简单泛型.Tuple2;
import 泛型.简单泛型.Tuple3;

import static 泛型.泛型方法.Tuple.tuple;

/**
 * <p>文件名称: TupleTest2 </p>
 * <p>功能  [功能描述]</p>
 * <p>创建时间：2020/12/1</p>
 *
 * @author qiang.jin
 */
public class TupleTest2 {
    static Tuple2<String, Integer> f() {
        return tuple("hi", 47);
    }

    static Tuple2 f2() {
        return tuple("hi2", 47);
    }

    static Tuple3<Amphibian, String, Integer> g() {
        return tuple(new Amphibian(), "hi g", 47);
    }

    public static void main(String[] args) {
        Tuple2<String, Integer> ttsi = f();
        System.out.println(ttsi);

        System.out.println(f2());
        System.out.println(g());
    }
}

class Amphibian {}
