package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCactusFlowerBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCactusFlowerBehaviorImpl extends BlockBehaviorImpl implements BlockCactusFlowerBehavior {
    public BlockCactusFlowerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
