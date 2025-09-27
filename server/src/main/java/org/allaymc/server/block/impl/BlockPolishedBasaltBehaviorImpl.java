package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedBasaltBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPolishedBasaltBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedBasaltBehavior {
    public BlockPolishedBasaltBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
