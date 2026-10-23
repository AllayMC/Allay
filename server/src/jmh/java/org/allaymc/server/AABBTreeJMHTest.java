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
 * {@code AABBTreeJMHTest} measures overlap queries, linear box scans, and updates over
 * prepared random AABB fixtures. Each workload advances through the same fixed-size
 * fixture arrays and allocates any query result list inside the measured invocation.
 * @author daoge_cmd
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class AABBTreeJMHTest {
    /**
     * Number of fixture objects and prepared query boxes.
     */
    private final int TEST_ENTITY_COUNT = 2000;
    /**
     * Upper bound for randomly sampled fixture side lengths.
     */
    private final int ENTITY_SIZE = 2;
    /**
     * Upper bound for randomly sampled minimum-corner coordinates.
     */
    private final int RANGE = 500;
    /**
     * Tree populated once with the prepared fixture entities.
     */
    private AABBTree<TestEntity> aabbTree;
    /**
     * Prepared entity references used for measured tree updates.
     */
    private TestEntity[] testEntities;
    /**
     * Copied entity bounds used by the linear scan workload.
     */
    private AABBd[] testEntityAABBs;
    /**
     * Prepared random query boxes used by both overlap workloads.
     */
    private AABBd[] testAABBs;
    /**
     * {@code indexCounter} selects the next fixture across measured invocations. Each
     * workload wraps it at {@code TEST_ENTITY_COUNT}, and the configured single thread
     * owns these updates.
     */
    private int indexCounter = 0;

    /**
     * Creates random entities, inserts them into the tree, and prepares query boxes. Setup
     * also copies entity bounds for the linear scan so fixture construction occurs outside
     * measured benchmark invocations.
     */
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

    /**
     * Returns a fixture with the supplied identifier and independently sampled geometry.
     * Coordinates use {@code RANGE}, and each side length uses {@code ENTITY_SIZE}; the
     * returned entity is not added to the tree here.
     */
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

    /**
     * Returns a new random box with sampled minimum coordinates and side lengths. Each
     * minimum lies within {@code RANGE}, while each nonnegative side length lies below
     * {@code ENTITY_SIZE}.
     */
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

    /**
     * Measures a tree overlap query using the next prepared random box. The measured
     * invocation includes result-list allocation and advances the fixture index; the local
     * result is not consumed by a blackhole.
     */
    @Benchmark
    public void testAABBTreeDetectOverlaps() {
        aabbTree.detectOverlaps(testAABBs[indexCounter], new ArrayList<>());
        indexCounter++;
        // Wrap the prepared-fixture cursor so repeated measured invocations reuse setup data.
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

    /**
     * Measures a linear scan of copied entity bounds against the next prepared box. The
     * invocation includes result-list allocation and index advancement, and matching boxes
     * are appended to that local list.
     */
    @Benchmark
    public void testForEach() {
        forEachDetect(testAABBs[indexCounter], new ArrayList<>());
        indexCounter++;
        // Wrap the prepared-fixture cursor so repeated measured invocations reuse setup data.
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

    /**
     * Measures updating the next already indexed entity without changing its fixture
     * bounds. The invocation advances the shared fixture index and includes the tree's
     * remove-and-reinsert update work.
     */
    @Benchmark
    public void testAABBTreeUpdate() {
        aabbTree.update(testEntities[indexCounter]);
        indexCounter++;
        // Wrap the prepared-fixture cursor so repeated measured invocations reuse setup data.
        if (indexCounter == TEST_ENTITY_COUNT) {
            indexCounter = 0;
        }
    }

    /**
     * Appends copied fixture boxes intersecting {@code aabb} to the supplied result list.
     * The method scans every prepared entity box, includes boundary contact through JOML,
     * and does not clear existing result entries.
     */
    public void forEachDetect(AABBd aabb, List<AABBd> result) {
        for (var entityAABB : testEntityAABBs) {
            if (entityAABB.intersectsAABB(aabb))
                result.add(entityAABB);
        }
    }
}
