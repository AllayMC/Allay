package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedDeepslateBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPolishedDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedDeepslateBehavior {
    public BlockPolishedDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
