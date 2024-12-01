package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDropperBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDropperBehaviorImpl extends BlockBehaviorImpl implements BlockDropperBehavior {
    public BlockDropperBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
