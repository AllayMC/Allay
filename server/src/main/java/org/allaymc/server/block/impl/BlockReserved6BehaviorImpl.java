package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockReserved6Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockReserved6BehaviorImpl extends BlockBehaviorImpl implements BlockReserved6Behavior {
    public BlockReserved6BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
