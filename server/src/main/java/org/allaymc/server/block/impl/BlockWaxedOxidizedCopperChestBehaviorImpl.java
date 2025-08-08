package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedOxidizedCopperChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedOxidizedCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedOxidizedCopperChestBehavior {
    public BlockWaxedOxidizedCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
