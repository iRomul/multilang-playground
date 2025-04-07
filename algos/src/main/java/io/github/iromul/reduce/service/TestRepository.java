package io.github.iromul.reduce.service;

import io.github.iromul.reduce.TestEntity;
import io.github.iromul.reduce.alg.TreeTraverse;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestRepository {

    private final List<TestEntity> data;

    public TestRepository(List<TestEntity> data) {
        this.data = data;
    }

    public TestEntity getById(Long id) {
        return getByIdIn(id, this.data);
    }

    public List<TestEntity> getLinkedEntities(Long id) {
        return data.stream()
                .filter(byLinkedId(id))
                .collect(Collectors.toList());
    }

    public List<TestEntity> getLinkedEntitiesFlatTree(Long id) {
        TestEntity root = getById(id);
        TreeTraverse.DFSIntance1<TestEntity> treeTraverse = TreeTraverse.dfsInstance(node -> getLinkedEntities(node.getId()), 7);

        List<TestEntity> linkedEntities = treeTraverse.collectAll(root);

        linkedEntities.remove(root);

        return linkedEntities;
    }

    private static TestEntity getByIdIn(Long id, List<TestEntity> data) {
        return data.stream()
                .filter(byId(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No id found: " + id));
    }

    private static Predicate<TestEntity> byId(Long id) {
        return testEntity -> testEntity.getId().equals(id);
    }

    private static Predicate<TestEntity> byLinkedId(Long id) {
        return testEntity -> Objects.equals(testEntity.getLinkedId(), id);
    }
}
