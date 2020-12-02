package 泛型.泛型方法;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件名称: GenericVarargs </p>
 * <p>功能  [功能描述]</p>
 * <p>创建时间：2020/12/1</p>
 *
 * @author qiang.jin
 */
public class GenericVarargs {
    @SafeVarargs
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for (T item : args) {
            result.add(item);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        System.out.println(ls);
        ls = makeList("ABCDEFGHIGKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}
