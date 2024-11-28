package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOxidizedCopperGrateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockOxidizedCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockOxidizedCopperGrateBehavior {
    public BlockOxidizedCopperGrateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
