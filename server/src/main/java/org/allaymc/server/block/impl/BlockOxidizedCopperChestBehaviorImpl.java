package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOxidizedCopperChestBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockOxidizedCopperChestBehaviorImpl extends BlockBehaviorImpl implements BlockOxidizedCopperChestBehavior {
    public BlockOxidizedCopperChestBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
