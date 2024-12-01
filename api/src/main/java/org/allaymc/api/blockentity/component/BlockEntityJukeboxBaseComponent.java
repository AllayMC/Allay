package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
public interface BlockEntityJukeboxBaseComponent extends BlockEntityBaseComponent {
    void play();

    void stop();

    ItemStack getMusicDiscItem();

    void setMusicDiscItem(ItemStack item);
}
