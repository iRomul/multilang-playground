package io.github.iromul.playground.leetcode.utils;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Iterables;

import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;

public class LinkedListAssert extends AbstractAssert<LinkedListAssert, ListNode> {

    private final Iterables iterables = Iterables.instance();

    public LinkedListAssert(ListNode actual) {
        super(actual, LinkedListAssert.class);
    }

    public static LinkedListAssert assertThat(ListNode actual) {
        return new LinkedListAssert(actual);
    }

    public LinkedListAssert containsExactly(Integer... expected) {
        List<Integer> actual = ListNodeUtils.toList(super.actual);

        iterables.assertContainsExactly(new WritableAssertionInfo(null), actual, expected);

        return this;
    }

    public LinkedListAssert containsExactlyElementsOf(Iterable<Integer> expected) {
        List<Integer> actual = ListNodeUtils.toList(super.actual);
        Integer[] expectedArray = (Integer[]) newArrayList(expected).toArray();

        return containsExactly(expectedArray);
    }
}
