package com.ke.aboutleetcode;

import java.util.LinkedList;
import java.util.Stack;

public class _155 {
    class MinStack {
        LinkedList<Node> nodes;

        /** initialize your data structure here. */
        public MinStack() {
            nodes = new LinkedList<>();
        }

        public void push(int x) {
           nodes.add(new Node(x, nodes.size() == 0 ? x : Math.min(nodes.getLast().min, x)));
        }

        public void pop() {
            if (nodes.size() > 0) {
                nodes.removeLast();
            }
        }

        public int top() {
            return nodes.getLast().val;
        }

        public int getMin() {
            return nodes.getLast().min;
        }
    }

    class Node {
        int val;
        int min;

        Node() {

        }

        Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new _155().new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
