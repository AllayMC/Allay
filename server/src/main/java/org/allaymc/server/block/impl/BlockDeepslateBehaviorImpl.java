package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateBehavior {
    public BlockDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
