package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSoulLanternBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSoulLanternBehaviorImpl extends BlockBehaviorImpl implements BlockSoulLanternBehavior {
    public BlockSoulLanternBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
