package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityCow;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.physics.AABBOverlapFilter;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.allaymc.server.registry.loader.DispenserBehaviorRegistryLoader;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class ShearsDispenseBehaviorTest {

    private final ShearsDispenseBehavior behavior = new ShearsDispenseBehavior();

    private void stubSheepLookup(Dimension dimension, EntityManager entityManager, Entity entity) {
        var physicsService = mock(EntityPhysicsEngine.class);
        when(entityManager.getPhysicsService()).thenReturn(physicsService);
        when(physicsService.computeCollidingEntities(any(AABBd.class), any(AABBOverlapFilter.class))).thenAnswer(invocation -> {
            AABBOverlapFilter<Entity> filter = invocation.getArgument(1);
            return filter.test(entity) ? List.of(entity) : List.of();
        });
    }

    @Test
    void shearsShearAdultUnshearedSheepAndDropWool() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var sheep = mock(EntitySheep.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        stubSheepLookup(dimension, entityManager, sheep);
        when(sheep.isSheared()).thenReturn(false);
        when(sheep.isBaby()).thenReturn(false);
        when(sheep.getColor()).thenReturn(DyeColor.WHITE);
        when(sheep.getLocation()).thenReturn(new Location3d(0.5, 64.5, -0.5, dimension));

        var item = ItemTypes.SHEARS.createItemStack(1);
        var result = behavior.dispense(block, BlockFace.NORTH, item);

        assertTrue(result.succeeded());
        verify(sheep).setSheared(true);
        assertEquals(1, item.getDamage());
        assertSame(item, result.remainingItem());

        var stackCaptor = ArgumentCaptor.forClass(ItemStack.class);
        var posCaptor = ArgumentCaptor.forClass(Vector3d.class);
        verify(dimension).dropItem(stackCaptor.capture(), posCaptor.capture());
        assertEquals(ItemTypes.WHITE_WOOL, stackCaptor.getValue().getItemType());
        assertTrue(stackCaptor.getValue().getCount() >= 1 && stackCaptor.getValue().getCount() <= 3);
        assertEquals(0.5, posCaptor.getValue().x(), 0.001);
        assertEquals(65.0, posCaptor.getValue().y(), 0.001);
        assertEquals(-0.5, posCaptor.getValue().z(), 0.001);

        var soundCaptor = ArgumentCaptor.forClass(CustomSound.class);
        verify(dimension).addSound(any(Vector3d.class), soundCaptor.capture());
        assertEquals(SoundNames.MOB_SHEEP_SHEAR, soundCaptor.getValue().soundName());
    }

    @Test
    void shearedSheepNotShearedAgain() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var sheep = mock(EntitySheep.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        stubSheepLookup(dimension, entityManager, sheep);
        when(sheep.isSheared()).thenReturn(true);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.SHEARS.createItemStack(1));

        assertFalse(result.succeeded());
        verify(sheep, never()).setSheared(anyBoolean());
        verify(dimension, never()).dropItem(any(ItemStack.class), any(Vector3d.class));
        verify(dimension, never()).addSound(any(Vector3d.class), any(CustomSound.class));
    }

    @Test
    void babySheepNotSheared() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var sheep = mock(EntitySheep.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        stubSheepLookup(dimension, entityManager, sheep);
        when(sheep.isSheared()).thenReturn(false);
        when(sheep.isBaby()).thenReturn(true);

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.SHEARS.createItemStack(1));

        assertFalse(result.succeeded());
        verify(sheep, never()).setSheared(anyBoolean());
        verify(dimension, never()).dropItem(any(ItemStack.class), any(Vector3d.class));
    }

    @Test
    void nonSheepEntityNotAffected() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        stubSheepLookup(dimension, entityManager, mock(EntityCow.class));

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.SHEARS.createItemStack(1));

        assertFalse(result.succeeded());
        verify(dimension, never()).dropItem(any(ItemStack.class), any(Vector3d.class));
    }

    @Test
    void shearsAtOneDurabilityBreak() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var entityManager = mock(EntityManager.class);
        var sheep = mock(EntitySheep.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getEntityManager()).thenReturn(entityManager);
        stubSheepLookup(dimension, entityManager, sheep);
        when(sheep.isSheared()).thenReturn(false);
        when(sheep.isBaby()).thenReturn(false);
        when(sheep.getColor()).thenReturn(DyeColor.WHITE);
        when(sheep.getLocation()).thenReturn(new Location3d(0.5, 64.5, -0.5, dimension));

        var item = ItemTypes.SHEARS.createItemStack(1);
        item.setDamage(item.getItemType().getItemData().maxDamage() - 1);
        var result = behavior.dispense(block, BlockFace.NORTH, item);

        assertTrue(result.succeeded());
        verify(sheep).setSheared(true);
        assertSame(ItemAirStack.AIR_STACK, result.remainingItem());
    }

    @Test
    void shearsRegisteredWithCorrectBehavior() throws IllegalAccessException {
        var map = new DispenserBehaviorRegistryLoader().load(null);
        var behavior = map.get(ItemTypes.SHEARS);
        assertNotNull(behavior);
        assertEquals(ShearsDispenseBehavior.class, behavior.getClass());
    }
}