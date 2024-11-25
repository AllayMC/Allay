package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAzaleaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAzaleaBehaviorImpl extends BlockBehaviorImpl implements BlockAzaleaBehavior {
    public BlockAzaleaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
