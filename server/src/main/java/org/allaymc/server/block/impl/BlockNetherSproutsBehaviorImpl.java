package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetherSproutsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockNetherSproutsBehaviorImpl extends BlockBehaviorImpl implements BlockNetherSproutsBehavior {
    public BlockNetherSproutsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
