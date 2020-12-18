package chapter_20_泛型.补偿擦除;

import chapter_20_泛型.Suppliers;
import com.sun.deploy.security.WinDeployNTLMAuthCallback;
import javafx.scene.chart.ScatterChart;

import java.lang.reflect.Array;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

// 因为擦除，将失去执行代码中某些操作的能力：无法在运行时知道确切类型
class Erased<T> {
    private final int SIZE = 100;
    public void f(Object arg) {
        // error: illegal generic type for instanceof
        // 丢失类型信息
        // if (arg instanceof T) {}

        // error: unexpected type
        // 不可实例化对象
        // T var = new T();

        // error: generic array creation
        // 不可创建数组
        // T[] array = new T[SIZE];

        // warning: [unchecked] unchecked cast
        // 不可转型
        // T[] array = (T[]) new Object[SIZE];
    }
}

class Building {}
class House extends Building {}

// 对判断类型参数的补偿：
class ClassTypeCapture<T> {
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));

        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));

        // 不能推断出参数
        // ClassTypeCapture<Building> ctt3 = new ClassTypeCapture<>(House.class);
    }
}

// 对创建实例的补偿：如果使用类型标记，则可以使用 newInstance() 创建该类型的新对象
class ClassAsFactory<T> implements Supplier<T> {
    Class<T> kind;

    ClassAsFactory(Class<T> kind) {
        this.kind = kind;
    }

    @Override
    public T get() {
        try {
            return kind.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

class Employee {
    @Override
    public String toString() {
        return "Employee{}";
    }
}

class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        System.out.println(fe.get());

        ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
        try {
            // 这是因为 Integer 没有无参构造函数，ClassAsFactory<Integer> 会失败
            // 抛出：java.lang.InstantiationException
            System.out.println(fi.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

// 创建工厂的两种不同方法：
// 1.
class IntegerFactory implements Supplier<Integer> {
    private int i;

    @Override
    public Integer get() {
        return ++i;
    }
}
// 2.
class Widget {
    private int id;

    Widget(int n) {
        id = n;
    }

    @Override
    public String toString() {
        return "Widget{" + "id=" + id + '}';
    }

    public static class Factory implements Supplier<Widget> {
        private int i = 0;
        @Override
        public Widget get() {
            return new Widget(++i);
        }
    }
}

class Fudge {
    private static int count = 1;
    private int n = count++;

    @Override
    public String toString() {
        return "Fudge{" + "n=" + n + '}';
    }
}

class Foo2<T> {
    private List<T> x = new ArrayList<>();

    Foo2(Supplier<T> factory) {
        Suppliers.fill(x, factory, 5);
    }

    @Override
    public String toString() {
        return "Foo2{" + "x=" + x + '}';
    }
}

class FactoryConstraint {
    public static void main(String[] args) {
        System.out.println(new Foo2<>(new IntegerFactory()));
        System.out.println(new Foo2<>(new Widget.Factory()));
        System.out.println(new Foo2<>(Fudge::new));
    }
}

// 使用模板方法设计模式
abstract class GenericWithCreate<T> {
    final T element;

    GenericWithCreate() {
        element = create();
    }

    abstract T create();
}

class X {}

class XCreate extends GenericWithCreate<X> {
    @Override
    X create() {
        return new X();
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

class CreateGeneric {
    public static void main(String[] args) {
        XCreate xc = new XCreate();
        xc.f();
    }
}


// 补偿数组：通用解决方案是在试图创建泛型数组的时候使用 ArrayList
class ListOfGenerics<T> {
    private List<T> array = new ArrayList<>();

    public void add(T item) {
        array.add(item);
    }

    public T get(int index) {
        return array.get(index);
    }
}

// 创建泛型类型数组。通过使用编译器满意的方式定义对数组的通用引用：
class Generic<T> {}

class ArrayOfGenericReference {
    static Generic<Integer>[] gia;
}

// 由于所有数组，无论它们持有什么类型，都具有相同的结构（每个数组插槽的大小和数组布局），
// 因此似乎可以创建一个 Object 数组并将其转换为所需的数组类型
class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            gia = (Generic<Integer>[]) new Object[SIZE];
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
        // Runtime type is the raw (erased) type:
        gia = (Generic<Integer>[]) new Generic[SIZE];
        gia[0] = new Generic<>();
        gia[1] = new Generic<Integer>();
//        gia[2] = new Object();
        gia[2] = (Generic<Integer>) new Object();
        // 编译时发现类型不匹配:
//        gia[3] = new Generic<Double>();
    }
}

// 一个包装数组的简单泛型包装器:
class GenericArray<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArray(int sz) {
        array = (T[]) new Object[sz];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArray<Integer> gai = new GenericArray<>(10);
        try {
            Integer[] ia = gai.rep();
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
        // This is OK:
        Object[] oa = gai.rep();
    }
}

// 在集合中使用 Object[]，并在使用数组元素时向 T 添加强制类型转换：
class GenericArray2<T> {
    private Object[] array;

    public GenericArray2(int sz) {
        array = new Object[sz];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep() {
        return (T[]) array; // Unchecked cast
    }

    public static void main(String[] args) {
        GenericArray2<Integer> gai = new GenericArray2<>(10);
        // 初始化
        for (int i = 0; i < 10; i++) {
            gai.put(i, i);
        }
        // 遍历
        for (int i = 0; i < 10; i++) {
            System.out.println(gai.get(i) + " ");
        }
        System.out.println();
        try {
            // 类型转换异常，因为 “擦除”
            Integer[] ia = gai.rep();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// 2.0 版，传入类型标记：
class GenericArrayWithTypeToken<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int sz) {
        // 类型标记 Class<T> 被传递到构造函数中以从擦除中恢复
        array = (T[]) Array.newInstance(type, sz);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class, 10);
        Integer[] ia = gai.rep();
        System.out.println(Arrays.toString(ia));
    }
}


