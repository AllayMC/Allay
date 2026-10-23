package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityFlowerPot;

/**
 * {@code BlockFlowerPotBehavior} combines ordinary block behavior with access to flower-pot
 * block entities. Consumers use the holder contract to retrieve the pot at a position, while
 * its base component handles support and plant interactions.
 */
public interface BlockFlowerPotBehavior extends BlockBehavior, BlockBlockEntityHolderComponent<BlockEntityFlowerPot> {
}
