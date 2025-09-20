package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCarvedPumpkinBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCarvedPumpkinBehaviorImpl extends BlockBehaviorImpl implements BlockCarvedPumpkinBehavior {
    public BlockCarvedPumpkinBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
