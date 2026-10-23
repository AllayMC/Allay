package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntitySignBaseComponent;

/**
 * {@code BlockEntitySign} exposes sign text and waxing state together with the
 * block-entity contract. The sign component exposes independent front and back
 * text faces while the common base supplies type, position, dimension, custom
 * naming, and persistent identity for the sign.
 *
 * @author daoge_cmd
 */
public interface BlockEntitySign extends BlockEntity, BlockEntitySignBaseComponent {
}
