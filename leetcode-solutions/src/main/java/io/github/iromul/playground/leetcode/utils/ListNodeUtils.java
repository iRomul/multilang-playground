package io.github.iromul.playground.leetcode.utils;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class ListNodeUtils {

    @Contract("null -> null")
    public static ListNode fromList(List<Integer> values) {
        if (values == null || values.size() == 0) {
            return null;
        }

        if (values.size() == 1) {
            return new ListNode(values.get(0));
        }

        ListNode firstNode = new ListNode(values.get(0));

        ListNode currentNode = firstNode;

        for (int i = 1; i < values.size(); i++) {
            int value = values.get(i);
            ListNode node = new ListNode(value);

            currentNode.next = node;

            currentNode = node;
        }

        return firstNode;
    }

    @Contract("null -> null")
    public static List<Integer> toList(ListNode node) {
        if (node == null) {
            return null;
        }

        List<Integer> result = new ArrayList<>();

        ListNode curr = node;

        do {
            result.add(curr.val);

            curr = curr.next;
        } while (curr != null);

        return result;
    }
}
