package io.github.iromul.reduce;

import java.util.List;

public class MapAlgorithm {

    public static TestGraphDto mapToGraph(List<TestEntity> entities, Long firstId) {
        return null;
    }

    public static TestListDto mapToList(List<TestEntity> entities, Long firstId) {
        TestEntity root = entities.stream().filter(testEntity -> testEntity.getId().equals(firstId)).findFirst().get();

        TestListDto rootDto = new TestListDto(
                root.getId().toString(),
                root.getName(),
                null
        );

        return rootDto;
    }
}
