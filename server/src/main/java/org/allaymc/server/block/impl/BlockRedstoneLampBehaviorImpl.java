package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedstoneLampBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRedstoneLampBehaviorImpl extends BlockBehaviorImpl implements BlockRedstoneLampBehavior {
    public BlockRedstoneLampBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
