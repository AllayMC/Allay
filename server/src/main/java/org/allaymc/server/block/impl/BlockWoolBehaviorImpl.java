package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWoolBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWoolBehaviorImpl extends BlockBehaviorImpl implements BlockWoolBehavior {
    public BlockWoolBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
