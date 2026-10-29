package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedSulfurBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPolishedSulfurBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedSulfurBehavior {
    public BlockPolishedSulfurBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
