package com.ke.aboutleetcode;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _23 {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length <= 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (ListNode list : lists) {
            while (list != null) {
                pq.add(list.val);
                list = list.next;
            }
        }

        ListNode node = new ListNode();
        ListNode curNode = node;

        while (!pq.isEmpty()) {
            curNode.next = new ListNode(pq.poll());
            curNode = curNode.next;
        }

        return node.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode[] lists = new ListNode[]{node1, node2, node3};
        ListNode newNode = mergeKLists(lists);
        while (newNode != null) {
            System.out.println(newNode.val);
            newNode = newNode.next;
        }
    }

}
