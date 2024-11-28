package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedstoneWireBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRedstoneWireBehaviorImpl extends BlockBehaviorImpl implements BlockRedstoneWireBehavior {
    public BlockRedstoneWireBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
