package org.allaymc.api.blockentity.component;

import org.allaymc.api.blockentity.interfaces.BlockEntityChest;

/**
 * @author IWareQ
 */
public interface BlockEntityChestBaseComponent extends BlockEntityBaseComponent {
    boolean tryPairWith(BlockEntityChest other);

    void unpair();

    BlockEntityChest getPair();

    default boolean isPaired() {
        return getPair() != null;
    }
}
