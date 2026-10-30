package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.enums.WallConnectionType;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.EnumMap;
import java.util.Map;

/**
 * {@code BlockWallBaseComponentImpl} derives wall arm connections, arm heights, and
 * the center post from neighboring block states. Each horizontal face first decides
 * whether its neighbor can connect, then combines that connection with the shape
 * above to select a short or tall arm. The post decision uses those completed arms
 * rather than the original wall properties.
 *
 * <p>
 * Special neighboring behaviors handle gates, walls, bars, and panes before generic
 * full-face support. Above-block geometry can force tall arms, and selected
 * above-block types can force a post. Without that override, the post is omitted only
 * for two opposite arms of equal height. Placement and neighbor updates share the
 * same calculation so the resulting shape follows one set of connection rules.
 *
 * @author IWareQ
 */
public class BlockWallBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates wall base behavior for the supplied block type.
     */
    public BlockWallBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards the neighbor event and recalculates wall connections and the center
     * post. The dimension receives a replacement only when those calculated
     * properties differ from the current block snapshot.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var updatedState = updateConnectionsAndPost(block);
        if (!block.getBlockState().equals(updatedState)) {
            block.getDimension().setBlockState(block.getPosition(), updatedState);
        }
    }

    /**
     * Calculates wall connections and the center post before inherited placement
     * processing. {@code placementInfo} may be {@code null}, and the final result is
     * the inherited dimension write outcome.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var block = new Block(blockState, new Position3i(placeBlockPos, dimension));
        blockState = updateConnectionsAndPost(block);
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Returns a wall state whose horizontal arms and post reflect neighboring blocks.
     * Each face first becomes connected or empty, and connected faces use the above
     * block to select their height.
     *
     * <p>
     * The post calculation receives this evolving state after all four connection
     * properties have been processed. That ordering matters because equal opposite
     * arms can suppress a post, while corners, unequal heights, and selected
     * above-block states keep it. The input snapshot is replaced as properties change
     * rather than being written to the dimension here.
     */
    private BlockState updateConnectionsAndPost(Block current) {
        var aboveBlock = current.offsetPos(BlockFace.UP);

        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (canConnect(current, face)) {
                current = connect(current, aboveBlock.getBlockState(), face);
            } else {
                current = current.setPropertyValue(getWallConnectionProperty(face), WallConnectionType.NONE);
            }
        }

        return current.setPropertyValue(BlockPropertyTypes.WALL_POST_BIT, shouldHavePost(current.getBlockState(), aboveBlock.getBlockState())).getBlockState();
    }

    /**
     * Returns the wall snapshot with one connected arm set short or tall. An above
     * wall supplies its corresponding connection property directly, while other above
     * blocks contribute their lower-face geometry.
     *
     * <p>
     * Collision solids are preferred, with general shape solids used when collision
     * geometry is empty. A solid must begin at the lower boundary and overlap both
     * the central strip and a selected edge to make the arm tall. The edge tests use
     * the side opposite each {@code BlockFace} offset: {@code NORTH} uses high Z,
     * {@code SOUTH} uses low Z, {@code WEST} uses high X, and {@code EAST} uses low
     * X. Otherwise the connected arm remains short.
     */
    private Block connect(Block current, BlockState above, BlockFace face) {
        var property = getWallConnectionProperty(face);

        boolean shouldBeTall;
        if (above.getBehavior() instanceof BlockWallBehavior) {
            shouldBeTall = above.getPropertyValue(property) != WallConnectionType.NONE;
        } else {
            var shape = above.getBlockStateData().collisionShape();
            if (shape.getSolids().isEmpty()) {
                // An empty collision shape supplies no arm-height geometry. Use the
                // general shape so noncolliding solids can still determine whether
                // the connected wall arm becomes tall.
                shape = above.getBlockStateData().shape();
            }

            shouldBeTall = shape.getSolids().stream().anyMatch(solid -> {
                if (solid.minY() != 0) {
                    return false;
                }
                // Combine the central half-width strip with the per-face edge test.
                // The edge mapping below uses the side opposite each BlockFace
                // offset rather than the side named by that offset.
                var xOverlap = solid.minX() < 0.75 && solid.maxX() > 0.25;
                var zOverlap = solid.minZ() < 0.75 && solid.maxZ() > 0.25;

                return switch (face) {
                    case NORTH -> xOverlap && solid.maxZ() > 0.75;
                    case SOUTH -> xOverlap && solid.minZ() < 0.25;
                    case WEST -> zOverlap && solid.maxX() > 0.75;
                    case EAST -> zOverlap && solid.minX() < 0.25;
                    default -> false;
                };
            });
        }

        return current.setPropertyValue(property, shouldBeTall ? WallConnectionType.TALL : WallConnectionType.SHORT);
    }

    /**
     * Returns whether the neighboring block accepts a wall connection on this face.
     * Gates require a perpendicular axis; walls, iron bars, glass panes, or a full
     * opposing collision face also qualify.
     */
    private boolean canConnect(Block current, BlockFace face) {
        var neighbor = current.offsetPos(face);
        if (neighbor.getBehavior() instanceof BlockFenceGateBehavior) {
            var direction = neighbor.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
            return BlockFace.from(direction).getAxis() != face.getAxis();
        }

        var behavior = neighbor.getBehavior();
        return behavior instanceof BlockWallBehavior ||
               behavior instanceof BlockIronBarsBehavior ||
               behavior instanceof BlockGlassPaneBehavior ||
               neighbor.getBlockStateData().collisionShape().isFull(face.opposite());
    }

    /**
     * Returns whether above-block support rules or the completed arm pattern require
     * a center post. Selected banners, tripwire, signs, upright torches, and
     * above-wall posts can force the result.
     *
     * <p>
     * Without an above-block override, exactly two connections are required before
     * omission is possible. Those two arms must point in opposite directions and have
     * matching heights; corners and unequal arms keep the post. The method reads the
     * calculated wall state, so callers must compute its arm properties before asking
     * for this result.
     */
    private boolean shouldHavePost(BlockState current, BlockState above) {
        var aboveType = above.getBlockType();

        var aboveBehavior = above.getBehavior();
        var shouldBePost = aboveType == BlockTypes.STANDING_BANNER ||
                           aboveType == BlockTypes.TRIP_WIRE ||
                           aboveBehavior instanceof BlockSignBehavior;

        if (aboveBehavior instanceof BlockTorchBehavior) {
            shouldBePost = above.getPropertyValue(BlockPropertyTypes.TORCH_FACING_DIRECTION) == TorchFacingDirection.TOP;
        }

        if (aboveBehavior instanceof BlockWallBehavior) {
            shouldBePost = above.getPropertyValue(BlockPropertyTypes.WALL_POST_BIT);
        }

        if (shouldBePost) {
            return true;
        }

        // Above-block overrides have already forced required posts. Only the
        // completed connection pattern can suppress the remaining post for equal,
        // opposite arms.
        var connections = getWallConnections(current);
        if (connections.size() != 2) {
            return true;
        }

        var iterator = connections.entrySet().iterator();
        var a = iterator.next();
        var b = iterator.next();
        return a.getValue() != b.getValue() || a.getKey().opposite() != b.getKey();
    }

    /**
     * Returns a new mutable map containing only nonempty horizontal wall connections.
     * Changes to this map do not alter the block state, and faces with
     * {@code WallConnectionType.NONE} are omitted.
     */
    public Map<BlockFace, WallConnectionType> getWallConnections(BlockState blockState) {
        Map<BlockFace, WallConnectionType> connections = new EnumMap<>(BlockFace.class);
        for (var blockFace : BlockFace.getHorizontalBlockFaces()) {
            var connectionType = blockState.getPropertyValue(getWallConnectionProperty(blockFace));
            if (connectionType != WallConnectionType.NONE) {
                connections.put(blockFace, connectionType);
            }
        }

        return connections;
    }

    /**
     * Returns the directional wall property for a horizontal block face. Vertical
     * faces and other unsupported directions throw {@code IllegalArgumentException},
     * so callers must choose one of the four horizontal faces.
     */
    private EnumPropertyType<WallConnectionType> getWallConnectionProperty(BlockFace face) {
        return switch (face) {
            case NORTH -> BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH;
            case SOUTH -> BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH;
            case EAST -> BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST;
            case WEST -> BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST;
            default -> throw new IllegalArgumentException("Unsupported face: " + face);
        };
    }
}
