package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWheatBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWheatBehaviorImpl extends BlockBehaviorImpl implements BlockWheatBehavior {
    public BlockWheatBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
