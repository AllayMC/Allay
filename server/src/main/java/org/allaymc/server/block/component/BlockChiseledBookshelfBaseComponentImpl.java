package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityChiseledBookshelf;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.ChiseledBookshelfSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockChiseledBookshelfBaseComponentImpl extends BlockBaseComponentImpl {
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityChiseledBookshelf> blockEntityHolderComponent;

    public BlockChiseledBookshelfBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        blockState = blockState.setPropertyValue(
                BlockPropertyTypes.DIRECTION_4,
                placementInfo.player().getHorizontalFace().opposite().getHorizontalIndex()
        );
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var facing = BlockFace.fromHorizontalIndex(blockState.getPropertyValue(BlockPropertyTypes.DIRECTION_4));
        if (interactInfo.blockFace() != facing) {
            return false;
        }

        var slot = calculateSlot(facing, interactInfo.clickedPos());
        var container = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension).getContainer();
        var slotItem = container.getItemStack(slot);
        var clickedBlock = new Block(dimension, pos);
        if (!slotItem.isEmptyOrAir()) {
            container.clearSlot(slot);
            interactInfo.player().tryAddItem(slotItem);
            clickedBlock.addSound(new ChiseledBookshelfSound(ChiseledBookshelfSound.Type.PICKUP, slotItem.hasEnchantments()));
            return true;
        }

        if (!itemStack.getItemType().hasItemTag(ItemTags.CHISELED_BOOKSHELF_BOOK)) {
            return false;
        }

        var inserted = itemStack.copy(true);
        inserted.setCount(1);
        container.setItemStack(slot, inserted);
        if (interactInfo.player().getGameMode() != GameMode.CREATIVE) {
            interactInfo.player().tryConsumeItemInHand();
        }
        clickedBlock.addSound(new ChiseledBookshelfSound(ChiseledBookshelfSound.Type.INSERT, inserted.hasEnchantments()));
        return true;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        return blockEntity == null ? 0 : blockEntity.getLastInteractedSlot();
    }

    private int calculateSlot(BlockFace facing, Vector3fc clickedPos) {
        var row = clickedPos.y() >= 0.5f ? 0 : 1;
        return row * 3 + calculateColumn(facing, clickedPos.x(), clickedPos.z());
    }

    private int calculateColumn(BlockFace facing, float clickX, float clickZ) {
        var offset = facing.rotateYCCW().getOffset();
        float distance = clickX * offset.x() + clickZ * offset.z();
        if (distance < 0) {
            distance += 1.0f;
        }
        return distance < (1 / 3f) ? 0 : distance < (2 / 3f) ? 1 : 2;
    }
}
