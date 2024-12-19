package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3f;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author IWareQ
 */
public class BlockJukeboxBaseComponentImpl extends BlockBaseComponentImpl {
    @Dependency
    BlockEntityHolderComponent<BlockEntityJukebox> blockEntityHolderComponent;

    public BlockJukeboxBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        var blockEntity = blockEntityHolderComponent.getBlockEntity(new Position3i(interactInfo.clickedBlockPos(), dimension));
        if (blockEntity instanceof BlockEntityJukebox jukebox) {
            var musicDisc = jukebox.getMusicDiscItem();
            if (musicDisc != null) {
                jukebox.setMusicDiscItem(null);
                jukebox.stop();

                dimension.dropItem(musicDisc, new Position3f(blockEntity.getPosition()).add(0, 1, 0));
            } else {
                jukebox.setMusicDiscItem(player.getItemInHand());
                player.clearItemInHand();

                jukebox.play();
            }
        }

        return true;
    }
}
