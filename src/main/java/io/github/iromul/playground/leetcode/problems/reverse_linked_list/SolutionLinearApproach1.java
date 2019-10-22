package io.github.iromul.playground.leetcode.problems.reverse_linked_list;

import io.github.iromul.playground.leetcode.utils.ListNode;
import org.jetbrains.annotations.Contract;

public class SolutionLinearApproach1 {

    @Contract("null -> null")
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode a = head;
        ListNode b = a.next;
        ListNode ahead;

        a.next = null;

        do {
            ahead = b.next;

            b.next = a;
            a = b;

            b = ahead;
        } while (ahead != null);

        return a;
    }
}
