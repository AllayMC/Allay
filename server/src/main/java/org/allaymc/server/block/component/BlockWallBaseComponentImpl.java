package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.BlockFenceGateBehavior;
import org.allaymc.api.block.interfaces.BlockSignBehavior;
import org.allaymc.api.block.interfaces.BlockTorchBehavior;
import org.allaymc.api.block.interfaces.BlockWallBehavior;
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
 * @author IWareQ
 */
public class BlockWallBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockWallBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var updatedState = updateConnectionsAndPost(block);
        if (!block.getBlockState().equals(updatedState)) {
            block.getDimension().setBlockState(block.getPosition(), updatedState);
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var block = new Block(blockState, new Position3i(placeBlockPos, dimension));
        blockState = updateConnectionsAndPost(block);
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

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

    private Block connect(Block current, BlockState above, BlockFace face) {
        var property = getWallConnectionProperty(face);

        boolean shouldBeTall;
        if (above.getBehavior() instanceof BlockWallBehavior) {
            shouldBeTall = above.getPropertyValue(property) != WallConnectionType.NONE;
        } else {
            var shape = above.getBlockStateData().collisionShape();
            if (shape.getSolids().isEmpty()) {
                shape = above.getBlockStateData().shape();
            }

            shouldBeTall = shape.getSolids().stream().anyMatch(solid -> {
                if (solid.minY() != 0) {
                    return false;
                }

                // From Dragonfly
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

    private boolean canConnect(Block current, BlockFace face) {
        var neighbor = current.offsetPos(face);
        if (neighbor.getBehavior() instanceof BlockFenceGateBehavior) {
            var direction = neighbor.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
            return BlockFace.from(direction).getAxis() != face.getAxis();
        }

        var type = neighbor.getBlockType();
        return neighbor.getBehavior() instanceof BlockWallBehavior ||
               type == BlockTypes.IRON_BARS ||
               type == BlockTypes.GLASS_PANE ||
               neighbor.getBlockStateData().collisionShape().isFull(face.opposite());
    }


    /**
     * @see <a href="https://joakimthorsen.github.io/MCPropertyEncyclopedia/?selection=variants,tag_wall_post_override&filter=(tag_wall_post_override:No)#">Minecraft Block Property Encyclopedia</a>
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

        var connections = getWallConnections(current);
        if (connections.size() != 2) {
            return true;
        }

        var iterator = connections.entrySet().iterator();
        var a = iterator.next();
        var b = iterator.next();
        return a.getValue() != b.getValue() || a.getKey().opposite() != b.getKey();
    }

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
