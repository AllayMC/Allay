package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockActivatorRailBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockActivatorRailBehaviorImpl extends BlockBehaviorImpl implements BlockActivatorRailBehavior {
    public BlockActivatorRailBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
