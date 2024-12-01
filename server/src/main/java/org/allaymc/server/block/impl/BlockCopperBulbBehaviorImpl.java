package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockCopperBulbBehavior {
    public BlockCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}