package io.github.iromul.reduce

import io.github.iromul.reduce.service.TestApiService
import io.github.iromul.reduce.service.TestRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MapAlgorithmTest {

    @Nested
    inner class GraphLikeDtoMapping {

        /**
         * Graph:
         * (1)   (2)
         *  |
         * (3)   (4)
         *  \    /
         *    (5)
         *     |      /
         *    (6)   (7)
         *     \    /
         *       (8)
         *
         * Start: (1)
         *
         * Expected: (1) - (3) - (5) - (6) - (8)
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
                TestEntity(7, "Seven", 7), // no incoming links
                TestEntity(8, "Eight", null),
            )

            val testRepository = TestRepository(data)
            val testApiService = TestApiService(testRepository)

            val actual = testApiService.getGraphLikeDto(1)

            val expected = TestGraphDto(
                "1",
                "One",
                TestGraphDto(
                    "3",
                    "Three",
                    TestGraphDto(
                        "5",
                        "Five",
                        TestGraphDto(
                            "6",
                            "Six",
                            TestGraphDto(
                                "8",
                                "Eight",
                                null
                            )
                        )
                    )
                )
            )

            assertThat(actual).isEqualTo(expected)
        }
    }

    @Nested
    inner class ListLikeDtoMapping {

        /**
         * Graph:
         * (1)   (2)
         *  |
         * (3)   (4)
         *  \    /
         *    (5)
         *     |      /
         *    (6)   (7)
         *     \    /
         *       (8)
         *
         * Start: (1)
         *
         * Expected: (1) [(3), (5), (6), (8)]
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
                TestEntity(7, "Seven", 7), // no incoming links
                TestEntity(8, "Eight", null),
            )

            val testRepository = TestRepository(data)
            val testApiService = TestApiService(testRepository)

            val actual = testApiService.getListLikeDto(1)

            val expected = TestListDto(
                "1",
                "One",
                listOf(
                    TestListDto(
                        "3",
                        "Three",
                        null
                    ),
                    TestListDto(
                        "5",
                        "Five",
                        null
                    ),
                    TestListDto(
                        "6",
                        "Six",
                        null
                    ),
                    TestListDto(
                        "8",
                        "Eight",
                        null
                    )
                )
            )

            assertThat(actual).isEqualTo(expected)
        }
    }
}