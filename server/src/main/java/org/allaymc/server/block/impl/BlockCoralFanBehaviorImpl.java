package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCoralFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCoralFanBehaviorImpl extends BlockBehaviorImpl implements BlockCoralFanBehavior {
    public BlockCoralFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
