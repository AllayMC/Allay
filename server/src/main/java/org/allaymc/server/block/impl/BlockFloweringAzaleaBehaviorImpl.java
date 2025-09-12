package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFloweringAzaleaBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFloweringAzaleaBehaviorImpl extends BlockBehaviorImpl implements BlockFloweringAzaleaBehavior {
    public BlockFloweringAzaleaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
