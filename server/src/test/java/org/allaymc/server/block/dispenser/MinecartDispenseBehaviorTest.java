package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityChestMinecart;
import org.allaymc.api.entity.interfaces.EntityCommandBlockMinecart;
import org.allaymc.api.entity.interfaces.EntityHopperMinecart;
import org.allaymc.api.entity.interfaces.EntityMinecart;
import org.allaymc.api.entity.interfaces.EntityTntMinecart;
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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class MinecartDispenseBehaviorTest {

    private final MinecartDispenseBehavior behavior = new MinecartDispenseBehavior();

    @Test
    void minecartOnRailSpawnsMinecartEntity() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        doReturn(BlockTypes.RAIL).when(targetBlock).getBlockType();

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.MINECART.createItemStack(1));

        assertTrue(result.succeeded());
        var captor = ArgumentCaptor.forClass(Entity.class);
        verify(entityManager).addEntity(captor.capture());
        var entity = captor.getValue();
        assertInstanceOf(EntityMinecart.class, entity);
        assertEquals(0.5, entity.getLocation().x(), 0.001);
        assertEquals(64.5, entity.getLocation().y(), 0.001);
        assertEquals(-0.5, entity.getLocation().z(), 0.001);
    }

    @Test
    void allMinecartVariantsSpawnMatchingEntities() {
        var itemToEntity = Map.of(
                ItemTypes.MINECART, EntityMinecart.class,
                ItemTypes.CHEST_MINECART, EntityChestMinecart.class,
                ItemTypes.HOPPER_MINECART, EntityHopperMinecart.class,
                ItemTypes.TNT_MINECART, EntityTntMinecart.class,
                ItemTypes.COMMAND_BLOCK_MINECART, EntityCommandBlockMinecart.class
        );

        for (var entry : itemToEntity.entrySet()) {
            var dimension = mock(Dimension.class);
            var block = mock(Block.class);
            var entityManager = mock(EntityManager.class);
            var targetBlock = mock(BlockState.class);

            when(block.getDimension()).thenReturn(dimension);
            when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
            when(dimension.getEntityManager()).thenReturn(entityManager);
            when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
            doReturn(BlockTypes.RAIL).when(targetBlock).getBlockType();

            var result = behavior.dispense(block, BlockFace.NORTH, entry.getKey().createItemStack(1));

            assertTrue(result.succeeded(), entry.getKey().getIdentifier().toString());
            var captor = ArgumentCaptor.forClass(Entity.class);
            verify(entityManager).addEntity(captor.capture());
            assertInstanceOf(entry.getValue(), captor.getValue(), entry.getKey().getIdentifier().toString());
        }
    }

    @Test
    void minecartOnEachRailVariantSpawns() {
        for (var railType : new Object[]{BlockTypes.ACTIVATOR_RAIL, BlockTypes.DETECTOR_RAIL, BlockTypes.GOLDEN_RAIL}) {
            var dimension = mock(Dimension.class);
            var block = mock(Block.class);
            var entityManager = mock(EntityManager.class);
            var targetBlock = mock(BlockState.class);

            when(block.getDimension()).thenReturn(dimension);
            when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
            when(dimension.getEntityManager()).thenReturn(entityManager);
            when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
            doReturn(railType).when(targetBlock).getBlockType();

            var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.MINECART.createItemStack(1));

            assertTrue(result.succeeded());
            verify(entityManager).addEntity(any(Entity.class));
        }
    }

    @Test
    void minecartOnNonRailFails() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var targetBlock = mock(BlockState.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(targetBlock);
        doReturn(BlockTypes.STONE).when(targetBlock).getBlockType();

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.MINECART.createItemStack(1));

        assertFalse(result.succeeded());
        verify(entityManager, never()).addEntity(any(Entity.class));
    }

    @Test
    void allMinecartItemsRegisteredWithCorrectBehavior() throws IllegalAccessException {
        var map = new DispenserBehaviorRegistryLoader().load(null);
        for (var field : ItemTypes.class.getFields()) {
            var name = field.getName();
            if (!name.endsWith("MINECART")) {
                continue;
            }
            var itemType = (ItemType<?>) field.get(null);
            assertNotNull(itemType, name);
            var behavior = map.get(itemType);
            assertNotNull(behavior, name);
            assertEquals(MinecartDispenseBehavior.class, behavior.getClass(), name);
        }
    }
}