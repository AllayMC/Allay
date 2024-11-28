package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedExposedCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedExposedCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedExposedCopperBulbBehavior {
    public BlockWaxedExposedCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
