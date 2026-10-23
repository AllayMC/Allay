package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityMovingBlockBaseComponent;

/**
 * {@code BlockEntityMovingBlock} exposes temporary piston-moved block state
 * together with the block-entity contract. The moving-block component carries
 * the displaced block, optional block-entity NBT, and piston position while the
 * common base supplies the identity and location of the temporary moving
 * entity.
 *
 * @author daoge_cmd
 */
public interface BlockEntityMovingBlock extends BlockEntity, BlockEntityMovingBlockBaseComponent {
}
