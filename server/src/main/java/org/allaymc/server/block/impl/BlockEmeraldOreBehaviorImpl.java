package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEmeraldOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockEmeraldOreBehaviorImpl extends BlockBehaviorImpl implements BlockEmeraldOreBehavior {
    public BlockEmeraldOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
