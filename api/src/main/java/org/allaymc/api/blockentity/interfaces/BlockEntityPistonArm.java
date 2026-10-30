package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityPistonArmBaseComponent;

/**
 * {@code BlockEntityPistonArm} exposes piston arm movement state together with
 * the block-entity contract. The arm component exposes animation progress,
 * attached blocks, and movement controls while the common base supplies
 * persistent identity and the dimension position from which the piston
 * operates.
 *
 * @author daoge_cmd
 */
public interface BlockEntityPistonArm extends BlockEntity, BlockEntityPistonArmBaseComponent {
}
