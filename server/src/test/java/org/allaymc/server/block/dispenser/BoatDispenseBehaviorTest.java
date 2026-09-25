package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.server.registry.loader.DispenserBehaviorRegistryLoader;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3ic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class BoatDispenseBehaviorTest {

    private final BoatDispenseBehavior behavior = new BoatDispenseBehavior();

    @Test
    void boatOnWaterSpawnsEntityInTargetCell() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(mock(BlockLiquidBehavior.class));
        doReturn(BlockTypes.WATER).when(targetBlock).getBlockType();

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.OAK_BOAT.createItemStack(1));

        assertTrue(result.succeeded());
        var captor = ArgumentCaptor.forClass(Entity.class);
        verify(entityManager).addEntity(captor.capture());
        var entity = captor.getValue();
        assertEquals(0.5, entity.getLocation().x(), 0.001);
        assertEquals(64.5, entity.getLocation().y(), 0.001);
        assertEquals(-0.5, entity.getLocation().z(), 0.001);
    }

    @Test
    void boatOnNonWaterWithAirAboveSpawnsOnTop() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);
        var airBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        when(dimension.getBlockState(any(Vector3ic.class))).thenAnswer(invocation -> {
            var pos = invocation.getArgument(0, Vector3ic.class);
            return pos.y() == 65 ? airBlock : targetBlock;
        });
        doReturn(BlockTypes.AIR).when(airBlock).getBlockType();

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.OAK_BOAT.createItemStack(1));

        assertTrue(result.succeeded());
        var captor = ArgumentCaptor.forClass(Entity.class);
        verify(entityManager).addEntity(captor.capture());
        var entity = captor.getValue();
        assertEquals(0.5, entity.getLocation().x(), 0.001);
        assertEquals(65.5, entity.getLocation().y(), 0.001);
        assertEquals(-0.5, entity.getLocation().z(), 0.001);
    }

    @Test
    void boatOnNonWaterWithSolidAboveFails() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);
        var solidBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenAnswer(invocation -> {
            var pos = invocation.getArgument(0, Vector3ic.class);
            return pos.y() == 65 ? solidBlock : targetBlock;
        });
        doReturn(BlockTypes.STONE).when(solidBlock).getBlockType();

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.OAK_BOAT.createItemStack(1));

        assertFalse(result.succeeded());
        verify(entityManager, never()).addEntity(any(Entity.class));
    }

    @Test
    void chestBoatOnWaterSpawnsChestBoatEntity() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        when(targetBlock.getBehavior()).thenReturn(mock(BlockLiquidBehavior.class));
        doReturn(BlockTypes.WATER).when(targetBlock).getBlockType();

        var result = new ChestBoatDispenseBehavior().dispense(block, BlockFace.NORTH, ItemTypes.OAK_CHEST_BOAT.createItemStack(1));

        assertTrue(result.succeeded());
        var captor = ArgumentCaptor.forClass(Entity.class);
        verify(entityManager).addEntity(captor.capture());
        assertEquals(0.5, captor.getValue().getLocation().x(), 0.001);
        assertEquals(64.5, captor.getValue().getLocation().y(), 0.001);
        assertEquals(-0.5, captor.getValue().getLocation().z(), 0.001);
    }

    @Test
    void allBoatItemsRegisteredWithCorrectBehaviors() throws IllegalAccessException {
        var map = new DispenserBehaviorRegistryLoader().load(null);
        for (var field : ItemTypes.class.getFields()) {
            var name = field.getName();
            if (!name.endsWith("BOAT")) {
                continue;
            }
            var itemType = (ItemType<?>) field.get(null);
            assertNotNull(itemType, name);
            var behavior = map.get(itemType);
            assertNotNull(behavior, name);
            if (name.endsWith("CHEST_BOAT")) {
                assertEquals(ChestBoatDispenseBehavior.class, behavior.getClass(), name);
            } else {
                assertEquals(BoatDispenseBehavior.class, behavior.getClass(), name);
            }
        }
    }
}