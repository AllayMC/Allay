package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedstoneBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRedstoneBlockBehaviorImpl extends BlockBehaviorImpl implements BlockRedstoneBlockBehavior {
    public BlockRedstoneBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
