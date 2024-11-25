package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedWeatheredCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedWeatheredCopperBulbBehavior {
    public BlockWaxedWeatheredCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
