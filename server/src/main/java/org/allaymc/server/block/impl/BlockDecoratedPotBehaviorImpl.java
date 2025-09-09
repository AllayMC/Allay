package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDecoratedPotBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDecoratedPotBehaviorImpl extends BlockBehaviorImpl implements BlockDecoratedPotBehavior {
    public BlockDecoratedPotBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
