package cn.allay.server;

import cn.allay.api.datastruct.aabbtree.AABBTree;
import cn.allay.api.datastruct.aabbtree.TestEntity;
import org.joml.primitives.AABBd;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Allay Project 2023/7/30
 *
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
    private AABBd[] testEntityAABBs;
    private AABBd[] testAABBs;

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
            testEntityAABBs[i] = testEntities[i].copyOffsetAABBTo(null);
        }
        testAABBs = new AABBd[TEST_ENTITY_COUNT];
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

    public AABBd createRandomAABB() {
        var x = ThreadLocalRandom.current().nextFloat() * RANGE;
        var y = ThreadLocalRandom.current().nextFloat() * RANGE;
        var z = ThreadLocalRandom.current().nextFloat() * RANGE;
        var width = ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE;
        var height = ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE;
        var length = ThreadLocalRandom.current().nextFloat() * ENTITY_SIZE;
        return new AABBd(
                x, y, z,
                x + width, y + height, z + length
        );
    }

    private int indexCounter = 0;

//    @Benchmark
    public void testAABBTreeDetectOverlaps() {
        aabbTree.detectOverlaps(testAABBs[indexCounter], new ArrayList<>());
        indexCounter++;
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

//    @Benchmark
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
