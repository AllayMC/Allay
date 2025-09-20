package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAndesiteBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAndesiteBehaviorImpl extends BlockBehaviorImpl implements BlockAndesiteBehavior {
    public BlockAndesiteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
