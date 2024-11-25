package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNetherreactorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockNetherreactorBehaviorImpl extends BlockBehaviorImpl implements BlockNetherreactorBehavior {
    public BlockNetherreactorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
