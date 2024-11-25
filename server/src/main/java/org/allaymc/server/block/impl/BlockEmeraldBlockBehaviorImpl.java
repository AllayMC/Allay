package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEmeraldBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockEmeraldBlockBehaviorImpl extends BlockBehaviorImpl implements BlockEmeraldBlockBehavior {
    public BlockEmeraldBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
