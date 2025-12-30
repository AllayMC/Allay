package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemMusicDiscStack;
import org.allaymc.api.math.position.Position3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author IWareQ
 */
public class BlockJukeboxBaseComponentImpl extends BlockBaseComponentImpl {
    @Dependency
    BlockBlockEntityHolderComponent<BlockEntityJukebox> blockEntityHolderComponent;

    public BlockJukeboxBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var blockEntity = blockEntityHolderComponent.getBlockEntity(new Position3i(interactInfo.clickedBlockPos(), dimension));
        var oldMusicDiscItem = blockEntity.getMusicDiscItem();
        if (oldMusicDiscItem != null) {
            blockEntity.setMusicDiscItem(null);
            blockEntity.stop();
            dimension.dropItem(oldMusicDiscItem, new Position3d(blockEntity.getPosition()).add(0.5, 1, 0.5));
            return true;
        } else if (itemStack instanceof ItemMusicDiscStack musicDiscItem) {
            blockEntity.setMusicDiscItem(musicDiscItem);
            interactInfo.player().clearItemInHand();
            blockEntity.play();
            return true;
        }

        return false;
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (blockEntity instanceof BlockEntityJukebox jukebox) {
            jukebox.stop();
        }
    }
}
