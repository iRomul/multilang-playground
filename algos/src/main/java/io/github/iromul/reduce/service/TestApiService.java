package io.github.iromul.reduce.service;

import io.github.iromul.reduce.TestEntity;
import io.github.iromul.reduce.TestGraphDto;
import io.github.iromul.reduce.TestListDto;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestApiService {

    private final TestRepository repository;

    public TestApiService(TestRepository repository) {
        this.repository = repository;
    }

    public TestListDto getListLikeDto(Long id) {
        TestEntity entity = repository.getById(id);

        TestListDto dto = mapListDto(entity);

        if (entity.getLinkedId() != null) {
//            List<TestEntity> linkedEntitiesList = repository.getLinkedEntities(entity);
            List<TestEntity> linkedEntitiesList = Collections.emptyList();

            dto.setLinkedDto(linkedEntitiesList
                    .stream()
                    .map(this::mapListDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public TestGraphDto getGraphLikeDto(Long id) {
        TestEntity entity = repository.getById(id);

        TestGraphDto dto = mapGraphDto(entity);

        if (entity.getLinkedId() != null) {
            buildTree(dto, entity);
        }

        return dto;
    }

    public void buildTree(TestGraphDto root, TestEntity rootEntity) {
//        List<TestEntity> linkedEntitiesList = repository.getLinkedEntities(rootEntity);
        List<TestEntity> linkedEntitiesList = Collections.emptyList();

        Map<Long, TestEntity> linked = linkedEntitiesList.stream()
                .collect(Collectors.toMap(TestEntity::getId, Function.identity()));

        buildTree(root, rootEntity, linked);
    }

    public void buildTree(TestGraphDto currentDto, TestEntity currentEntity, Map<Long, TestEntity> data) {
        if (currentEntity.getLinkedId() != null) {
            TestEntity nextEntity = data.get(currentEntity.getLinkedId());
            TestGraphDto nextDto = mapGraphDto(nextEntity);

            currentDto.linkedDto = nextDto;

            buildTree(nextDto, nextEntity, data);
        }
    }

    public TestListDto mapListDto(TestEntity testEntity) {
        return new TestListDto(
                testEntity.getId().toString(),
                testEntity.getName(),
                null
        );
    }

    public TestGraphDto mapGraphDto(TestEntity testEntity) {
        return new TestGraphDto(
                testEntity.getId().toString(),
                testEntity.getName(),
                null
        );
    }
}
