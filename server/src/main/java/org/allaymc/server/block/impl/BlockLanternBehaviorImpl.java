package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLanternBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLanternBehaviorImpl extends BlockBehaviorImpl implements BlockLanternBehavior {
    public BlockLanternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
