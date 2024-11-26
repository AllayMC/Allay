package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPurpurPillarBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPurpurPillarBehaviorImpl extends BlockBehaviorImpl implements BlockPurpurPillarBehavior {
    public BlockPurpurPillarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
