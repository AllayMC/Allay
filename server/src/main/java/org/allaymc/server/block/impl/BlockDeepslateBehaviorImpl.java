package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateBehavior {
    public BlockDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
