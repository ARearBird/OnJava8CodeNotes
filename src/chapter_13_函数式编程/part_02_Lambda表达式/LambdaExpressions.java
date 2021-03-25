package chapter_13_函数式编程.part_02_Lambda表达式;

/**
 * Lambda表达式的基本使用：(形参列表) -> {方法体;}
 * 从 3 个接口开始，每个接口都有一个（参数数量不同的）方法，以便演示 Lambda 表达式的用法
 */
interface Description {
    String brief();
}

interface Body {
    String detailed(String head);
}

interface Multi {
    String twoArg(String head, Double d);
}

public class LambdaExpressions {
    static Description desc = () -> " Short info"; // 没有参数时，必须使用括号 () 表示空参数列表；方法体只有一行，隐式使用 return 将此行执行结果返回
    static Description moreLines = () -> {
        System.out.println("moreLines");
        return "from moreLines"; // 方法体为多行时，必须需使用 return 显式返回结果
    };
    static Body bod = h -> h + " No Parens!"; // 只有一个参数，可以不用括号 ()；方法体只有一行，可以不用大括号 {}
    static Body bod2 = (h) -> " More details";
    static Multi multi = (h, n) -> h + n;

    public static void main(String[] args) {
        // 使用接口的相同调用，执行不同的方法行为
        System.out.println(desc.brief());
        System.out.println(moreLines.brief());
        System.out.println(bod.detailed("Oh!"));
        System.out.println(bod2.detailed("Hi!"));
        System.out.println(multi.twoArg("Pi! ", 3.1415926));
    }
}
