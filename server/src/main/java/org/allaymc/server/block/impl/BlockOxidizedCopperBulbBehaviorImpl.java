package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOxidizedCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockOxidizedCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockOxidizedCopperBulbBehavior {
    public BlockOxidizedCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
