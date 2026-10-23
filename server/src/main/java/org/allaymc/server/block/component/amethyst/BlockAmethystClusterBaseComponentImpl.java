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
 * {@code BlockAmethystClusterBaseComponentImpl} handles directional placement,
 * support checks, and ordinary drops for amethyst buds and clusters. Full attachment
 * surfaces support each facing, mature clusters select Fortune-adjusted shard drops,
 * and immature buds return an empty ordinary drop set.
 *
 * @author daoge_cmd
 */
public class BlockAmethystClusterBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code BASE_DROP_COUNT} supplies the unenchanted shard count used as both
     * bounds of the mature cluster drop distribution.
     */
    private static final int BASE_DROP_COUNT = 4;

    /**
     * Creates amethyst bud and cluster base behavior for the supplied block type.
     */
    public BlockAmethystClusterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places an amethyst bud or cluster facing away from a full attachment surface. A
     * {@code null} {@code placementInfo} preserves the incoming orientation and skips
     * the player support validation.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            var clickedFace = placementInfo.blockFace();
            blockState = blockState.setPropertyValue(MINECRAFT_BLOCK_FACE, toMinecraftBlockFace(clickedFace));
            var attachedFace = clickedFace.opposite();
            var attachedPos = attachedFace.offsetPos(placeBlockPos);
            var attachedState = dimension.getBlockState(attachedPos);
            if (!attachedState.getBlockStateData().collisionShape().isFull(clickedFace)) {
                return false;
            }
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Forwards the update and checks support when the neighbor opposite the bud
     * facing changes. A support lacking a full face breaks the bud or cluster through
     * the normal break path.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var budFacing = toBlockFace(block.getPropertyValue(MINECRAFT_BLOCK_FACE));
        if (face != budFacing.opposite()) {
            return;
        }
        if (!neighbor.getBlockStateData().collisionShape().isFull(budFacing)) {
            block.breakBlock();
        }
    }

    /**
     * Returns Fortune-adjusted amethyst shards only for a mature cluster. Smaller
     * buds return an empty set, and a {@code null} tool uses the Fortune helper's
     * unenchanted distribution.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (this.blockType == BlockTypes.AMETHYST_CLUSTER) {
            var count = FortuneDropHelper.weighted(usedItem, BASE_DROP_COUNT, BASE_DROP_COUNT);
            return Set.of(ItemTypes.AMETHYST_SHARD.createItemStack(count));
        }

        return Set.of();
    }

    /**
     * Returns the block property face with the supplied face's ordinal. This
     * conversion depends on {@code BlockFace} and {@code MinecraftBlockFace}
     * preserving the same six-direction declaration order for each direction.
     */
    protected static MinecraftBlockFace toMinecraftBlockFace(BlockFace blockFace) {
        // Both face enums list down, up, north, south, west, and east in the same
        // order, allowing this property conversion to preserve direction.
        return MinecraftBlockFace.values()[blockFace.ordinal()];
    }

    /**
     * Returns the world block face with the property face's ordinal. This reverse
     * conversion depends on both face enums retaining the same declaration order for
     * all six directions.
     */
    protected static BlockFace toBlockFace(MinecraftBlockFace minecraftBlockFace) {
        // Support checks need world offsets rather than property labels. Matching
        // enum declaration order makes the reverse ordinal conversion recover the
        // original face.
        return BlockFace.values()[minecraftBlockFace.ordinal()];
    }
}
