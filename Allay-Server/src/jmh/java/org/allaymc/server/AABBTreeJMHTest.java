package org.allaymc.server;

import org.allaymc.server.datastruct.aabb.AABBTree;
import org.allaymc.server.datastruct.aabbtree.TestEntity;
import org.joml.primitives.AABBf;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author daoge_cmd
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
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
    private AABBf[] testEntityAABBs;
    private AABBf[] testAABBs;
    private int indexCounter = 0;

    @Setup
    public void init() {
        aabbTree = new AABBTree<>();
        testEntities = new TestEntity[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testEntities[i] = createRandomTestEntity(i);
            aabbTree.add(testEntities[i]);
        }
        testEntityAABBs = new AABBf[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testEntityAABBs[i] = testEntities[i].copyOffsetAABBTo(null);
        }
        testAABBs = new AABBf[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testAABBs[i] = createRandomAABB();
        }
    }

    public TestEntity createRandomTestEntity(int id) {
        return new TestEntity(
                id,
                ThreadLocalRandom.current().nextFloat() * RANGE,
                ThreadLocalRandom.current().nextFloat() * RANGE,
                ThreadLocalRandom.current().nextFloat() * RANGE,
                ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE,
                ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE,
                ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE
        );
    }

    public AABBf createRandomAABB() {
        var x = ThreadLocalRandom.current().nextFloat() * RANGE;
        var y = ThreadLocalRandom.current().nextFloat() * RANGE;
        var z = ThreadLocalRandom.current().nextFloat() * RANGE;
        var width = ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE;
        var height = ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE;
        var length = ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE;
        return new AABBf(
                x, y, z,
                x + width, y + height, z + length
        );
    }

    // @Benchmark
    public void testAABBTreeDetectOverlaps() {
        aabbTree.detectOverlaps(testAABBs[indexCounter], new ArrayList<>());
        indexCounter++;
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

    // @Benchmark
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

    public void forEachDetect(AABBf aabb, List<AABBf> result) {
        for (var entityAABB : testEntityAABBs) {
            if (entityAABB.intersectsAABB(aabb))
                result.add(entityAABB);
        }
    }
}
