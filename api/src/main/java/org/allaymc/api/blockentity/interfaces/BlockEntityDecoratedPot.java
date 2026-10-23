package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityDecoratedPotBaseComponent;

/**
 * {@code BlockEntityDecoratedPot} exposes decorated pot contents and sherds
 * together with the block-entity contract. The pot component exposes its single
 * stored stack, insertion checks, decoration, and fill-based comparator result
 * while the common base supplies position and persistence.
 *
 * @author IWareQ
 */
public interface BlockEntityDecoratedPot extends BlockEntity, BlockEntityDecoratedPotBaseComponent {
}
