package chapter_13_函数式编程.part_01_新旧对比;

/**
 * 使用 匿名内部类、Lambda表达式、方法引用 来实现控制方法的不同行为
 */
interface Strategy {
    String approach(String msg);
}

class Soft implements Strategy {
    @Override
    public String approach(String msg) {
        return msg.toLowerCase() + "?";
    }
}

class Unrelated {
    static String twice(String msg) {
        return msg + " " + msg;
    }
}

public class Strategize {
    Strategy strategy;
    String msg;
    Strategize(String msg) {
        strategy = new Soft(); // 使用 Soft 作为默认策略
        this.msg = msg;
    }

    void communicate() {
        System.out.println(strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Strategy[] strategies = {
                // 匿名内部类
                new Strategy() {
                    @Override
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },
                // Lambda表达式
                msg -> msg.substring(0, 5),
                // 方法引用
                Unrelated::twice
        };
        Strategize s = new Strategize("Hello there");
        s.communicate();
        // 遍历 Strategy 数组，通过 changeStrategy() 方法将每个 strategy 传入变量 s 中（传递的是"行为"）
        for (Strategy newStrategy : strategies) {
            s.changeStrategy(newStrategy);
            s.communicate();
        }
    }
}