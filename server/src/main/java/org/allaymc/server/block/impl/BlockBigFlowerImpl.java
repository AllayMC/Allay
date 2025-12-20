package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBigFlower;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBigFlowerImpl extends BlockBehaviorImpl implements BlockBigFlower {
    public BlockBigFlowerImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
