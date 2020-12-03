package chapter_20_泛型.泛型擦除;

import java.util.*;

/**
 * 泛型擦除引入
 */
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }
}

/**
 * 泛型擦除提升
 */
class Frob {}
class Fnorkle {}
class Quark<Q> {}
class Particle<POSITION, MOMENTUM> {}

class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        Map<Frob, Fnorkle> map = new HashMap<>();
        Quark<Fnorkle> quark = new Quark<>();
        Particle<Long, Double> p = new Particle<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }
}
// Class.getTypeParameters() “返回一个 TypeVariable 对象数组，表示泛型声明中声明的类型参数...” 这暗示你可以发现这些参数类型。
// 但是正如上例中输出所示，你只能看到用作参数占位符的标识符，这并非有用的信息

// 残酷的现实是：在泛型代码内部，无法获取任何有关泛型参数类型的信息!!!
// Java 泛型是使用擦除实现的。这意味着当你在使用泛型时，任何具体的类型信息都被擦除了，你唯一知道的就是你在使用一个对象。
// 因此，List<String> 和 List<Integer> 在运行时实际上是相同的类型。它们都被擦除成原生类型 List

/**
 * 泛型擦除进阶
 */
class HasF {
    public void f() {
        System.out.println("HasF.f()");
    }
}

class Manipulator<T> {
    private T obj;

    Manipulator(T x) {
        obj = x;
    }

    // Error: cannot find symbol: method f():
    public void manipulate() {
        // obg.f(); // 报错，因为Java编译器只知道 obj 是一个对象，其他一无所知
    }
}

class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hf);
        manipulator.manipulate();
    }
}

class Has extends HasF {
    public void ff() {
        System.out.println("sss");
    }
}

// 解决：
// 必须协助泛型类，给定泛型类一个边界，以此告诉编译器只能接受遵循这个边界的类型。
// 这里重用了 extends 关键字。由于有了边界，下面的代码就能通过编译：
class Manipulator2<T extends HasF> {
    private T obj;
    Manipulator2(T x) {
        obj = x;
    }
    public void manipulate() {
        obj.f();
        // obj.ff(); // 对于 “边界” 的子类依旧无法调用。
    }
}

// “泛型类型参数会擦除到它的第一个边界”，T 擦除到了 HasF，就像在类的声明中用 HasF 替换了 T 一样：
class Manipulator3 {
    private HasF obj;
    Manipulator3(HasF x) {
        obj = x;
    }
    public void manipulate() {
        obj.f();
    }
}

// 提出了很重要的一点：
// 泛型只有在类型参数比某个具体类型（以及其子类）更加“泛化”——代码能跨多个类工作时才有用。
// 因此，类型参数和它们在有用的泛型代码中的应用，通常比简单的类替换更加复杂。
// 但是，不能因此认为使用 <T extends HasF> 形式就是有缺陷的。
// 例如，如果某个类有一个获得 T 的方法，那么泛型就有所帮助，因为它们之后将返回确切的类型：
class ReturnGenericType<T extends HasF> {
    private T obj;
    ReturnGenericType(T x) {
        obj = x;
    }
    public T get() {
        return obj;
    }
}
