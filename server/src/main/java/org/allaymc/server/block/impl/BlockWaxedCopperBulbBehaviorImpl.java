package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedCopperBulbBehavior {
    public BlockWaxedCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
