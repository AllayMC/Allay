package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGlowingobsidianBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGlowingobsidianBehaviorImpl extends BlockBehaviorImpl implements BlockGlowingobsidianBehavior {
    public BlockGlowingobsidianBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
