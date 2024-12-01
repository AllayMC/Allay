package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLapisOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLapisOreBehaviorImpl extends BlockBehaviorImpl implements BlockLapisOreBehavior {
    public BlockLapisOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
