package chapter_20_泛型.part_01_简单泛型;

class Automobile {}

// 一个只能持有单个对象的类。这个类可以明确指定其持有的对象的类型：
class Holder {
    private Automobile a;

    public Holder(Automobile a) { this.a = a; }

    public void set(Automobile a) { this.a = a; }
    public Automobile get() { return a; }

    public static void main(String[] args) {
        Holder h1 = new Holder(new Automobile());
        System.out.println(h1.get());

        // h1只能拥有 Animal 一个类型的对象
        // h1.set("str");  // 报错
    }
}

// 上面一个类的复用性不高（因为它无法持有其他类型的对象），
// Java 5 之前，可以使用 Object 来提升这个类的复用性
class ObjectHolder {
    private Object object;
    public ObjectHolder(Object o) {
        this.object = o;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    public Object getObject() {
        return object;
    }

    public static void main(String[] args) {
        ObjectHolder oh = new ObjectHolder(new String("str"));
        String str = (String) oh.getObject();
        System.out.println(str);
        oh.setObject(11);   // 自动装箱为 Integer
        Integer i = (Integer) oh.getObject();
        System.out.println(i);
        /**
         * oh 先后拥有了 3 个数据类型不同的对象
         */
    }
}

// 一个集合中存储多种不同类型的对象的情况很少见，通常而言，我们只会用集合存储同一种类型的对象。
// 因此，与其使用 Object ，我们更希望先指定一个类型占位符，稍后再决定具体使用什么类型.
// 要达到这个目的：
//      1. 需要使用类型参数；
//      2. 然后在使用这个类时，再用实际的类型替换此类型参数。
class GenericHolder<T> {
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
        g.set("str");   // 此处会有数据类型校验。
        // g.set(11);   // 报错，因为数据类型不匹配
        String s = g.get();     // 也不需要类型转换
        System.out.print(s);

        GenericHolder<Integer> gInt = new GenericHolder<Integer>();
        gInt.set(11);
        Integer i = gInt.get();
        System.out.print(i);

        // 报错 ”Type argument cannot be of primitive type（类型参数不能是原始类型）“
        // GenericHolder<int> gInt = new GenericHolder<int>();

        // Java 7 新的简写语法：钻石语法、菱形语法
        GenericHolder<Integer> g2 = new GenericHolder<>();
        g2.set(11);
    }
}
