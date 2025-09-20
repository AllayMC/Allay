package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDispenserBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDispenserBehaviorImpl extends BlockBehaviorImpl implements BlockDispenserBehavior {
    public BlockDispenserBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
