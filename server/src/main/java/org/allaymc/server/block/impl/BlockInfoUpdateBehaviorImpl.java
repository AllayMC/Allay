package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockInfoUpdateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockInfoUpdateBehaviorImpl extends BlockBehaviorImpl implements BlockInfoUpdateBehavior {
    public BlockInfoUpdateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
