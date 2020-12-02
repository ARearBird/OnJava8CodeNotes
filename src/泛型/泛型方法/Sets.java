package 泛型.泛型方法;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static 泛型.泛型方法.Sets.*;

/**
 * <p>文件名称: Sets </p>
 * <p>功能  由 Set 表示的数学关系</p>
 * <p>创建时间：2020/12/1</p>
 *
 * @author qiang.jin
 */
public class Sets {
    // 并集
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    // 交集
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    // 差集
    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
        Set<T> result = new HashSet<>(superset);
        result.removeAll(subset);
        return result;
    }

    // Reflexive--everything not in the intersection:
    // 反射性——所有不在交集的东西:
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }
}

/**
 * Sets 的应用（一）
 */
enum Watercolors {
    ZINC, LEMON_YELLOW, MEDIUM_YELLOW, DEEP_YELLOW,
    ORANGE, BRILLIANT_RED, CRIMSON, MAGENTA,
    ROSE_MADDER, VIOLET, CERULEAN_BLUE_HUE,
    PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE,
    PERMANENT_GREEN, VIRIDIAN_HUE, SAP_GREEN,
    YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
    BURNT_UMBER, PAYNES_GRAY, IVORY_BLACK
}

class WatercolorSets {
    public static void main(String[] args) {
        Set<Watercolors> set1 = EnumSet.range(Watercolors.BRILLIANT_RED, Watercolors.VIRIDIAN_HUE);
        Set<Watercolors> set2 = EnumSet.range(Watercolors.CERULEAN_BLUE_HUE, Watercolors.BURNT_UMBER);
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2 + "\n");
        System.out.println("并集union(set1, set2): " + union(set1, set2) + "\n");
        System.out.println("交集intersection(set1, set2): " + intersection(set1, set2) + "\n");
        System.out.println("差集difference(set1, set2): " + difference(set1, set2) + "\n");
        System.out.println("补集complement(set1, set2)" + complement(set1, set2) + "\n");
        System.out.println();
        }
    }
}

/**
 * Sets 的应用（二）
 */
class CollectionMethodDifferences {
    static Set<String> methodSet(Class<?> type) {
        return Arrays.stream(type.getMethods())
                .map(Method::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    static void interfaces(Class<?> type) {
        System.out.print("Interfaces in " + type.getSimpleName() + ": ");
        System.out.println(Arrays.stream(type.getInterfaces()).map());
    }
}
