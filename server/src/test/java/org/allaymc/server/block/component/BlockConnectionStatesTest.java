package org.allaymc.server.block.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.tripwire.BlockTripWireBaseComponentImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3i;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * The expected values were recorded from BDS 1.26.52: the blocks were placed with /setblock and probed with
 * /testforblock, because these states are server authoritative since 1.26.50.
 */
@ExtendWith(AllayTestExtension.class)
class BlockConnectionStatesTest {

    @ParameterizedTest(name = "{0} next to {1} -> {2}")
    @CsvSource({
            "oak_fence, stone, true",
            "oak_fence, oak_leaves, false",
            "oak_fence, spruce_fence, true",
            "oak_fence, nether_brick_fence, false",
            "oak_fence, glass_pane, false",
            "oak_fence, iron_bars, false",
            "oak_fence, cobblestone_wall, false",
            "oak_fence, glass, true",
            "oak_fence, pumpkin, false",
            "oak_fence, melon_block, false",
            "oak_fence, barrier, false",
            "oak_fence, undyed_shulker_box, false",
            "oak_fence, gate_north, false",
            "oak_fence, gate_east, false",
            "oak_fence, oak_slab, false",
            "oak_fence, stairs_back, true",
            "oak_fence, stairs_front, false",
            "oak_fence, chest, false",
            "oak_fence, ice, true",
            "oak_fence, beacon, true",
            "oak_fence, crafting_table, true",
            "oak_fence, glowstone, true",
            "oak_fence, tnt, true",
            "nether_brick_fence, oak_fence, false",
            "nether_brick_fence, nether_brick_fence, true",
            "glass_pane, stone, true",
            "glass_pane, glass_pane, true",
            "glass_pane, iron_bars, true",
            "glass_pane, copper_bars, true",
            "glass_pane, cobblestone_wall, true",
            "glass_pane, oak_fence, false",
            "glass_pane, oak_leaves, false",
            "glass_pane, glass, true",
            "glass_pane, gate_north, false",
            "glass_pane, pumpkin, false",
            "iron_bars, stone, true",
            "iron_bars, glass_pane, true",
            "iron_bars, iron_bars, true",
            "iron_bars, copper_bars, true",
            "iron_bars, cobblestone_wall, true",
            "iron_bars, oak_fence, false",
            "iron_bars, oak_leaves, false",
            "iron_bars, glass, true",
            "iron_bars, gate_north, false",
            "iron_bars, pumpkin, false",
            "copper_bars, stone, true",
            "copper_bars, glass_pane, true",
            "copper_bars, iron_bars, true",
            "copper_bars, copper_bars, true",
            "copper_bars, cobblestone_wall, true",
            "copper_bars, oak_fence, false",
            "copper_bars, oak_leaves, false",
            "copper_bars, glass, true",
            "copper_bars, gate_north, false",
            "copper_bars, pumpkin, false",
            "trip_wire, trip_wire, true",
            "trip_wire, hook_facing_wire, true",
            "trip_wire, hook_facing_east, false",
            "trip_wire, hook_facing_west, false",
            "trip_wire, stone, false"
    })
    void northConnectionMatchesBds(String center, String northNeighbor, boolean expected) {
        var centerType = type(center);
        var neighbors = new EnumMap<BlockFace, BlockState>(BlockFace.class);
        neighbors.put(BlockFace.NORTH, neighbor(northNeighbor));

        var state = component(centerType).updateConnections(centerType.getDefaultState(), lookup(neighbors));

        assertEquals(expected, state.getPropertyValue(MINECRAFT_CONNECTION_NORTH));
        assertFalse(state.getPropertyValue(MINECRAFT_CONNECTION_SOUTH));
        assertFalse(state.getPropertyValue(MINECRAFT_CONNECTION_EAST));
        assertFalse(state.getPropertyValue(MINECRAFT_CONNECTION_WEST));
    }

    @Test
    void fenceConnectsOnPlaceAndDisconnectsWhenTheNeighborIsRemoved() {
        var blocks = new HashMap<Vector3i, BlockState>();
        var dimension = mockDimension(blocks);
        var fencePos = new Vector3i(0, 64, 0);
        var stonePos = new Vector3i(BlockFace.NORTH.offsetPos(fencePos));
        blocks.put(stonePos, BlockTypes.STONE.getDefaultState());
        var behavior = BlockTypes.OAK_FENCE.getBlockBehavior();

        behavior.place(dimension, BlockTypes.OAK_FENCE.getDefaultState(), fencePos, null);
        assertTrue(blocks.get(fencePos).getPropertyValue(MINECRAFT_CONNECTION_NORTH));

        var air = BlockTypes.AIR.getDefaultState();
        blocks.put(stonePos, air);
        behavior.onNeighborUpdate(
                new Block(blocks.get(fencePos), new Position3i(fencePos, dimension)),
                new Block(air, new Position3i(stonePos, dimension)),
                BlockFace.NORTH,
                BlockTypes.STONE.getDefaultState()
        );
        assertFalse(blocks.get(fencePos).getPropertyValue(MINECRAFT_CONNECTION_NORTH));
    }

    private static Dimension mockDimension(Map<Vector3i, BlockState> blocks) {
        var dimension = mock(Dimension.class, CALLS_REAL_METHODS);
        doReturn(true).when(dimension).isYInRange(anyDouble());
        doAnswer(invocation -> blocks.getOrDefault(
                new Vector3i(invocation.<Integer>getArgument(0), invocation.<Integer>getArgument(1), invocation.<Integer>getArgument(2)),
                BlockTypes.AIR.getDefaultState()
        )).when(dimension).getBlockState(anyInt(), anyInt(), anyInt(), anyInt());
        doAnswer(invocation -> {
            var pos = new Vector3i(invocation.<Integer>getArgument(0), invocation.<Integer>getArgument(1), invocation.<Integer>getArgument(2));
            blocks.put(pos, invocation.getArgument(3));
            return true;
        }).when(dimension).setBlockState(
                anyInt(), anyInt(), anyInt(), any(BlockState.class), anyInt(),
                anyBoolean(), anyBoolean(), anyBoolean(), nullable(PlayerInteractInfo.class)
        );
        return dimension;
    }

    private static BlockHorizontalConnectionBaseComponentImpl component(BlockType<?> type) {
        if (type == BlockTypes.TRIP_WIRE) {
            return new BlockTripWireBaseComponentImpl(type);
        }
        if (type == BlockTypes.GLASS_PANE) {
            return new BlockGlassPaneBaseComponentImpl(type);
        }
        if (type == BlockTypes.IRON_BARS || type == BlockTypes.COPPER_BARS) {
            return new BlockThinBaseComponentImpl(type);
        }
        return new BlockFenceBaseComponentImpl(type);
    }

    private static BlockType<?> type(String name) {
        return switch (name) {
            case "oak_fence" -> BlockTypes.OAK_FENCE;
            case "nether_brick_fence" -> BlockTypes.NETHER_BRICK_FENCE;
            case "glass_pane" -> BlockTypes.GLASS_PANE;
            case "iron_bars" -> BlockTypes.IRON_BARS;
            case "copper_bars" -> BlockTypes.COPPER_BARS;
            case "trip_wire" -> BlockTypes.TRIP_WIRE;
            default -> throw new IllegalArgumentException(name);
        };
    }

    private static BlockState neighbor(String name) {
        return switch (name) {
            case "stone" -> BlockTypes.STONE.getDefaultState();
            case "oak_leaves" -> BlockTypes.OAK_LEAVES.getDefaultState();
            case "spruce_fence" -> BlockTypes.SPRUCE_FENCE.getDefaultState();
            case "oak_fence" -> BlockTypes.OAK_FENCE.getDefaultState();
            case "nether_brick_fence" -> BlockTypes.NETHER_BRICK_FENCE.getDefaultState();
            case "glass_pane" -> BlockTypes.GLASS_PANE.getDefaultState();
            case "iron_bars" -> BlockTypes.IRON_BARS.getDefaultState();
            case "copper_bars" -> BlockTypes.COPPER_BARS.getDefaultState();
            case "cobblestone_wall" -> BlockTypes.COBBLESTONE_WALL.getDefaultState();
            case "glass" -> BlockTypes.GLASS.getDefaultState();
            case "pumpkin" -> BlockTypes.PUMPKIN.getDefaultState();
            case "melon_block" -> BlockTypes.MELON_BLOCK.getDefaultState();
            case "barrier" -> BlockTypes.BARRIER.getDefaultState();
            case "undyed_shulker_box" -> BlockTypes.UNDYED_SHULKER_BOX.getDefaultState();
            case "gate_north" -> BlockTypes.FENCE_GATE.getDefaultState().setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, MinecraftCardinalDirection.NORTH);
            case "gate_east" -> BlockTypes.FENCE_GATE.getDefaultState().setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, MinecraftCardinalDirection.EAST);
            case "oak_slab" -> BlockTypes.OAK_SLAB.getDefaultState();
            // The fence is south of these stairs: weirdo_direction 2 puts their full back side towards it, 3 the steps.
            case "stairs_back" -> stairs(2, false);
            case "stairs_front" -> stairs(3, false);
            case "chest" -> BlockTypes.CHEST.getDefaultState();
            case "ice" -> BlockTypes.ICE.getDefaultState();
            case "beacon" -> BlockTypes.BEACON.getDefaultState();
            case "crafting_table" -> BlockTypes.CRAFTING_TABLE.getDefaultState();
            case "glowstone" -> BlockTypes.GLOWSTONE.getDefaultState();
            case "tnt" -> BlockTypes.TNT.getDefaultState();
            case "trip_wire" -> BlockTypes.TRIP_WIRE.getDefaultState();
            // The hook is north of the wire, so direction 0 (south) faces the wire.
            case "hook_facing_wire" -> BlockTypes.TRIPWIRE_HOOK.getDefaultState().setPropertyValue(DIRECTION_4, 0);
            case "hook_facing_east" -> BlockTypes.TRIPWIRE_HOOK.getDefaultState().setPropertyValue(DIRECTION_4, 3);
            case "hook_facing_west" -> BlockTypes.TRIPWIRE_HOOK.getDefaultState().setPropertyValue(DIRECTION_4, 1);
            default -> throw new IllegalArgumentException(name);
        };
    }

    private static BlockState stairs(int direction, boolean upsideDown) {
        return BlockTypes.OAK_STAIRS.getDefaultState()
                .setPropertyValue(WEIRDO_DIRECTION, direction)
                .setPropertyValue(UPSIDE_DOWN_BIT, upsideDown);
    }

    private static Function<BlockFace, BlockState> lookup(Map<BlockFace, BlockState> neighbors) {
        return face -> neighbors.getOrDefault(face, BlockTypes.AIR.getDefaultState());
    }
}
