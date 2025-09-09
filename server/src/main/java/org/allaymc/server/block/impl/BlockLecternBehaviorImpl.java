package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockLecternBehaviorImpl extends BlockBehaviorImpl implements BlockLecternBehavior {
    public BlockLecternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
