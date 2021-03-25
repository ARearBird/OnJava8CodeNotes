package test;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.Random;

/**
 * <p>项目名称: XNRH</p>
 * <p>文件名称: test </p>
 * <p>功能  [功能描述]</p>
 * <p>创建时间：2021/1/18</p>
 * <p>公司信息：浙江创得信息技术股份有限公司</p>
 *
 * @author qiang.jin
 * @version v1.0
 * @update [日期YYYY-MM-DD] [更改人姓名] [变更描述]
 */
public class test {
    public static void main(String[] args) {
        boolean b = false;
//        System.out.println(~b);  // Operator '~' cannot be applied to 'boolean'
        System.out.println(b & b);
        System.out.println(Integer.toBinaryString(10));
        System.out.println(3 + 4 + 3 + " " + 3 + (3 + 3));
        int i = 1;
        System.out.println(i = 100);
        short s1 = 1, s2 = 3;
//        short s3 = s1 + s2;   // 这个时候 s1 + s2 的值的类型就变为了 int 类型

        int i1 = 3, j1 = 5;
        for (;i1 > j1;) {    // 条件执行语句为 false
            System.out.println("for i 执行");
        }
        do {
            System.out.println("do while 执行");   // 执行一次
        } while (i1 > j1);   // 条件执行语句为 false

        while (i1 > j1) {   // 条件执行语句为 false
            System.out.println("while 执行");
        }

        // foreach 不会改变内容的，但如果是引用类型数组，则会改变数组的值，这是因为他们引用的是相同的内存单元
        int[] ints = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int ii: ints) {
            ii = 1;
        }
        for (int ii : ints) {
            System.out.print(ii + " ");
        }

        char c = (char) (new Random().nextInt(26) + 'a');
        System.out.println(c);
    }
}
