package org.allaymc.server;

import org.allaymc.server.datastruct.aabb.AABBTree;
import org.allaymc.server.datastruct.aabbtree.TestEntity;
import org.joml.primitives.AABBd;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author daoge_cmd
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class AABBTreeJMHTest {
    private final int TEST_ENTITY_COUNT = 2000;
    private final int ENTITY_SIZE = 2;
    private final int RANGE = 500;
    private AABBTree<TestEntity> aabbTree;
    private TestEntity[] testEntities;
    private AABBd[] testEntityAABBs;
    private AABBd[] testAABBs;
    private int indexCounter = 0;

    @Setup
    public void init() {
        aabbTree = new AABBTree<>();
        testEntities = new TestEntity[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testEntities[i] = createRandomTestEntity(i);
            aabbTree.add(testEntities[i]);
        }
        testEntityAABBs = new AABBd[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testEntityAABBs[i] = new AABBd().set(testEntities[i].getOffsetAABB());
        }
        testAABBs = new AABBd[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testAABBs[i] = createRandomAABB();
        }
    }

    public TestEntity createRandomTestEntity(int id) {
        return new TestEntity(
                id,
                ThreadLocalRandom.current().nextDouble() * RANGE,
                ThreadLocalRandom.current().nextDouble() * RANGE,
                ThreadLocalRandom.current().nextDouble() * RANGE,
                ThreadLocalRandom.current().nextDouble() * ENTITY_SIZE,
                ThreadLocalRandom.current().nextDouble() * ENTITY_SIZE,
                ThreadLocalRandom.current().nextDouble() * ENTITY_SIZE
        );
    }

    public AABBd createRandomAABB() {
        var x = ThreadLocalRandom.current().nextDouble() * RANGE;
        var y = ThreadLocalRandom.current().nextDouble() * RANGE;
        var z = ThreadLocalRandom.current().nextDouble() * RANGE;
        var width = ThreadLocalRandom.current().nextDouble() * ENTITY_SIZE;
        var height = ThreadLocalRandom.current().nextDouble() * ENTITY_SIZE;
        var length = ThreadLocalRandom.current().nextDouble() * ENTITY_SIZE;
        return new AABBd(
                x, y, z,
                x + width, y + height, z + length
        );
    }

    @Benchmark
    public void testAABBTreeDetectOverlaps() {
        aabbTree.detectOverlaps(testAABBs[indexCounter], new ArrayList<>());
        indexCounter++;
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

    @Benchmark
    public void testForEach() {
        forEachDetect(testAABBs[indexCounter], new ArrayList<>());
        indexCounter++;
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

    @Benchmark
    public void testAABBTreeUpdate() {
        aabbTree.update(testEntities[indexCounter]);
        indexCounter++;
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

    public void forEachDetect(AABBd aabb, List<AABBd> result) {
        for (var entityAABB : testEntityAABBs) {
            if (entityAABB.intersectsAABB(aabb))
                result.add(entityAABB);
        }
    }
}
