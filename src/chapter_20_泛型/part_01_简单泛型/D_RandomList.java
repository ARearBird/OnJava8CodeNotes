package chapter_20_泛型.part_01_简单泛型;

import java.util.*;
import java.util.stream.IntStream;

class RandomList<T> extends ArrayList<T> {
    private Random rand = new Random(47);

    public T select() {
        return get(rand.nextInt(size()));
    }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<>();
        Arrays.stream("The quick brown fox jumped over the lazy brown dog".split(" ")).forEach(rs::add);
        IntStream.range(0, 11).forEach(i -> System.out.print(rs.select() + " "));

        // 换一种写法
        System.out.println();
        rs.addAll(Arrays.asList("The quick brown fox jumped over the lazy brown dog".split(" ")));
        for (int i = 0, len = rs.size(); i < len; i++) {
            System.out.print(rs.select() + " ");
        }
    }
}
