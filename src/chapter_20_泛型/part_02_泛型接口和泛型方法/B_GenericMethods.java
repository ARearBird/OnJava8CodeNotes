package chapter_20_泛型.part_02_泛型接口和泛型方法;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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
 * 泛型方法的使用实例（四）：一个 Set 工具类，代表数学集合操作
 */
class Sets {
    // 并集
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    // 交集
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    // 差集
    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
        Set<T> result = new HashSet<>(superset);
        result.removeAll(subset);
        return result;
    }

    // Reflexive--everything not in the intersection:
    // 反射性——所有不在交集的东西:
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}
// Sets 工具类的应用（一）
enum Watercolors {
    ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW,
    ORANGE, BRILLIANT_RED, CRIMSON, MAGENTA,
    ROSE_MADDER, VIOLET, CERULEAN_BLUE_HUE,
    PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE,
    PERMANENT_GREEN, VIRIDIAN_HUE, SAP_GREEN,
    YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
    BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
}
class WatercolorSets {
    public static void main(String[] args) {
        Set<Watercolors> set1 = EnumSet.range(Watercolors.BRILLIANT_RED, Watercolors.VIRIDIAN_HUE);
        Set<Watercolors> set2 = EnumSet.range(Watercolors.CERULEAN_BLUE_HUE, Watercolors.BURNT_UMBER);
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2 + "\n");
        System.out.println("并集union(set1, set2): " + Sets.union(set1, set2) + "\n");
        System.out.println("交集intersection(set1, set2): " + Sets.intersection(set1, set2) + "\n");
        System.out.println("差集difference(set1, set2): " + Sets.difference(set1, set2) + "\n");
        System.out.println("补集complement(set1, set2)" + Sets.complement(set1, set2) + "\n");
        System.out.println();
    }
}
// Sets 工具类的应用（二）：Sets.difference() 方法来展示 java.util 包中各种 Collection 和 Map 类之间的方法差异
class CollectionMethodDifferences {
    static Set<String> methodSet(Class<?> type) {
        return Arrays.stream(type.getMethods())
                .map(Method::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    static void interfaces(Class<?> type) {
        System.out.print("Interfaces in " + type.getSimpleName() + ": ");
        System.out.println(
                Arrays.stream(type.getInterfaces())
                        .map(Class::getSimpleName)
                        .collect(Collectors.toList())
        );
    }

    static Set<String> object = methodSet(Object.class);

    static {
        object.add("clone");
    }

    static void difference(Class<?> superset, Class<?> subset) {
        System.out.print(superset.getSimpleName() +
                " extends " + subset.getSimpleName() +
                ", adds: ");
        Set<String> comp = Sets.difference(
                methodSet(superset), methodSet(subset));
        comp.removeAll(object); // Ignore 'Object' methods
        System.out.println(comp);
        interfaces(superset);
    }

    public static void main(String[] args) {
        System.out.println("Collection: " + methodSet(Collection.class));
        interfaces(Collection.class);
        difference(Set.class, Collection.class);
        difference(HashSet.class, Set.class);
        difference(LinkedHashSet.class, HashSet.class);
        difference(TreeSet.class, Set.class);
        difference(List.class, Collection.class);
        difference(ArrayList.class, List.class);
        difference(LinkedList.class, List.class);
        difference(Queue.class, Collection.class);
        difference(PriorityQueue.class, Queue.class);
        System.out.println("Map: " + methodSet(Map.class));
        difference(HashMap.class, Map.class);
        difference(LinkedHashMap.class, HashMap.class);
        difference(SortedMap.class, Map.class);
        difference(TreeMap.class, Map.class);
    }
}
