package io.github.iromul.playground.leetcode.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListNode {

    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode of(int... values) {
        List<Integer> valuesList = Arrays.stream(values).boxed().collect(Collectors.toList());

        return ListNodeUtils.fromList(valuesList);
    }
}
