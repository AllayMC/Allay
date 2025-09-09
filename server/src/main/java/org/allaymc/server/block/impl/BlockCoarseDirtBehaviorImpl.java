package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoarseDirtBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCoarseDirtBehaviorImpl extends BlockBehaviorImpl implements BlockCoarseDirtBehavior {
    public BlockCoarseDirtBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
