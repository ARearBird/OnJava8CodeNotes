package chapter_20_泛型.part_01_简单泛型;

/**
 * 元组类
 */
class Tuple2<A, B> {
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

class Tuple3<A, B, C> extends Tuple2<A, B> {
    private final C c;

    public Tuple3(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String rep() {
        return super.rep() + ", " + c;
    }

    // 没有覆盖 toString()

    public static void main(String[] args) {
        Tuple3<String, Integer, Tuple2<String, Double>> t3 = new Tuple3<>("str", 11, new Tuple2<>("tuple2", 10.28));
        System.out.print(t3);
    }
}

class Tuple4<A, B, C, D> extends Tuple3<A, B, C> {
    private final D d;

    public Tuple4(A a, B b, C c, D d) {
        super(a, b, c);
        this.d = d;
    }

    @Override
    public String rep() {
        return super.rep() + ", " + d;
    }

    // 没有覆盖 toString()
}

/**
 * 测试类
 */
class Amphibian {}
class Vehicle {}
class TupleTest {
    static Tuple2<String, Integer> f() {
        return new Tuple2<>("hi", 47); // 47 自动装箱为 Integer
    }

    static Tuple3<Amphibian, String, Integer> g() {
        return new Tuple3<>(new Amphibian(), "hi", 47);
    }

    static Tuple4<Vehicle, Amphibian, String, Integer> h() {
        return new Tuple4<>(new Vehicle(), new Amphibian(), "hi", 47);
    }

    public static void main(String[] args) {
        Tuple2<String, Integer> tuple = f();
        System.out.println(tuple);
        // tuple.a = "str"; // Cannot assign a value to final variable 'a' (无法为最终变量“a”赋值)
        System.out.println(g());
        System.out.println(h());
    }
}

/**
 * 泛型方法和元组
 */
class Tuple {
    public static <A, B> Tuple2<A, B> tuple(A a, B b) {
        return new Tuple2<>(a, b);
    }

    public static <A, B, C> Tuple3<A, B, C> tuple(A a, B b, C c) {
        return new Tuple3<>(a, b, c);
    }

    public static <A, B, C, D> Tuple4<A, B, C, D> tuple(A a, B b, C c, D d) {
        return new Tuple4<>(a, b, c, d);
    }
}
class TupleTest2 {
    static Tuple2<String, Integer> f() {
        return Tuple.tuple("hi", 47);
    }

    static Tuple2 f2() {
        return Tuple.tuple("hi", 47);
    }

    static Tuple3<Amphibian, String, Integer> g() {
        return Tuple.tuple(new Amphibian(),"hi", 47);
    }

    static Tuple4<Vehicle, Amphibian, String, Integer> h() {
        return Tuple.tuple(new Vehicle(), new Amphibian(), "hi", 47);
    }

    public static void main(String[] args) {
        Tuple2<String, Integer> tuple = f();
        System.out.println(tuple);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
    }
}
