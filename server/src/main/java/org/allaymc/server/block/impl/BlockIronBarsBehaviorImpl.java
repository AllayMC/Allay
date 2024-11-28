package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIronBarsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockIronBarsBehaviorImpl extends BlockBehaviorImpl implements BlockIronBarsBehavior {
    public BlockIronBarsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
