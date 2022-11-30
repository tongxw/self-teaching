package com.coding_challenge;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class PStack {
    ListNode node;
    int size;

    public PStack(ListNode node, int size) {
        this.node = node;
        this.size = size;
    }

    public PStack() {
        this(null, 0);
    }

    public PStack push(int val) {
        // create list node, then list.next = this.node
        ListNode next = new ListNode(val, this.node);

        // return this list with PStack (head of the list)
        return new PStack(next, size + 1);
    }

    public PStack pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("stack is empty");
        }

        return new PStack(node.next, size - 1);
    }

    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("stack is empty");
        }
        // return top;
        return node.val;
    }

    public int size() {
        return size;
    }

    public PStack reverse() throws Exception {
        if (size == 0) {
            return null;
        }

        PStack ret = new PStack();
        PStack cur = this;
        while (!cur.isEmpty()) {
            ret = ret.push(cur.peek());
            cur = cur.pop();
        }

        return ret;
    }

    public  boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "stack is empty.";
        } else {
            String ret = "stack size: " + size + ", top->bot: ";
            ListNode cur = node;
            while (cur != null) {
                ret += cur.val + "->";
                cur = cur.next;
            }

            return ret;
        }
    }
}

class PStackTest {
    public static void test() {
        try {
            PStack stack0 = new PStack();
            PStack stack1 = stack0.push(1);
            System.out.println(stack1);
            PStack stack2 = stack1.push(2);
            System.out.println(stack2);
            PStack stack3 = stack2.push(3);
            System.out.println(stack3);

            System.out.println(stack3.pop());

            System.out.println(stack3.reverse());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
