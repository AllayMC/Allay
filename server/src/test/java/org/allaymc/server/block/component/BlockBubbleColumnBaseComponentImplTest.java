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
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class BlockBubbleColumnBaseComponentImplTest {
    private final Map<Cell, BlockState> blocks = new HashMap<>();
    private Dimension dimension;

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

    private void setMainBlock(int y, BlockState state) {
        blocks.put(new Cell(y, 0), state);
    }

    private BlockState getBlock(int y, int layer) {
        return blocks.getOrDefault(new Cell(y, layer), BlockTypes.AIR.getDefaultState());
    }

    private record Cell(int y, int layer) {
    }
}
