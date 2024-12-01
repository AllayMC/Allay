package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedOxidizedCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedOxidizedCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedOxidizedCopperBulbBehavior {
    public BlockWaxedOxidizedCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
