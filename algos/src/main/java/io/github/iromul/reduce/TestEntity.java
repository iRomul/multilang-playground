package io.github.iromul.reduce;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * [TestEntity(id=1)]
 *
 * [TestEntity(id=2)]
 */
public class TestEntity {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @Nullable
    private Long linkedId;

    public TestEntity(@NotNull Long id, @NotNull String name, @Nullable Long linkedId) {
        this.id = id;
        this.name = name;
        this.linkedId = linkedId;
    }

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Nullable
    public Long getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(@Nullable Long linkedId) {
        this.linkedId = linkedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkedId=" + linkedId +
                '}';
    }
}
