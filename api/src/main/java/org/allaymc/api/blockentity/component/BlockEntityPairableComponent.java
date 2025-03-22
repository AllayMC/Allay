package org.allaymc.api.blockentity.component;

import org.allaymc.api.blockentity.BlockEntity;

/**
 * @author IWareQ
 */
public interface BlockEntityPairableComponent extends BlockEntityComponent {
    boolean tryPairWith(BlockEntity other);

    void unpair();

    BlockEntity getPair();

    default boolean isPaired() {
        return getPair() != null;
    }

    boolean isLead();
}
