package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRespawnAnchorBehaviorImpl extends BlockBehaviorImpl implements BlockRespawnAnchorBehavior {
    public BlockRespawnAnchorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
