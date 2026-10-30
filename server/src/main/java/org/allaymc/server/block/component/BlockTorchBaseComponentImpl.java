package org.allaymc.server.block.component;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code BlockTorchBaseComponentImpl} selects torch attachment faces and breaks
 * torches when support disappears. Player placement can search alternative side or
 * floor surfaces, while a shared bidirectional map translates support directions to
 * torch property values and restores them for later support checks.
 *
 * @author daoge_cmd
 */
public class BlockTorchBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code TORCH_FACING_DIRECTION_MAPPER} translates support faces to torch
     * orientation values and exposes inverse lookup for support checks.
     */
    private static final BiMap<BlockFace, TorchFacingDirection> TORCH_FACING_DIRECTION_MAPPER = EnumBiMap.create(BlockFace.class, TorchFacingDirection.class);

    // Initialize both directions of the torch orientation mapping before placement
    // and support checks use it.
    static {
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.DOWN, TorchFacingDirection.UNKNOWN);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.UP, TorchFacingDirection.TOP);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.NORTH, TorchFacingDirection.SOUTH);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.SOUTH, TorchFacingDirection.NORTH);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.WEST, TorchFacingDirection.EAST);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.EAST, TorchFacingDirection.WEST);
    }

    /**
     * Creates the torch block behavior for the supplied block type.
     */
    public BlockTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards the update and checks the torch's stored support unless the upper
     * neighbor changed. Missing support breaks the torch, and the stored direction is
     * decoded through the shared inverse mapping.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.UP) {
            return;
        }

        var torchFacingDirection = block.getPropertyValue(BlockPropertyTypes.TORCH_FACING_DIRECTION);
        var blockFace = TORCH_FACING_DIRECTION_MAPPER.inverse().get(torchFacingDirection);
        if (!canBeSupportedAt(block.offsetPos(blockFace.opposite()).getBlockState(), blockFace)) {
            block.breakBlock();
        }
    }

    /**
     * Places a torch on the clicked support or a suitable alternative face. A
     * {@code null} {@code placementInfo} writes the supplied state directly; no
     * acceptable support returns {@code false} without placement.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.blockFace();
        if (face == BlockFace.DOWN || !canBeSupportedAt(placementInfo.getClickedBlock().getBlockState(), face)) {
            // The clicked face cannot accept this torch. Search side and floor
            // supports so another adjacent surface can satisfy the attachment
            // requirement.
            face = findValidFace(dimension, placeBlockPos);
        }

        if (face == null) {
            return false;
        }

        blockState = blockState.setPropertyValue(BlockPropertyTypes.TORCH_FACING_DIRECTION, TORCH_FACING_DIRECTION_MAPPER.get(face));
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Returns a valid torch facing from south, west, north, east, then lower support
     * positions. The search returns {@code null} when no side or floor surface can
     * support this torch.
     */
    private BlockFace findValidFace(Dimension dimension, Vector3ic placeBlockPos) {
        for (var face : new BlockFace[]{BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST, BlockFace.DOWN}) {
            if (canBeSupportedAt(dimension.getBlockState(face.offsetPos(placeBlockPos)), face.opposite())) {
                return face.opposite();
            }
        }

        return null;
    }

    /**
     * Returns whether the requested face can support torch attachment. Floor
     * placement requires a center-full upper face, while side attachment requires the
     * entire requested face to be full.
     */
    private boolean canBeSupportedAt(BlockState blockState, BlockFace face) {
        var shape = blockState.getBlockStateData().collisionShape();
        return face == BlockFace.UP ? shape.isCenterFull(BlockFace.UP) : shape.isFull(face);
    }
}
