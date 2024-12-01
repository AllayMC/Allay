package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFlowerPotBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFlowerPotBehaviorImpl extends BlockBehaviorImpl implements BlockFlowerPotBehavior {
    public BlockFlowerPotBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
