package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComparatorBaseComponent;

/**
 * {@code BlockEntityComparator} exposes comparator output signal together with
 * the block-entity contract. The comparator component stores its output
 * separately from the surrounding block properties so redstone consumers can
 * retrieve the last calculated signal through the specialized component.
 *
 * @author daoge_cmd
 */
public interface BlockEntityComparator extends BlockEntity, BlockEntityComparatorBaseComponent {
}
