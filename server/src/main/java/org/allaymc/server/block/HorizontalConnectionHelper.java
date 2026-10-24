package org.allaymc.server.block;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.interfaces.BlockShulkerBoxBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Maintains the four {@code minecraft:connection_*} states that fences, panes, bars and trip wire carry since 1.26.50.
 * <p>
 * The client no longer derives these sides from the neighbors, so the server has to keep them up to date when such a
 * block is placed and whenever one of its neighbors changes.
 */
@UtilityClass
public final class HorizontalConnectionHelper {

    /**
     * Returns the connection state stored for a horizontal side.
     *
     * @param face a horizontal face
     * @return the matching {@code minecraft:connection_*} property
     * @throws IllegalArgumentException if {@code face} is not horizontal
     */
    public static BooleanPropertyType connectionProperty(BlockFace face) {
        return switch (face) {
            case NORTH -> BlockPropertyTypes.MINECRAFT_CONNECTION_NORTH;
            case SOUTH -> BlockPropertyTypes.MINECRAFT_CONNECTION_SOUTH;
            case EAST -> BlockPropertyTypes.MINECRAFT_CONNECTION_EAST;
            case WEST -> BlockPropertyTypes.MINECRAFT_CONNECTION_WEST;
            default -> throw new IllegalArgumentException("Unsupported face: " + face);
        };
    }

    /**
     * Returns {@code state} with every horizontal side connected exactly when {@code connects} accepts it.
     *
     * @param state    a block state that has the four connection properties
     * @param connects decides whether the side facing the given direction is connected
     * @return the updated state, or {@code state} itself when nothing changed
     */
    public static BlockState withConnections(BlockState state, Predicate<BlockFace> connects) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            state = state.setPropertyValue(connectionProperty(face), connects.test(face));
        }
        return state;
    }

    /**
     * Returns whether fences, panes and bars attach to the side of {@code neighbor} that faces back along {@code face}.
     * <p>
     * They attach to any full collision face except leaves, barriers, pumpkins, melons and shulker boxes, which matches
     * what BDS 1.26.52 resolves for these states.
     *
     * @param neighbor the neighbor block state
     * @param face     the direction from the connecting block to the neighbor
     * @return {@code true} if the neighbor offers a solid side to connect to
     */
    public static boolean isSolidSide(BlockState neighbor, BlockFace face) {
        var type = neighbor.getBlockType();
        if (type.hasBlockTag(BlockTags.LEAVES) ||
            type == BlockTypes.BARRIER ||
            type == BlockTypes.PUMPKIN ||
            type == BlockTypes.CARVED_PUMPKIN ||
            type == BlockTypes.LIT_PUMPKIN ||
            type == BlockTypes.MELON_BLOCK ||
            neighbor.getBehavior() instanceof BlockShulkerBoxBehavior) {
            return false;
        }
        return neighbor.getBlockStateData().collisionShape().isFull(face.opposite());
    }

    /**
     * Returns a lookup of the layer 0 neighbors of {@code pos} by direction.
     *
     * @param dimension the dimension the position belongs to
     * @param pos       the position whose neighbors are looked up
     * @return a function resolving the neighbor block state on the given side
     */
    public static Function<BlockFace, BlockState> neighbors(Dimension dimension, Vector3ic pos) {
        return face -> dimension.getBlockState(face.offsetPos(pos));
    }
}
