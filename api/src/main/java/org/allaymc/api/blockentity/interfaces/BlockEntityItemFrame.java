package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityItemFrameBaseComponent;

/**
 * {@code BlockEntityItemFrame} exposes item frame contents and rotation
 * together with the block-entity contract. The frame component supplies the
 * displayed stack and its rotation while the common base supplies type,
 * dimension, location, custom naming, and NBT persistence for the frame.
 *
 * @author daoge_cmd
 */
public interface BlockEntityItemFrame extends BlockEntity, BlockEntityItemFrameBaseComponent {
}
