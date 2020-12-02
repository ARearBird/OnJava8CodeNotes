package 泛型.简单泛型;

/**
 * <p>文件名称: Holder1 </p>
 * <p>功能  只能持有单个对象的类</p>
 * <p>创建时间：2020/11/27</p>
 *
 * @author qiang.jin
 */
class Animal {}

public class Holder1 {
    private Animal animal;

    public Holder1(Animal animal) {
        this.animal = animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public Animal getAnimal() {
        return animal;
    }

    public static void main(String[] args) {
        Holder1 h1 = new Holder1(new Animal());
        System.out.println(h1.getAnimal());
        /**
         * h1只能拥有 Animal 一个类型的对象
         */
    }
}
