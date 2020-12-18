package chapter_20_泛型.通配符;

import java.util.ArrayList;
import java.util.List;

class A {
    private List li;

    public List getLi() {
        return li;
    }
}
class B extends A {}

class M {
    public static void main(String[] args) {
        List<A> la = new ArrayList<>();
        // List<A> 可以存放 A 及 A 的子类型
        la.add(new B());
        // 但是 List<A> 不可以存放 List<B>
        // la = new ArrayList<B>(); // Compile Error
    }
}

class GenericsAndCovariance {
    public static void main(String[] args) {

    }
}

