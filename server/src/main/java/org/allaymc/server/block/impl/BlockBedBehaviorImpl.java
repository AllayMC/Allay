package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBedBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBedBehaviorImpl extends BlockBehaviorImpl implements BlockBedBehavior {
    public BlockBedBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
