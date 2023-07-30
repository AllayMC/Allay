package cn.allay.server;

import cn.allay.api.datastruct.aabbtree.AABBTree;
import cn.allay.server.datastruct.aabbtree.TestEntity;
import org.joml.primitives.AABBf;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
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
    private final int TEST_ENTITY_COUNT = 500;
    private AABBTree<TestEntity> aabbTree;
    private TestEntity[] testEntities;
    private AABBf[] testAABBs;

    @Setup
    public void init() {
        aabbTree = new AABBTree<>();
        testEntities = new TestEntity[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testEntities[i] = createRandomTestEntity(i);
            aabbTree.add(testEntities[i]);
        }
        testAABBs = new AABBf[TEST_ENTITY_COUNT];
        for (int i = 0; i < TEST_ENTITY_COUNT; i++) {
            testAABBs[i] = createRandomAABB();
        }
    }

    public TestEntity createRandomTestEntity(int id) {
        return new TestEntity(
                id,
                ThreadLocalRandom.current().nextFloat() * 100,
                ThreadLocalRandom.current().nextFloat() * 100,
                ThreadLocalRandom.current().nextFloat() * 100,
                ThreadLocalRandom.current().nextFloat() * 2,
                ThreadLocalRandom.current().nextFloat() * 2,
                ThreadLocalRandom.current().nextFloat() * 2
        );
    }

    public AABBf createRandomAABB() {
        var x = ThreadLocalRandom.current().nextFloat() * 100;
        var y = ThreadLocalRandom.current().nextFloat() * 100;
        var z = ThreadLocalRandom.current().nextFloat() * 100;
        var width = ThreadLocalRandom.current().nextFloat() * 2;
        var height = ThreadLocalRandom.current().nextFloat() * 2;
        var length = ThreadLocalRandom.current().nextFloat() * 2;
        return new AABBf(
                x, y, z,
                x + width, y + height, z + length
        );
    }

    private int indexCounter = 0;

    @Benchmark
    public void testAABB() {
        aabbTree.detectOverlaps(testAABBs[indexCounter], new ArrayList<>());
        indexCounter++;
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }
}
