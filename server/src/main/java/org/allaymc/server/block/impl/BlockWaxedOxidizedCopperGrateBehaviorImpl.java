package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedOxidizedCopperGrateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedOxidizedCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedOxidizedCopperGrateBehavior {
    public BlockWaxedOxidizedCopperGrateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
