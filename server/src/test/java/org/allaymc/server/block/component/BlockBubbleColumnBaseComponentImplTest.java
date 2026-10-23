package org.allaymc.server.block.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3i;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * {@code BlockBubbleColumnBaseComponentImplTest} exercises bubble column conversion,
 * direction changes, restoration, and flowing-water boundaries. A mocked dimension
 * stores states by height and layer so each case can inspect foreground columns and
 * their retained water independently.
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class BlockBubbleColumnBaseComponentImplTest {
    /**
     * {@code blocks} stores foreground and retained-layer states for this test
     * instance. {@code setUp} connects mocked dimension reads and accepted writes to
     * this shared map.
     */
    private final Map<Cell, BlockState> blocks = new HashMap<>();
    /**
     * Mocked dimension under test.
     */
    private Dimension dimension;

    /**
     * Connects a real-default-method dimension mock to the test's height-and-layer
     * state map. Reads return air for absent cells, writes always succeed, and the
     * mock accepts all queried heights.
     */
    @BeforeEach
    void setUp() {
        dimension = mock(Dimension.class, CALLS_REAL_METHODS);
        doReturn(true).when(dimension).isYInRange(anyDouble());
        doAnswer(invocation ->
                blocks.getOrDefault(
                        new Cell(invocation.getArgument(1), invocation.getArgument(3)),
                        BlockTypes.AIR.getDefaultState()
                )
        ).when(dimension).getBlockState(anyInt(), anyInt(), anyInt(), anyInt());
        doAnswer(invocation -> {
            blocks.put(new Cell(invocation.getArgument(1), invocation.getArgument(4)), invocation.getArgument(3));
            return true;
        }).when(dimension).setBlockState(
                anyInt(), anyInt(), anyInt(), any(BlockState.class), anyInt(),
                anyBoolean(), anyBoolean(), anyBoolean(), nullable(org.allaymc.api.block.dto.PlayerInteractInfo.class)
        );
    }

    /**
     * Builds a three-block source-water column above soul sand and below stone. Every
     * source position must become an upward bubble column with retained source water,
     * and stone must remain unchanged.
     */
    @Test
    void shouldBuildBubbleColumnThroughAllSourceWaterBlocks() {
        setMainBlock(0, BlockTypes.SOUL_SAND.getDefaultState());
        setMainBlock(1, BlockTypes.WATER.getDefaultState());
        setMainBlock(2, BlockTypes.WATER.getDefaultState());
        setMainBlock(3, BlockTypes.WATER.getDefaultState());
        setMainBlock(4, BlockTypes.STONE.getDefaultState());

        BlockBubbleColumnBaseComponentImpl.updateAt(dimension, new Vector3i(0, 1, 0));

        for (int y = 1; y <= 3; y++) {
            var bubbleColumn = getBlock(y, 0);
            assertEquals(BlockTypes.BUBBLE_COLUMN, bubbleColumn.getBlockType());
            assertEquals(false, bubbleColumn.getPropertyValue(BlockPropertyTypes.DRAG_DOWN));
            assertTrue(org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource(getBlock(y, 1)));
        }
        assertEquals(BlockTypes.STONE, getBlock(4, 0).getBlockType());
    }

    /**
     * Builds an upward column and replaces its soul sand generator with magma.
     * Refreshing the same starting position must switch every existing column block
     * to downward drag.
     */
    @Test
    void shouldUpdateDirectionForTheWholeExistingColumn() {
        setMainBlock(0, BlockTypes.SOUL_SAND.getDefaultState());
        setMainBlock(1, BlockTypes.WATER.getDefaultState());
        setMainBlock(2, BlockTypes.WATER.getDefaultState());
        setMainBlock(3, BlockTypes.STONE.getDefaultState());
        BlockBubbleColumnBaseComponentImpl.updateAt(dimension, new Vector3i(0, 1, 0));

        setMainBlock(0, BlockTypes.MAGMA.getDefaultState());
        BlockBubbleColumnBaseComponentImpl.updateAt(dimension, new Vector3i(0, 1, 0));

        for (int y = 1; y <= 2; y++) {
            assertEquals(true, getBlock(y, 0).getPropertyValue(BlockPropertyTypes.DRAG_DOWN));
        }
    }

    /**
     * Builds a two-block column and replaces its generator with ordinary stone.
     * Refreshing must restore foreground source water at both heights and clear the
     * previously retained water layer.
     */
    @Test
    void shouldRestoreWaterWhenGeneratorIsRemoved() {
        setMainBlock(0, BlockTypes.SOUL_SAND.getDefaultState());
        setMainBlock(1, BlockTypes.WATER.getDefaultState());
        setMainBlock(2, BlockTypes.WATER.getDefaultState());
        setMainBlock(3, BlockTypes.STONE.getDefaultState());
        BlockBubbleColumnBaseComponentImpl.updateAt(dimension, new Vector3i(0, 1, 0));

        setMainBlock(0, BlockTypes.STONE.getDefaultState());
        BlockBubbleColumnBaseComponentImpl.updateAt(dimension, new Vector3i(0, 1, 0));

        for (int y = 1; y <= 2; y++) {
            assertEquals(BlockTypes.WATER, getBlock(y, 0).getBlockType());
            assertTrue(org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource(getBlock(y, 0)));
            assertEquals(BlockTypes.AIR, getBlock(y, 1).getBlockType());
        }
    }

    /**
     * Places source water below a flowing-water state with nonzero depth. The source
     * position must convert to a bubble column while the flowing position remains
     * unchanged.
     */
    @Test
    void shouldStopAtFlowingWater() {
        setMainBlock(0, BlockTypes.SOUL_SAND.getDefaultState());
        setMainBlock(1, BlockTypes.WATER.getDefaultState());
        setMainBlock(2, BlockTypes.FLOWING_WATER.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.LIQUID_DEPTH, 1));

        BlockBubbleColumnBaseComponentImpl.updateAt(dimension, new Vector3i(0, 1, 0));

        assertEquals(BlockTypes.BUBBLE_COLUMN, getBlock(1, 0).getBlockType());
        assertEquals(BlockTypes.FLOWING_WATER, getBlock(2, 0).getBlockType());
    }

    /**
     * Stores the supplied state at one height in the foreground layer.
     */
    private void setMainBlock(int y, BlockState state) {
        blocks.put(new Cell(y, 0), state);
    }

    /**
     * Returns the stored state at the requested height and layer. An absent cell
     * returns the registered default air state, matching the mocked dimension's
     * empty-position behavior in these tests.
     */
    private BlockState getBlock(int y, int layer) {
        return blocks.getOrDefault(new Cell(y, layer), BlockTypes.AIR.getDefaultState());
    }

    /**
     * {@code Cell} identifies one mocked block position by height and layer.
     *
     * @param y block height coordinate
     * @param layer block storage layer index
     */
    private record Cell(int y, int layer) {
    }
}
