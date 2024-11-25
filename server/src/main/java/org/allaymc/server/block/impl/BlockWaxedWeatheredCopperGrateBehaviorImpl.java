package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperGrateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWaxedWeatheredCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockWaxedWeatheredCopperGrateBehavior {
    public BlockWaxedWeatheredCopperGrateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
