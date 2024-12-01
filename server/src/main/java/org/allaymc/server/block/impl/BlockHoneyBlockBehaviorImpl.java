package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockHoneyBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockHoneyBlockBehaviorImpl extends BlockBehaviorImpl implements BlockHoneyBlockBehavior {
    public BlockHoneyBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
