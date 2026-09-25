package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemShulkerBoxBaseComponent;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class ShulkerBoxDispenseBehaviorTest {

    private final ShulkerBoxDispenseBehavior behavior = new ShulkerBoxDispenseBehavior();

    @Test
    void dispenseShulkerBoxAtAirPlacesMatchingBlockAndTransfersStoredItems() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);
        var blockEntity = mock(BlockEntityShulkerBox.class);
        var container = mock(Container.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(BlockTypes.AIR.getDefaultState());
        when(dimension.setBlockState(anyInt(), anyInt(), anyInt(), any(BlockState.class), any())).thenReturn(true);
        when(dimension.getBlockEntity(any(Vector3ic.class))).thenReturn(blockEntity);
        when(blockEntity.getContainer()).thenReturn(container);

        var item = ItemTypes.RED_SHULKER_BOX.createItemStack(1);
        var stored = ItemTypes.DIAMOND.createItemStack(5);
        ((ItemShulkerBoxBaseComponent) item).setStoredItems(Map.of(0, stored));

        var result = behavior.dispense(block, BlockFace.NORTH, item);

        assertTrue(result.succeeded());
        var stateCaptor = ArgumentCaptor.forClass(BlockState.class);
        verify(dimension).setBlockState(eq(0), eq(64), eq(-1), stateCaptor.capture(), isNull());
        assertEquals(BlockTypes.RED_SHULKER_BOX, stateCaptor.getValue().getBlockType());
        verify(container).setItemStack(0, stored, false);
    }

    @Test
    void dispenseAtSolidBlockFailsWithoutPlacing() {
        var dimension = mock(Dimension.class);
        var block = mock(Block.class);

        when(block.getDimension()).thenReturn(dimension);
        when(block.getPosition()).thenReturn(new Position3i(0, 64, 0, dimension));
        when(dimension.getBlockState(any(Vector3ic.class))).thenReturn(BlockTypes.STONE.getDefaultState());

        var result = behavior.dispense(block, BlockFace.NORTH, ItemTypes.RED_SHULKER_BOX.createItemStack(1));

        assertFalse(result.succeeded());
        verify(dimension, never()).setBlockState(anyInt(), anyInt(), anyInt(), any(BlockState.class), any());
    }

    @Test
    void allShulkerBoxItemsRegistered() throws IllegalAccessException {
        var map = new DispenserBehaviorRegistryLoader().load(null);
        var registered = 0;
        for (var field : ItemTypes.class.getFields()) {
            if (!field.getName().endsWith("SHULKER_BOX") || field.getName().equals("SHULKER_BOX")) {
                continue;
            }
            var itemType = (ItemType<?>) field.get(null);
            assertNotNull(itemType.getBlockType(), field.getName() + " should map to a block type");
            var behavior = map.get(itemType);
            assertNotNull(behavior, field.getName() + " not registered");
            assertEquals(ShulkerBoxDispenseBehavior.class, behavior.getClass());
            registered++;
        }
        assertEquals(17, registered);
        assertNull(ItemTypes.SHULKER_BOX.getBlockType());
    }
}