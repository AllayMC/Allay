package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockInfestedCobblestoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockInfestedCobblestoneBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedCobblestoneBehavior {
    public BlockInfestedCobblestoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
