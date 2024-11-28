package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockExposedCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockExposedCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockExposedCopperBulbBehavior {
    public BlockExposedCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
