package chapter_06_初始化和清理.part_01_初始化;

/**
 * <p>项目名称: on_java_8</p>
 * <p>文件名称: Initialization </p>
 * <p>功能  初始化demo</p>
 * <p>创建时间：2021/1/29</p>
 *
 * @author qiang.jin
 * @version v1.0
 */
public class Initialization {
    int k;
    int i = f();
    int j = g(i);

    // 错误的格式
//    int jj = g(ii); // Illegal forward reference 非法的向前引用
//    int ii = f();



    int f() {
        return 1;
    }
    int g(int i) {
        return i;
    }

}


class Bowl {
    Bowl() {
        System.out.println("Bowl()");
    }

    Bowl(int i) {
        System.out.println("Bowl(" + i + ")");
    }

    void f1(int i) {
        System.out.println("f1(" + i + ")");
    }
}

class Table {
    static Bowl bowl1;

    Table() {
        System.out.println("Table()");
        bowl2.f1(1);
    }

    static Bowl bowl2 = new Bowl(2);
}

class StaticInitialization {
    public static void main(String[] args) {
        System.out.println(Table.bowl1);
    }
}







class Cup {
    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;

    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }

    Cups() {
        System.out.println("Cups()");
    }
}

class ExplicitStatic {
    public static void main(String[] args) {
        System.out.println("Inside main()");
        Cups.cup1.f(99); // [1]
    }

    // static Cups cups1 = new Cups(); // [2]
    // static Cups cups2 = new Cups(); // [2]
}