package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityCommandBlockBaseComponent;

/**
 * {@code BlockEntityCommandBlock} combines command execution controls,
 * command-sender identity, and persisted block-entity state. The command-block
 * component supplies command text, activation settings, output, and execution
 * behavior, while the shared base contract associates that state with a
 * dimension and block position.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCommandBlock extends BlockEntity, BlockEntityCommandBlockBaseComponent {
}
