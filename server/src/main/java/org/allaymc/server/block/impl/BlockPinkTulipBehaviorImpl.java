package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPinkTulipBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPinkTulipBehaviorImpl extends BlockBehaviorImpl implements BlockPinkTulipBehavior {
    public BlockPinkTulipBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
