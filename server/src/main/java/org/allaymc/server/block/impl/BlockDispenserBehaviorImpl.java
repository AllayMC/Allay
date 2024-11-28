package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDispenserBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDispenserBehaviorImpl extends BlockBehaviorImpl implements BlockDispenserBehavior {
    public BlockDispenserBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
