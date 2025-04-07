package io.github.iromul.reduce;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TestGraphDto {

    @NotNull
    public String id;
    @NotNull
    public String name;
    @Nullable
    public TestGraphDto linkedDto;

    public TestGraphDto(@NotNull String id, @NotNull String name, @Nullable TestGraphDto linkedDto) {
        this.id = id;
        this.name = name;
        this.linkedDto = linkedDto;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
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
    public TestGraphDto getLinkedDto() {
        return linkedDto;
    }

    public void setLinkedDto(@Nullable TestGraphDto linkedDto) {
        this.linkedDto = linkedDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestGraphDto testDto = (TestGraphDto) o;
        return Objects.equals(id, testDto.id) && Objects.equals(name, testDto.name) && Objects.equals(linkedDto, testDto.linkedDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, linkedDto);
    }

    @Override
    public String toString() {
        return "TestGraphDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", linkedDto=" + linkedDto +
                '}';
    }
}
