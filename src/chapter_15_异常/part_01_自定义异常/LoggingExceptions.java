package chapter_15_异常.part_01_自定义异常;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * 使用日志记录异常
 * 可以在定义异常类中记录日志，也可以在捕获异常的时候记录日志（推荐！）
 * 但更常见的情形是我们需要捕获和记录其他人编写的异常（这个里面可能没有记录日志）
 */

/**
 * 在异常类中记录日志，好处是不需要客户端程序员的干预就可自动运行
 */
class LoggingException extends Exception {
    // 创建了一个 String 参数相关联的 Logger 对象（通常与错误相关的包名和类名）,这个 Logger 对象会将其输出发送到 System.err
    private static Logger logger = Logger.getLogger("LoggingException");
    LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}
public class LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.out.println("Caught " + e);
        }
    }
}

/**
 * 在捕获异常的时候记录日志
 */
class LoggingExceptions2 {
    private static Logger logger = Logger.getLogger("LoggingExceptions2");
    static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            logException(e);
        }
    }
}