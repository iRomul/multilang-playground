package io.github.iromul.playground.leetcode.problems.reverse_linked_list;

import io.github.iromul.playground.leetcode.utils.ListNode;
import org.jetbrains.annotations.Contract;

public class SolutionLinearApproach1 {

    @Contract("null -> null")
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }

        return prev;
    }
}
