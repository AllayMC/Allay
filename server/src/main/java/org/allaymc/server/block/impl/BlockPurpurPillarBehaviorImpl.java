package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPurpurPillarBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPurpurPillarBehaviorImpl extends BlockBehaviorImpl implements BlockPurpurPillarBehavior {
    public BlockPurpurPillarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
