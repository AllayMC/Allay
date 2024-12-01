package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;

public interface BlockJukeboxBehavior extends
        BlockBehavior,
        BlockEntityHolderComponent<BlockEntityJukebox> {
}
