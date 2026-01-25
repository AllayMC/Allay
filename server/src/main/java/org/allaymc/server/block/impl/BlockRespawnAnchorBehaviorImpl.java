package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRespawnAnchorBehaviorImpl extends BlockBehaviorImpl implements BlockRespawnAnchorBehavior {
    @Delegate
    private BlockRespawnPointComponent blockRespawnPointComponent;

    public BlockRespawnAnchorBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
