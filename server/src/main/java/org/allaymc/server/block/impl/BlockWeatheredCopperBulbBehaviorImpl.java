package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWeatheredCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWeatheredCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockWeatheredCopperBulbBehavior {
    public BlockWeatheredCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
