package io.github.iromul.reduce.alg

import io.github.iromul.reduce.TestEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.function.Predicate

class RecursiveTreeCollectorTest {

    @Test
    fun `muffin nuffin depth 1, element 1`() {
        val collector = RecursiveTreeCollector<TestEntity>(1)

        val data = listOf(
            TestEntity(1, "One", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).isEmpty()
    }

    @Test
    fun `muffin nuffin depth 1, element 2`() {
        val collector = RecursiveTreeCollector<TestEntity>(1)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3))
    }

    @Test
    fun `muffin nuffin depth 1, element 3`() {
        val collector = RecursiveTreeCollector<TestEntity>(1)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3))
    }

    @Test
    fun `muffin nuffin depth 1, element 4`() {
        val collector = RecursiveTreeCollector<TestEntity>(1)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3))
    }

    @Test
    fun `muffin nuffin depth 1, element 5`() {
        val collector = RecursiveTreeCollector<TestEntity>(1)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", 5),
            TestEntity(5, "Six", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3))
    }

    @Test
    fun `muffin nuffin depth 2, element 1`() {
        val collector = RecursiveTreeCollector<TestEntity>(2)

        val data = listOf(
            TestEntity(1, "One", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).isEmpty()
    }

    @Test
    fun `muffin nuffin depth 2, element 2`() {
        val collector = RecursiveTreeCollector<TestEntity>(2)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3))
    }

    @Test
    fun `muffin nuffin depth 2, element 3`() {
        val collector = RecursiveTreeCollector<TestEntity>(2)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3), TestEntity(3, "Three", null))
    }

    @Test
    fun `muffin nuffin depth 2, element 4`() {
        val collector = RecursiveTreeCollector<TestEntity>(2)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3), TestEntity(3, "Three", null))
    }

    @Test
    fun `muffin nuffin depth 2, element 5`() {
        val collector = RecursiveTreeCollector<TestEntity>(2)

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", 5),
            TestEntity(5, "Six", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3), TestEntity(3, "Three", null))
    }

    @Test
    fun `muffin nuffin depth z, element 1`() {
        val collector = RecursiveTreeCollector<TestEntity>()

        val data = listOf(
            TestEntity(1, "One", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).isEmpty()
    }

    @Test
    fun `muffin nuffin depth z, element 2`() {
        val collector = RecursiveTreeCollector<TestEntity>()

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(TestEntity(2, "Two", 3))
    }

    @Test
    fun `muffin nuffin depth z, element 3`() {
        val collector = RecursiveTreeCollector<TestEntity>()

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", null)
        )
    }

    @Test
    fun `muffin nuffin depth z, element 4`() {
        val collector = RecursiveTreeCollector<TestEntity>()

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", null)
        )
    }

    @Test
    fun `muffin nuffin depth z, element 5`() {
        val collector = RecursiveTreeCollector<TestEntity>()

        val data = listOf(
            TestEntity(1, "One", 2),
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", 5),
            TestEntity(5, "Six", null)
        )

        val root = data.first()

        val list = collector.collect(
            root,
            { testEntity: TestEntity -> testEntity.linkedId != null },
            { testEntity: TestEntity -> getByIdIn(testEntity.linkedId!!, data) }
        )

        assertThat(list).containsExactly(
            TestEntity(2, "Two", 3),
            TestEntity(3, "Three", 4),
            TestEntity(4, "Four", 5),
            TestEntity(5, "Six", null)
        )
    }

    private fun getByIdIn(id: Long, data: List<TestEntity>): TestEntity {
        return data.stream()
            .filter(byId(id))
            .findFirst()
            .orElseThrow {
                RuntimeException(
                    "No id found: $id"
                )
            }
    }

    private fun byId(id: Long): Predicate<TestEntity> {
        return Predicate { testEntity: TestEntity -> testEntity.id == id }
    }
}