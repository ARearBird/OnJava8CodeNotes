package chapter_20_泛型.part_01_简单泛型;

/**
 * 用链式结构实现自己的堆栈
 */
class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;

        Node() {
            item = null;
            next = null;
        }
        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        // 判断节点是否为尾节点
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
        LinkedStack<String> linkedStack = new LinkedStack<>();
        for (String s : "I am good man".split(" ")) {
            linkedStack.push(s);
        }
        String s;
        while((s = linkedStack.pop()) != null) {
            System.out.println(s);
        }
    }
}
