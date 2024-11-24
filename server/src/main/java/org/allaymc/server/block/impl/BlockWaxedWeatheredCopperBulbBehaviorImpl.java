package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperBulbBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWaxedWeatheredCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedWeatheredCopperBulbBehavior {
    public BlockWaxedWeatheredCopperBulbBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
