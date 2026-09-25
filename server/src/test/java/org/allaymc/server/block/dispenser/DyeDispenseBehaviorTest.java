package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockSaplingBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3ic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class DyeDispenseBehaviorTest {

    private interface FertilizableBehavior extends BlockBehavior, BlockFertilizableComponent {
    }

    private interface SaplingBehavior extends BlockBehavior, BlockFertilizableComponent, BlockSaplingBehavior {
    }

    private final DyeDispenseBehavior behavior = new DyeDispenseBehavior();

    @Test
    void boneMealAtFertilizableBlockAppliesGrowthAndConsumesItem() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var targetBlock = mock(BlockState.class);
        var fertilizable = mock(FertilizableBehavior.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(fertilizable);
        when(fertilizable.onBoneMealUsed(any(Dimension.class), any(Vector3ic.class), any(BlockState.class))).thenReturn(true);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BONE_MEAL.createItemStack(1));

        assertTrue(result.succeeded());
    }

    @Test
    void rapidFertilizerAtFertilizableBlockAppliesGrowthAndConsumesItem() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var targetBlock = mock(BlockState.class);
        var fertilizable = mock(FertilizableBehavior.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(fertilizable);
        when(fertilizable.onBoneMealUsed(any(Dimension.class), any(Vector3ic.class), any(BlockState.class))).thenReturn(true);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.RAPID_FERTILIZER.createItemStack(1));

        assertTrue(result.succeeded());
    }

    @Test
    void boneMealAtNonFertilizableBlockFailsWithoutConsuming() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var targetBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(mock(BlockBehavior.class));

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BONE_MEAL.createItemStack(1));

        assertFalse(result.succeeded());
    }

    @Test
    void boneMealAtFullyGrownCropFailsWithoutConsuming() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var targetBlock = mock(BlockState.class);
        var fertilizable = mock(FertilizableBehavior.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(fertilizable);
        when(fertilizable.onBoneMealUsed(any(Dimension.class), any(Vector3ic.class), any(BlockState.class))).thenReturn(false);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BONE_MEAL.createItemStack(1));

        assertFalse(result.succeeded());
    }

    @Test
    void boneMealAtSaplingConsumesEvenWhenGrowthFails() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var targetBlock = mock(BlockState.class);
        var sapling = mock(SaplingBehavior.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(sapling);
        when(sapling.onBoneMealUsed(any(Dimension.class), any(Vector3ic.class), any(BlockState.class))).thenReturn(false);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BONE_MEAL.createItemStack(1));

        assertTrue(result.succeeded());
    }

    @Test
    void boneMealAtRealWheatBlockSucceeds() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(BlockTypes.WHEAT.getDefaultState());

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BONE_MEAL.createItemStack(1));

        assertTrue(result.succeeded());
    }

    @Test
    void boneMealAtRealStoneBlockFails() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(BlockTypes.STONE.getDefaultState());

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BONE_MEAL.createItemStack(1));

        assertFalse(result.succeeded());
    }
}