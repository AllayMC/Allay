package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDarkPrismarineBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDarkPrismarineBehaviorImpl extends BlockBehaviorImpl implements BlockDarkPrismarineBehavior {
    public BlockDarkPrismarineBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
