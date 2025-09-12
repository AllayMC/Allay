package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockObsidianBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockObsidianBehaviorImpl extends BlockBehaviorImpl implements BlockObsidianBehavior {
    public BlockObsidianBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
