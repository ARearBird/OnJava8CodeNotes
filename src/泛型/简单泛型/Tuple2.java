package 泛型.简单泛型;

/**
 * <p>文件名称: Tuple2 </p>
 * <p>功能  元组2</p>
 * <p>创建时间：2020/11/30</p>
 *
 * @author qiang.jin
 */
public class Tuple2<A, B> {
    public final A a;
    public final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public String rep() {
        return a + ", " + b;
    }
    @Override
    public String toString() {
        return "(" + rep() + ")";
    }

    public static void main(String[] args) {
        Tuple2<String, Integer> t2 = new Tuple2<String, Integer>("str", 1);
        System.out.print(t2);
    }
}
