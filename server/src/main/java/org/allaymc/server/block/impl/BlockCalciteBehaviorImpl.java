package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCalciteBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCalciteBehaviorImpl extends BlockBehaviorImpl implements BlockCalciteBehavior {
    public BlockCalciteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
