package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockIceBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockIceBehaviorImpl extends BlockBehaviorImpl implements BlockIceBehavior {
    public BlockIceBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
