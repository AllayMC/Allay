package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDropperBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDropperBehaviorImpl extends BlockBehaviorImpl implements BlockDropperBehavior {
    public BlockDropperBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
