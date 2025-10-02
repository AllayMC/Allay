package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCopperBarsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperBarsBehaviorImpl extends BlockBehaviorImpl implements BlockCopperBarsBehavior {
    public BlockCopperBarsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
