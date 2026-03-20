package org.allaymc.server.block.component.amethyst;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftBlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_BLOCK_FACE;

/**
 * Shared base component for amethyst buds (small, medium, large) and amethyst cluster.
 * Handles directional placement and support block validation.
 *
 * @author daoge_cmd
 */
public class BlockAmethystClusterBaseComponentImpl extends BlockBaseComponentImpl {

    private static final int BASE_DROP_COUNT = 4;

    public BlockAmethystClusterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            var clickedFace = placementInfo.blockFace();
            blockState = blockState.setPropertyValue(MINECRAFT_BLOCK_FACE, toMinecraftBlockFace(clickedFace));

            // Verify the attachment block has a full surface on the attached face
            var attachedFace = clickedFace.opposite();
            var attachedPos = attachedFace.offsetPos(placeBlockPos);
            var attachedState = dimension.getBlockState(attachedPos);
            if (!attachedState.getBlockStateData().collisionShape().isFull(clickedFace)) {
                return false;
            }
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var budFacing = toBlockFace(block.getPropertyValue(MINECRAFT_BLOCK_FACE));
        // Check if the neighbor that changed is the support block (opposite of facing direction)
        if (face != budFacing.opposite()) {
            return;
        }

        // Break if the support block no longer has a full surface
        if (!neighbor.getBlockStateData().collisionShape().isFull(budFacing)) {
            block.breakBlock();
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (this.blockType == BlockTypes.AMETHYST_CLUSTER) {
            var count = FortuneDropHelper.weighted(usedItem, BASE_DROP_COUNT, BASE_DROP_COUNT);
            return Set.of(ItemTypes.AMETHYST_SHARD.createItemStack(count));
        }

        return Set.of();
    }

    protected static MinecraftBlockFace toMinecraftBlockFace(BlockFace blockFace) {
        return MinecraftBlockFace.values()[blockFace.ordinal()];
    }

    protected static BlockFace toBlockFace(MinecraftBlockFace minecraftBlockFace) {
        return BlockFace.values()[minecraftBlockFace.ordinal()];
    }
}
