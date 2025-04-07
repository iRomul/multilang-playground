package io.github.iromul.reduce

import io.github.iromul.reduce.service.TestRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MapAlgorithmReverseTest {

    @Nested
    inner class ListLikeDtoMapping {

        /**
         * Graph:
         * (1)          (2)
         *  |
         * (3)   (4)
         *  \    /
         *    (5)
         *     |
         *    (6)   (7)
         *     \    /
         *       (8)
         *
         * Start: (1)
         *
         * Expected: (8) [(6), (7), (5), (3), (4), (1), (2)]
         */
        @Test
        fun name() {
            val data = listOf(
                TestEntity(1, "One", 3),
                TestEntity(2, "Two", null),
                TestEntity(3, "Three", 5),
                TestEntity(4, "Four", 5),
                TestEntity(5, "Five", 6),
                TestEntity(6, "Six", 8),
                TestEntity(7, "Seven", 8),
                TestEntity(8, "Eight", null)
            )

            val testRepository = TestRepository(data)

            val actual = testRepository.getLinkedEntitiesFlatTree(8)

            val expected = listOf(
                TestEntity(1, "One", 3),
                TestEntity(3, "Three", 5),
                TestEntity(4, "Four", 5),
                TestEntity(5, "Five", 6),
                TestEntity(6, "Six", 8),
                TestEntity(7, "Seven", 8)
            )

            assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)
        }
    }
}
