package chapter_22_枚举.初印象;

import java.util.Arrays;

/**
 * <p>项目名称: XNRH</p>
 * <p>文件名称: demo_01 </p>
 * <p>功能  [功能描述]</p>
 * <p>创建时间：2020/12/18</p>
 * <p>公司信息：浙江创得信息技术股份有限公司</p>
 *
 * @author qiang.jin
 * @version v1.0
 * @update [日期YYYY-MM-DD] [更改人姓名] [变更描述]
 */
enum Spiciness {
    NOT, MILD, MEDIUN, HOT, FLAMING
}

class SimpleEnumUse {
    public static void main(String[] args) {
        // 枚举类型的枚举值 可赋值给 枚举类型变量
        Spiciness howHot = Spiciness.MEDIUN;
        System.out.println(howHot);
    }
}

class EnumOrder {
    public static void main(String[] args) {
        for (Spiciness s : Spiciness.values()) {
            System.out.println(s + ", ordinal " + s.ordinal() + ", name " + s.name());
        }

        // 按照 enum 常量的声明顺序，生成这些常量值构成的数组
        Spiciness[] spicinesses = Spiciness.values();
        System.out.println(Arrays.toString(spicinesses));
    }
}

class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

    public void describe() {
        System.out.print("This burrito is ");
        switch (degree) {
            case MEDIUN:
                System.out.println("B");
                break;
            case HOT:
                System.out.println("A");
                break;
            case NOT:
            case MILD:
            case FLAMING:
            default:
                System.out.println("maybe too hot.");
        }
    }

    public static void main(String[] args) {
        Burrito plain = new Burrito(Spiciness.NOT), greenChile = new Burrito(Spiciness.MEDIUN),
                jalap = new Burrito(Spiciness.MILD);
        plain.describe();
        greenChile.describe();
        jalap.describe();

    }
}
