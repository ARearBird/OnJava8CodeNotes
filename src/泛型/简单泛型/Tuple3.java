package 泛型.简单泛型;

/**
 * <p>项目名称: XNRH</p>
 * <p>文件名称: Tuple3 </p>
 * <p>功能  三元组，其余（）依此类推</p>
 * <p>创建时间：2020/11/30</p>
 * <p>公司信息：浙江创得信息技术股份有限公司</p>
 *
 * @author qiang.jin
 * @version v1.0
 * @update [日期YYYY-MM-DD] [更改人姓名] [变更描述]
 */
public class Tuple3<A, B, C> extends Tuple2<A, B> {
    private C c;

    public Tuple3(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String rep() {
        return super.rep() + ", " + c;
    }

    public static void main(String[] args) {
        Tuple3<String, Integer, Tuple2<String, Double>> t3 = new Tuple3<>("str", 11, new Tuple2<>("tuple2", 10.28));
        System.out.print(t3);
    }
}
