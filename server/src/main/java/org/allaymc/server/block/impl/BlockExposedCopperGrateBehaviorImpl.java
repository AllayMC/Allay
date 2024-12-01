package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockExposedCopperGrateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockExposedCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockExposedCopperGrateBehavior {
    public BlockExposedCopperGrateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
