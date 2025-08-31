package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGlassPaneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGlassPaneBehaviorImpl extends BlockBehaviorImpl implements BlockGlassPaneBehavior {
    public BlockGlassPaneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
