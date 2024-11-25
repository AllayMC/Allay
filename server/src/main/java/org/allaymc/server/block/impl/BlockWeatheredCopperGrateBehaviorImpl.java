package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWeatheredCopperGrateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWeatheredCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockWeatheredCopperGrateBehavior {
    public BlockWeatheredCopperGrateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
