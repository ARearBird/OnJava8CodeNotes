package chapter_20_泛型.part_02_泛型接口和泛型方法;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 泛型方法
 */
class GenericMethods {
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

// 泛型方法和泛型类型参数
class GG<T> {
    public <U> void f(T u, U uu) {}
    // static 方法无法访问该类的泛型类型参数
    // public static  <U> void f2(T u, U uu) {}
}

/**
 * 泛型方法的使用实例（一）：泛型方法和变长参数
 */
class GenericVarargs {
    // 功能与标准库的 java.util.Arrays.asList() 方法相同
    // @SafeVarargs 注解保证我们不会对变长参数列表进行任何修改
    @SafeVarargs
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for (T item : args) {
            result.add(item);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        System.out.println(ls);
        ls = makeList("ABCDEFGHIGKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}

/**
 * 泛型方法的使用实例（二）：泛型类型参数、泛型方法、泛型接口 Supplier
 */
class BasicSupplier<T> implements Supplier<T> {
    private Class<T> type;

    public BasicSupplier(Class<T> type) {
        this.type = type;
    }

    @Override
    public T get() {
        try {
            // Assumes type is a public class:
            // 假设类型是公共类：
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    // Produce a default Supplier from a type token:
    public static <T> Supplier<T> create(Class<T> type) {
        return new BasicSupplier<>(type);
    }
}
// 创建一个这样的 BasicSupplier 对象，请调用 create() 方法，并将要生成类型的类型令牌传递给它。
// 创建对象的比较：
// BasicSupplier b = BasicSupplier.create(MyType.class);        较简洁的语法
// BasicSupplier b = new BasicSupplier <MyType>(MyType.class);  较笨拙的语法
// 如：
class CountedObject {
    private static long counter = 0;
    private final long id = counter++;

    public long id() {
        return id;
    }

    @Override
    public String toString() {
        return "CountedObject " + id;
    }
}
// CountedObject 类可以跟踪自身创建了多少个实例，并通过 toString() 报告这些实例的数量。
// BasicSupplier 可以轻松地为 CountedObject 创建 Supplier：
class BasicSupplierDemo {
    public static void main(String[] args) {
        Stream.generate(BasicSupplier.create(CountedObject.class))
                .limit(5)
                .forEach(System.out::println);
    }
}
// 泛型方法减少了产生 Supplier 对象所需的代码量。
// Java 泛型强制传递 Class 对象，以便在 create() 方法中将其用于类型推断。

/**
 * 泛型方法的使用实例（三）：泛型和元组，简化元组的使用
 * （详见：part_01_简单泛型.Tuple）
 */

/**
 * 泛型方法的使用实例（四）：一个 Set 工具类
 */

