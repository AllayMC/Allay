package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLecternBehaviorImpl extends BlockBehaviorImpl implements BlockLecternBehavior {
    public BlockLecternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
