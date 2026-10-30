package org.allaymc.server.block.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCorner;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.world.manager.AllayBlockUpdateManager;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.allaymc.api.block.property.enums.MinecraftCorner.*;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;
import static org.allaymc.server.block.BlockPlaceHelper.EWSN_DIRECTION_4_MAPPER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * {@code BlockStairsBaseComponentImplTest} verifies corner selection and real
 * neighbor-queue propagation against a dimension backed by an in-memory block map.
 */
@ExtendWith(AllayTestExtension.class)
class BlockStairsBaseComponentImplTest {
    /**
     * {@code ORIGIN} locates the central stair away from vertical boundaries.
     * Tests retain this coordinate and derive separate neighboring vectors.
     */
    private static final Vector3ic ORIGIN = new Vector3i(0, 64, 0);

    /**
     * {@code blocks} stores main-layer fixture states by copied coordinates.
     * The mocked dimension reads and writes this map; absent positions are air.
     */
    private final Map<Vector3ic, BlockState> blocks = new HashMap<>();
    /** Mocked fixture dimension. */
    private Dimension dimension;
    /** Actual neighbor notification queue. */
    private TestUpdateManager updates;
    /** Accepted fixture write count. */
    private int writes;

    /**
     * Connects dimension reads and writes to main-layer fixtures and the real
     * neighbor manager. Accepted writes enqueue updates through the dimension's
     * default implementation; placement callbacks are invoked explicitly by tests.
     */
    @BeforeEach
    void setUp() {
        dimension = mock(Dimension.class, CALLS_REAL_METHODS);
        updates = new TestUpdateManager(dimension);
        doReturn(updates).when(dimension).getBlockUpdateManager();
        doReturn(true).when(dimension).isYInRange(anyDouble());
        doAnswer(invocation -> {
            int layer = invocation.getArgument(3);
            var pos = new Vector3i(invocation.<Integer>getArgument(0), invocation.<Integer>getArgument(1), invocation.<Integer>getArgument(2));
            return layer == 0 ? stateAt(pos) : BlockTypes.AIR.getDefaultState();
        }).when(dimension).getBlockState(anyInt(), anyInt(), anyInt(), anyInt());
        doAnswer(invocation -> {
            var pos = new Vector3i(invocation.<Integer>getArgument(0), invocation.<Integer>getArgument(1), invocation.<Integer>getArgument(2));
            var previous = stateAt(pos);
            blocks.put(pos, invocation.getArgument(3));
            writes++;
            if (invocation.<Boolean>getArgument(6)) {
                dimension.updateAround(pos, previous);
            }
            return true;
        }).when(dimension).setBlockState(anyInt(), anyInt(), anyInt(), any(BlockState.class), anyInt(),
                anyBoolean(), anyBoolean(), anyBoolean(), nullable(PlayerInteractInfo.class));
    }

    /**
     * Places each corner configuration in every facing and occupied half.
     * A differently textured neighboring stair must produce the expected corner
     * while preserving the placed stair's facing and half.
     */
    @ParameterizedTest
    @MethodSource("cornerCases")
    void derivesAllCornersOnPlacement(BlockFace facing, boolean upsideDown, BlockFace neighborSide,
                                     BlockFace neighborFacing, MinecraftCorner corner) {
        var current = stairs(BlockTypes.OAK_STAIRS, facing, upsideDown);
        blocks.put(neighborSide.offsetPos(ORIGIN), stairs(BlockTypes.BIRCH_STAIRS, neighborFacing, upsideDown));

        assertTrue(current.getBehavior().place(dimension, current, ORIGIN, null));

        assertEquals(current.setPropertyValue(MINECRAFT_CORNER, corner), stateAt(ORIGIN));
    }

    /**
     * Supplies rotated inner and outer corner fixtures for both stair halves.
     */
    private static Stream<Arguments> cornerCases() {
        var cases = Stream.<Arguments>builder();
        for (var facing : BlockFace.getHorizontalBlockFaces()) {
            for (boolean upsideDown : new boolean[]{false, true}) {
                cases.add(Arguments.of(facing, upsideDown, facing, facing.rotateYCCW(), OUTER_LEFT));
                cases.add(Arguments.of(facing, upsideDown, facing, facing.rotateY(), OUTER_RIGHT));
                cases.add(Arguments.of(facing, upsideDown, facing.opposite(), facing.rotateYCCW(), INNER_LEFT));
                cases.add(Arguments.of(facing, upsideDown, facing.opposite(), facing.rotateY(), INNER_RIGHT));
            }
        }
        return cases.build();
    }

    /**
     * Adds a same-facing continuation beside each possible bend in east-facing
     * stairs. Both upper and lower runs must remain straight until that blocking
     * continuation is removed.
     */
    @ParameterizedTest
    @CsvSource({"EAST,NORTH,SOUTH", "EAST,SOUTH,NORTH", "WEST,NORTH,NORTH", "WEST,SOUTH,SOUTH"})
    void parallelContinuationPreventsCorners(BlockFace neighborSide, BlockFace neighborFacing, BlockFace blockingSide) {
        for (boolean upsideDown : new boolean[]{false, true}) {
            blocks.clear();
            var current = stairs(BlockTypes.OAK_STAIRS, BlockFace.EAST, upsideDown);
            blocks.put(neighborSide.offsetPos(ORIGIN), stairs(BlockTypes.BIRCH_STAIRS, neighborFacing, upsideDown));
            blocks.put(blockingSide.offsetPos(ORIGIN), stairs(BlockTypes.POPLAR_STAIRS, BlockFace.EAST, upsideDown));

            assertTrue(current.getBehavior().place(dimension, current, ORIGIN, null));
            assertEquals(NONE, stateAt(ORIGIN).getPropertyValue(MINECRAFT_CORNER));
        }
    }

    /**
     * Places stairs beside solid blocks, opposite halves, and parallel stairs.
     * None of those neighbors can form a corner, even when the incoming state
     * still carries a corner from another arrangement.
     */
    @Test
    void resetsCornersForIncompatibleNeighbors() {
        var current = stairs(BlockTypes.OAK_STAIRS, BlockFace.EAST, false).setPropertyValue(MINECRAFT_CORNER, INNER_LEFT);
        for (var neighbor : List.of(BlockTypes.STONE.getDefaultState(),
                stairs(BlockTypes.BIRCH_STAIRS, BlockFace.NORTH, true),
                stairs(BlockTypes.BIRCH_STAIRS, BlockFace.EAST, false),
                stairs(BlockTypes.BIRCH_STAIRS, BlockFace.WEST, false))) {
            blocks.put(BlockFace.EAST.offsetPos(ORIGIN), neighbor);
            assertTrue(current.getBehavior().place(dimension, current, ORIGIN, null));
            assertEquals(NONE, stateAt(ORIGIN).getPropertyValue(MINECRAFT_CORNER));
        }
    }

    /**
     * Provides competing front and back bends around copper stairs. The front
     * neighbor must select an outer corner before the back neighbor's inner
     * candidate, including when their stair materials differ.
     */
    @Test
    void outerCornerTakesPriorityForCopperStairs() {
        var current = stairs(BlockTypes.WAXED_CUT_COPPER_STAIRS, BlockFace.EAST, false);
        blocks.put(BlockFace.EAST.offsetPos(ORIGIN), stairs(BlockTypes.BIRCH_STAIRS, BlockFace.NORTH, false));
        blocks.put(BlockFace.WEST.offsetPos(ORIGIN), stairs(BlockTypes.POPLAR_STAIRS, BlockFace.SOUTH, false));

        assertTrue(current.getBehavior().place(dimension, current, ORIGIN, null));

        assertEquals(current.setPropertyValue(MINECRAFT_CORNER, OUTER_LEFT), stateAt(ORIGIN));
    }

    /**
     * Places stairs from a player-facing and underside-click interaction. Corner
     * selection must use the resulting east-facing upper half rather than the
     * orientation carried by the incoming default state.
     */
    @Test
    void selectsPlayerOrientationBeforeCalculatingCorner() {
        var player = mock(EntityPlayer.class);
        when(player.getHorizontalFace()).thenReturn(BlockFace.EAST);
        var interaction = new PlayerInteractInfo(player, ORIGIN, new Vector3f(0.5f, 0.5f, 0.5f), BlockFace.DOWN);
        blocks.put(BlockFace.EAST.offsetPos(ORIGIN), stairs(BlockTypes.BIRCH_STAIRS, BlockFace.NORTH, true));
        var current = stairs(BlockTypes.OAK_STAIRS, BlockFace.NORTH, false);

        assertTrue(current.getBehavior().place(dimension, current, ORIGIN, interaction));

        assertEquals(stairs(BlockTypes.OAK_STAIRS, BlockFace.EAST, true)
                .setPropertyValue(MINECRAFT_CORNER, OUTER_LEFT), stateAt(ORIGIN));
    }

    /**
     * Sends the postplacement callback after a direct dimension-style state write.
     * It must correct the corner once and suppress replacement callbacks when
     * publishing that derived state to clients and neighbors.
     */
    @Test
    void directPlacementRefreshesCornerWithoutRepeatingPlacementCallbacks() {
        var current = stairs(BlockTypes.OAK_STAIRS, BlockFace.EAST, false);
        blocks.put(ORIGIN, current);
        blocks.put(BlockFace.EAST.offsetPos(ORIGIN), stairs(BlockTypes.BIRCH_STAIRS, BlockFace.NORTH, false));
        var oldBlock = new Block(BlockTypes.AIR.getDefaultState(), new Position3i(ORIGIN, dimension));

        current.getBehavior().afterPlaced(oldBlock, current, null);

        assertEquals(OUTER_LEFT, stateAt(ORIGIN).getPropertyValue(MINECRAFT_CORNER));
        verify(dimension).setBlockState(0, 64, 0, stateAt(ORIGIN), 0, true, true, false, null);
        current.getBehavior().afterPlaced(oldBlock, stateAt(ORIGIN), null);
        assertEquals(1, writes);
    }

    /**
     * Adds, rotates, and removes an adjacent stair using queued neighbor updates.
     * Each topology change must reach the existing stair, and draining reciprocal
     * notifications must stop without rewriting an already correct corner.
     */
    @Test
    void neighborUpdatesPropagateAndSettle() {
        blocks.put(ORIGIN, stairs(BlockTypes.OAK_STAIRS, BlockFace.EAST, false));
        var neighborPos = BlockFace.EAST.offsetPos(ORIGIN);

        dimension.setBlockState(neighborPos, stairs(BlockTypes.BIRCH_STAIRS, BlockFace.NORTH, false));
        drainUpdates();
        assertEquals(OUTER_LEFT, stateAt(ORIGIN).getPropertyValue(MINECRAFT_CORNER));

        dimension.setBlockState(neighborPos, stairs(BlockTypes.BIRCH_STAIRS, BlockFace.SOUTH, false));
        drainUpdates();
        assertEquals(OUTER_RIGHT, stateAt(ORIGIN).getPropertyValue(MINECRAFT_CORNER));

        dimension.setBlockState(neighborPos, BlockTypes.AIR.getDefaultState());
        drainUpdates();
        assertEquals(NONE, stateAt(ORIGIN).getPropertyValue(MINECRAFT_CORNER));
        assertEquals(6, writes);

        dimension.updateAround(neighborPos);
        drainUpdates();
        assertEquals(6, writes);
    }

    /**
     * Rejects a placement performed without player context in the mocked dimension.
     * The stair behavior must report that rejection instead of claiming success
     * while leaving the original block unchanged.
     */
    @Test
    void reportsRejectedPlacementWithoutPlayerContext() {
        doReturn(false).when(dimension).setBlockState(anyInt(), anyInt(), anyInt(), any(BlockState.class), anyInt(),
                anyBoolean(), anyBoolean(), anyBoolean(), nullable(PlayerInteractInfo.class));
        var current = stairs(BlockTypes.OAK_STAIRS, BlockFace.EAST, false);

        assertFalse(current.getBehavior().place(dimension, current, ORIGIN, null));
        assertEquals(BlockTypes.AIR.getDefaultState(), stateAt(ORIGIN));
    }

    /**
     * Creates a straight stair fixture with the selected material, facing, and half.
     * The type must expose the canonical stair properties, and the facing must be
     * horizontal. Other property values remain at their registered defaults.
     */
    private static BlockState stairs(BlockType<?> type, BlockFace facing, boolean upsideDown) {
        return type.getDefaultState()
                .setPropertyValue(WEIRDO_DIRECTION, EWSN_DIRECTION_4_MAPPER.get(facing))
                .setPropertyValue(UPSIDE_DOWN_BIT, upsideDown)
                .setPropertyValue(MINECRAFT_CORNER, NONE);
    }

    /**
     * Returns the main-layer fixture state, using air for an absent position.
     * Looking up an empty coordinate does not insert a map entry, so neighbor
     * queries leave the fixture topology unchanged.
     */
    private BlockState stateAt(Vector3ic pos) {
        return blocks.getOrDefault(pos, BlockTypes.AIR.getDefaultState());
    }

    /**
     * Drains at most ten neighbor batches and rejects a nonsettling fixture.
     * The bound leaves room for reciprocal notifications while detecting repeated
     * writes that would otherwise keep these finite stair arrangements active.
     */
    private void drainUpdates() {
        // Each tick consumes its initial batch, so allow later ticks to process
        // reciprocal notifications produced by the previous batch.
        for (int i = 0; i < 10 && updates.hasPendingUpdates(); i++) {
            updates.tick();
        }
        assertFalse(updates.hasPendingUpdates(), "Stair neighbor updates did not settle");
    }

    /**
     * {@code TestUpdateManager} exposes queue completion while retaining production
     * neighbor dispatch and event handling.
     */
    private static class TestUpdateManager extends AllayBlockUpdateManager {
        /**
         * Creates the real update manager for the fixture dimension.
         */
        TestUpdateManager(Dimension dimension) {
            super(dimension);
        }

        /**
         * Returns whether another batch of neighbor notifications remains queued.
         */
        boolean hasPendingUpdates() {
            return !neighborUpdates.isEmpty();
        }
    }
}
