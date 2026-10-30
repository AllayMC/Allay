package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityNoteblockBaseComponent;

/**
 * {@code BlockEntityNoteblock} exposes note block pitch and powered state
 * together with the block-entity contract. The note component supplies pitch
 * advancement and the remembered redstone state while the common base supplies
 * the block entity location, dimension, custom naming, and NBT persistence.
 *
 * @author daoge_cmd
 */
public interface BlockEntityNoteblock extends BlockEntity, BlockEntityNoteblockBaseComponent {
}
