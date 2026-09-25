package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityCow;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.physics.AABBOverlapFilter;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.testutils.AllayTestExtension;
import org.allaymc.api.math.position.Position3i;
import org.joml.Vector3ic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class BucketDispenseBehaviorTest {

    private final BucketDispenseBehavior behavior = new BucketDispenseBehavior();

    @Test
    void adultCowInTargetCellReturnsMilkBucketAndPlaysMilkingSound() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var cow = mock(EntityCow.class);
        var physics = mock(EntityPhysicsEngine.class);
        var entityManager = mock(EntityManager.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getPhysicsService()).thenReturn(physics);
        when(physics.computeCollidingEntities(any(), any())).thenAnswer(invocation -> {
            AABBOverlapFilter<Entity> predicate = invocation.getArgument(1);
            return predicate.test(cow) ? List.of(cow) : List.of();
        });
        when(cow.isBaby()).thenReturn(false);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BUCKET.createItemStack(1));

        assertTrue(result.succeeded());
        assertEquals(ItemTypes.MILK_BUCKET, result.remainingItem().getItemType());
        verify(dimension).addSound(any(Vector3ic.class), eq(SimpleSound.MILKING));
        verify(dimension, never()).getBlockState(any(Vector3ic.class));
    }

    @Test
    void babyCowFallsThroughToExistingBehavior() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var cow = mock(EntityCow.class);
        var physics = mock(EntityPhysicsEngine.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getPhysicsService()).thenReturn(physics);
        when(physics.computeCollidingEntities(any(), any())).thenAnswer(invocation -> {
            AABBOverlapFilter<Entity> predicate = invocation.getArgument(1);
            return predicate.test(cow) ? List.of(cow) : List.of();
        });
        when(cow.isBaby()).thenReturn(true);
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(mock(BlockLiquidBehavior.class));
        doReturn(BlockTypes.WATER).when(targetBlock).getBlockType();
        when(targetBlock.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH)).thenReturn(0);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BUCKET.createItemStack(1));

        assertTrue(result.succeeded());
        assertEquals(ItemTypes.WATER_BUCKET, result.remainingItem().getItemType());
        verify(dimension, never()).addSound(any(Vector3ic.class), eq(SimpleSound.MILKING));
    }

    @Test
    void noCowInTargetCellKeepsWaterCollectionBehavior() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var physics = mock(EntityPhysicsEngine.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        when(entityManager.getPhysicsService()).thenReturn(physics);
        when(physics.computeCollidingEntities(any(), any())).thenReturn(List.of());
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(mock(BlockLiquidBehavior.class));
        doReturn(BlockTypes.WATER).when(targetBlock).getBlockType();
        when(targetBlock.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH)).thenReturn(0);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.BUCKET.createItemStack(1));

        assertTrue(result.succeeded());
        assertEquals(ItemTypes.WATER_BUCKET, result.remainingItem().getItemType());
        verify(dimension, never()).addSound(any(Vector3ic.class), eq(SimpleSound.MILKING));
    }
}