package io.github.iromul.playground.leetcode.problems.reverse_linked_list;

import io.github.iromul.playground.leetcode.utils.ListNode;
import org.junit.jupiter.api.Test;

import static io.github.iromul.playground.leetcode.utils.LinkedListAssert.assertThat;

class SolutionLinearApproach1Test {

    @Test
    void no_elements() {
        ListNode head = ListNode.of();

        ListNode reversed = SolutionLinearApproach1.reverseList(head);

        assertThat(reversed).isNull();
    }

    @Test
    void one_element() {
        ListNode head = ListNode.of(1);

        ListNode reversed = SolutionLinearApproach1.reverseList(head);

        assertThat(reversed).containsExactly(1);
    }

    @Test
    void two_elements() {
        ListNode head = ListNode.of(1, 2);

        ListNode reversed = SolutionLinearApproach1.reverseList(head);

        assertThat(reversed).containsExactly(2, 1);
    }

    @Test
    void three_elements() {
        ListNode head = ListNode.of(1, 2, 3);

        ListNode reversed = SolutionLinearApproach1.reverseList(head);

        assertThat(reversed).containsExactly(3, 2, 1);
    }

    @Test
    void four_elements() {
        ListNode head = ListNode.of(1, 2, 3, 4);

        ListNode reversed = SolutionLinearApproach1.reverseList(head);

        assertThat(reversed).containsExactly(4, 3, 2, 1);
    }
}
