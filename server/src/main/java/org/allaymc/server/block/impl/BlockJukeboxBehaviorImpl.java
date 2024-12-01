package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockJukeboxBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockJukeboxBehaviorImpl extends BlockBehaviorImpl implements BlockJukeboxBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityJukebox> blockEntityHolderComponent;

    public BlockJukeboxBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
