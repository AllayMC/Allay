package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCryingObsidianBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCryingObsidianBehaviorImpl extends BlockBehaviorImpl implements BlockCryingObsidianBehavior {
    public BlockCryingObsidianBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
