package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockInfestedDeepslateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockInfestedDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedDeepslateBehavior {
    public BlockInfestedDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
