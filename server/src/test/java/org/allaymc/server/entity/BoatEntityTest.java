package org.allaymc.server.entity;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.data.BoatVariant;
import org.allaymc.api.entity.interfaces.EntityBoat;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.manager.EntityManager;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityBoatImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.joml.Vector2f;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(AllayTestExtension.class)
class BoatEntityTest {
    private final Dimension dimension = Mockito.mock(Dimension.class);

    @Test
    void boatTypeHasFunctionalComponentsAndVanillaBounds() {
        EntityBoat boat = EntityTypes.BOAT.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .pos(1, 2, 3)
                .build());

        assertInstanceOf(EntityPhysicsComponent.class, boat);
        assertEquals(2, boat.getPassengerCapacity());
        assertEquals(1.4, boat.getBaseAABB().lengthX(), 0.00001);
        assertEquals(0.455, boat.getBaseAABB().lengthY(), 0.00001);
        assertEquals(BoatVariant.OAK, boat.getBoatVariant());
    }

    @Test
    void variantSurvivesNbtRoundTrip() {
        var boat = EntityTypes.BOAT.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .pos(1, 2, 3)
                .build());
        boat.setBoatVariant(BoatVariant.BAMBOO);

        var loaded = EntityTypes.BOAT.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .nbt(boat.saveNBT())
                .build());

        assertEquals(BoatVariant.BAMBOO, loaded.getBoatVariant());
    }

    @Test
    void allOrdinaryBoatItemsAreRegistered() {
        assertNotNull(ItemTypes.BOAT);
        assertNotNull(ItemTypes.OAK_BOAT);
        assertNotNull(ItemTypes.SPRUCE_BOAT);
        assertNotNull(ItemTypes.BIRCH_BOAT);
        assertNotNull(ItemTypes.JUNGLE_BOAT);
        assertNotNull(ItemTypes.ACACIA_BOAT);
        assertNotNull(ItemTypes.DARK_OAK_BOAT);
        assertNotNull(ItemTypes.MANGROVE_BOAT);
        assertNotNull(ItemTypes.BAMBOO_RAFT);
        assertNotNull(ItemTypes.CHERRY_BOAT);
        assertNotNull(ItemTypes.PALE_OAK_BOAT);
    }

    @Test
    void passengersFillTwoSeatsAndSecondPassengerIsPromoted() {
        var boat = createBoat();
        var first = mockPassenger();
        var second = mockPassenger();
        var third = mockPassenger();

        assertTrue(boat.addPassenger(first));
        assertTrue(boat.addPassenger(second));
        assertFalse(boat.addPassenger(third));
        assertSame(first, boat.getControllingPassenger());
        verify(first).setVehicle(boat);
        verify(second).setVehicle(boat);

        assertTrue(boat.removePassenger(first));
        assertSame(second, boat.getControllingPassenger());
        verify(first).setVehicle(null);
    }

    @Test
    void controllerInputAcceleratesBoatAndWaterProvidesBuoyancy() {
        var boat = createBoat();
        var controller = mockPassenger();
        assertTrue(boat.addPassenger(controller));

        boat.setPaddleInput(controller, new Vector2f(0, 1), true, true);
        ((EntityBaseComponentImpl) ((EntityBoatImpl) boat).getBaseComponent()).tick(1);
        assertTrue(boat.getMotion().lengthSquared() > 0);

        boat.setMotion(new Vector3d(0, -0.05, 0));
        var floatingMotion = boat.updateMotion(new EntityPhysicsComponent.LiquidState(true, false));
        assertTrue(floatingMotion.y() > -0.05);
    }

    @Test
    void boatItemPlacesConfiguredEntity() {
        var manager = Mockito.mock(EntityManager.class);
        var physics = Mockito.mock(EntityPhysicsEngine.class);
        var player = mockPassenger();
        var pos = new Vector3i(4, 5, 6);
        when(dimension.getBlockState(pos)).thenReturn(BlockTypes.AIR.getDefaultState());
        when(dimension.getEntityManager()).thenReturn(manager);
        when(manager.getPhysicsService()).thenReturn(physics);
        when(physics.computeCollidingEntities(any(AABBdc.class))).thenReturn(java.util.List.of());

        var item = ItemTypes.MANGROVE_BOAT.createItemStack();
        var used = item.useItemOnBlock(dimension, pos, new PlayerInteractInfo(player, pos, new org.joml.Vector3f(), org.allaymc.api.block.data.BlockFace.UP));

        assertTrue(used);
        var entityCaptor = org.mockito.ArgumentCaptor.forClass(Entity.class);
        verify(manager).addEntity(entityCaptor.capture());
        assertEquals(BoatVariant.MANGROVE, ((EntityBoat) entityCaptor.getValue()).getBoatVariant());
        verify(player).tryConsumeItemInHand();
    }

    private EntityBoat createBoat() {
        return EntityTypes.BOAT.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .pos(1, 2, 3)
                .build());
    }

    private EntityPlayer mockPassenger() {
        var player = Mockito.mock(EntityPlayer.class);
        when(player.getGameMode()).thenReturn(GameMode.SURVIVAL);
        when(player.getDimension()).thenReturn(dimension);
        when(player.getLocation()).thenReturn(new Location3d(1, 2, 3, dimension));
        when(player.getAABB()).thenReturn(new AABBd(-0.3, 0, -0.3, 0.3, 1.8, 0.3));
        when(player.trySetLocation(Mockito.any())).thenReturn(true);
        return player;
    }
}
