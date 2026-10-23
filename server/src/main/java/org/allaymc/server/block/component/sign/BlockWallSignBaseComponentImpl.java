package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;
import org.joml.Vector3ic;

import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * {@code BlockWallSignBaseComponentImpl} orients wall signs and removes them when
 * their attached side loses solid support. Normal and silk-touch drops both
 * resolve the configured sign item lazily, allowing block and item registration to
 * use separate initialization order.
 *
 * @author daoge_cmd
 */
public class BlockWallSignBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code dropItemType} resolves the sign item type when each drop is
     * requested. The constructor retains the supplied resolver, allowing item
     * registration to finish before that lookup occurs.
     */
    protected final Supplier<ItemType<?>> dropItemType;

    /**
     * Creates a sign component retaining its lazy drop-item resolver.
     * {@code dropItemTypeSupplier} is called on each drop request and must supply
     * a usable item type when those requests occur.
     */
    public BlockWallSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<ItemType<?>> dropItemTypeSupplier) {
        super(blockType);
        this.dropItemType = dropItemTypeSupplier;
    }

    /**
     * Creates a sign component whose drop type resolves from the supplied item
     * identifier. The constructor retains the identifier's lookup operation rather
     * than resolving its item type immediately.
     */
    public BlockWallSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItemId) {
        this(blockType, dropItemId::getItemType);
    }

    /**
     * Places the wall sign facing the clicked block face. {@code placementInfo} is
     * required, and its face ordinal sets {@code FACING_DIRECTION} before the
     * dimension processes player placement. This method performs no support check
     * before the write.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal()),
                placementInfo
        );
    }

    /**
     * Runs shared handling and checks the sign's attached wall side. A nonsolid
     * support breaks the sign only when the changed neighbor lies opposite its
     * stored facing direction.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        var signFace = Objects.requireNonNull(BlockFace.fromIndex(block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION)));
        if (face == signFace.opposite() && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    /**
     * Returns one newly created item from the lazy sign drop type.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemType.get().createItemStack(1));
    }

    /**
     * Returns one newly created sign item for the silk-touch path.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return dropItemType.get().createItemStack(1);
    }
}
