package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedTuffBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPolishedTuffBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedTuffBehavior {
    public BlockPolishedTuffBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
