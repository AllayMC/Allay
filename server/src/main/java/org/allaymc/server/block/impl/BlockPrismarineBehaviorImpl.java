package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPrismarineBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPrismarineBehaviorImpl extends BlockBehaviorImpl implements BlockPrismarineBehavior {
    public BlockPrismarineBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
