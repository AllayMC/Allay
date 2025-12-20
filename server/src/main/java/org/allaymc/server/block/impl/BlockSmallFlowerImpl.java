package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSmallFlower;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSmallFlowerImpl extends BlockBehaviorImpl implements BlockSmallFlower {
    public BlockSmallFlowerImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
