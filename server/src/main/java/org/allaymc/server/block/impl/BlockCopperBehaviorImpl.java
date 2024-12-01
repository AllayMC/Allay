package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperBehaviorImpl extends BlockBehaviorImpl implements BlockCopperBehavior {
    public BlockCopperBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
