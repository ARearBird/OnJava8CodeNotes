package chapter_15_异常.part_01_自定义异常;

/**
 * 当抛出异常后，有几件事会随之发生:
 *      1. 使用 new 在堆上创建异常对象
 *      2. 异常处理机制接管程序
 *      3. 寻找相关的异常处理程序继续执行程序
 *      4.
 *          1）如果异常向上抛出，出现异常的方法就被终止了，抛出异常的剩余代码不会执行
 *          2）如果异常被处理，则剩余代码继续执行
 */

/**
 * 自定义异常
 * 一个简单的demo
 * 说明上述异常流程
 */
class SimpleException extends Exception {}

class SimpleExceptionDemo {
    public void f() throws SimpleException {
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public void f2() throws SimpleException {
        f();
        System.out.println("f2() - 抛出异常的剩余代码");  // f() 过程中抛出异常时，这句将不会执行
    }

    public static void main(String[] args) {
        SimpleExceptionDemo me = new SimpleExceptionDemo();
        try {
            me.f2();
        } catch (SimpleException e) {
            System.out.println("Caught it!");
        }
        System.out.println("main - 抛出异常的剩余代码");
    }
}

/**
 * 创建一个带字符串参数的异常类，并且输出信息到标准错误流
 */
public class MyException extends Exception {
    MyException() {}
    MyException(String msg) { super(msg);}
}
class FullConstructors {
    public static void f() throws MyException {
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }
    public static void g() throws MyException {
        System.out.println("Throwing MyException from g()");
        throw new MyException("Originated in g()");
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (MyException e) {
            e.printStackTrace(System.out); // 信息输出到控制台
            e.printStackTrace(); // 信息输出到标准错误流
        }

        try {
            g();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }
    }
}
