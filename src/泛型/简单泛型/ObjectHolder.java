package 泛型.简单泛型;

/**
 * <p>文件名称: ObjectHolder </p>
 * <p>功能  可以持有多个对象的类</p>
 * <p>创建时间：2020/11/30</p>
 *
 * @author qiang.jin
 */
public class ObjectHolder {
    private Object object;
    public ObjectHolder(Object o) {
        this.object = o;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    public Object getObject() {
        return object;
    }

    public static void main(String[] args) {
        ObjectHolder oh = new ObjectHolder(new String("str"));
        String str = (String) oh.getObject();
        System.out.println(str);
        oh.setObject(11);
        Integer i = (Integer) oh.getObject();
        System.out.println(i);
        /**
         * oh 先后拥有了 3 个数据类型不同的对象
         */
    }
}
