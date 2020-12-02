package 泛型.简单泛型;

/**
 * <p>文件名称: LinkedStack </p>
 * <p>功能  堆栈类</p>
 * <p>创建时间：2020/11/30</p>
 *
 * @author qiang.jin
 */
public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;

        Node() { item = null; next = null; }
        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        /**
         * 判断节点是否为尾节点
         */
        boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<>(); // 定义栈顶元素节点

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<String>();
        for (String item : "I am good man".split(" ")) {
            linkedStack.push(item);
        }
        String s;
        while((s = linkedStack.pop()) != null) {
            System.out.println(s);
        }
    }

}
