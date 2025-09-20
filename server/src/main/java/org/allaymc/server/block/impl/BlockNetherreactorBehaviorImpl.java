package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetherreactorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockNetherreactorBehaviorImpl extends BlockBehaviorImpl implements BlockNetherreactorBehavior {
    public BlockNetherreactorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
