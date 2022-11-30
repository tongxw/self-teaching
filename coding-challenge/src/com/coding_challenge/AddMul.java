package com.coding_challenge;

import java.util.*;

public class AddMul {

    public static int eval(String s) {
        String[] tokens = s.split("\\s+");
        // s(i) = '(' => push to stack
        // s(i) = add, num => push to stack
        // s(i) = ')'
        //    1. pop the stack util '(', add to the list
        //    2 the last one must be 'add' or 'mul'
        //    3. pop the '('
        //    4. add or mul all numbers based on 'add' or 'mul'
        //    5. push the result back to stack

        //  return the top of the stack


        Deque<String> stack = new LinkedList<>();
        for (String token : tokens) {
            if (!token.equals(")")) {
                stack.push(token);
            } else {
                List<String> list = new ArrayList<>();
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                stack.pop();
                if (list.size() < 2) { // at least there is one number besides "Add" or "Mul"
                    continue;
                }
                String op = list.get(list.size() - 1);
                if (op.equals("add")) {
                    int res = 0;
                    for (int i=0; i<list.size()-1; i++) {
                        res += Integer.parseInt(list.get(i));
                    }
                    stack.push(String.valueOf(res));
                } else {
                    int res = 1;
                    for (int i=0; i<list.size()-1; i++) {
                        res *= Integer.parseInt(list.get(i));
                    }
                    stack.push(String.valueOf(res));
                }
            }
        }

        return stack.isEmpty() ? 0 : Integer.parseInt(stack.pop());
    }

    public static void test() {
        String s1 = "( )";
        String s2 = "( and )";
        String s3 = "( mul )";
        String s4 = "( add 1 2 ( mul 3 4 5 ) 6 )";

        System.out.println(eval(s1));
        System.out.println(eval(s2));
        System.out.println(eval(s3));
        System.out.println(eval(s4));

    }
}
