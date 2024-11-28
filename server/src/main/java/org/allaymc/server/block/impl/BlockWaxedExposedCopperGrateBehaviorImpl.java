package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedExposedCopperGrateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedExposedCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedExposedCopperGrateBehavior {
    public BlockWaxedExposedCopperGrateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
