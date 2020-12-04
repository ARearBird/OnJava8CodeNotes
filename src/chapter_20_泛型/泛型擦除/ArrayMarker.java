package chapter_20_泛型.泛型擦除;

import chapter_20_泛型.Suppliers;

import javax.jnlp.ClipboardService;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 擦除-边界处的动作
 */

class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        // 对于泛型中创建数组，使用 Array.newInstance() 是推荐的方式：
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<Integer> stringMaker = new ArrayMaker<>(Integer.class);
        Integer[] stringArray = stringMaker.create(9);
        System.out.println(Arrays.toString(stringArray));
        System.out.println(Arrays.toString((Integer[]) Array.newInstance(Integer.class, 9)));
    }
}
// 即使 kind 被存储为 Class<T>，擦除也意味着它实际被存储为没有任何参数的 Class。
// 因此，当你在使用它时，例如创建数组，Array.newInstance() 实际上并未拥有 kind 所蕴含的类型信息。
// 所以它不会产生具体的结果，因而必须转型。

// 如果创建一个集合而不是数组，情况就不同了：
class ListMaker<T> {
    List<T> create() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        ListMaker<String> stringMaker = new ListMaker<>();
        List<String> stringList = stringMaker.create();
        System.out.println(stringList);
    }
}

// 在创建 List 的同时向其中放入一些对象：
class FilledList<T> extends ArrayList<T> {
    FilledList(Supplier<T> gen, int size) {
        Suppliers.fill(this, gen, size);
    }

    public FilledList(T t, int size) {
        for (int i = 0; i < size; i++) {
            this.add(t);
        }
    }

    public static void main(String[] args) {
        List<String> list = new FilledList<>("hello", 4);
        System.out.println(list);
        // Supplier version:
        List<Integer> ilist = new FilledList<>(() -> 47, 4);
        System.out.println(ilist);
    }
}
// 另一个版本：
class ListMaker2<T> {
    List<T> list;

    ListMaker2(int size, T t) {
        list = create();
        for (int i = 0; i < size; i++) {
            list.add(t);
        }
    }

    List<T> create() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        ListMaker2<String> stringMaker = new ListMaker2<>(4, "hi");
        System.out.println(stringMaker.list);
    }
}
// 即使编译器无法得知 add() 中的 T 的任何信息，但它仍可以在编译期确保你放入 FilledList 中的对象是 T 类型。
// 因此，即使擦除移除了方法或类中的实际类型的信息，编译器仍可以确保方法或类中使用的类型的内部一致性。

// 因为擦除移除了方法体中的类型信息，所以在运行时的问题就是边界：即对象进入和离开方法的地点。
// 这些正是编译器在编译期执行类型检查并插入转型代码的地点。
