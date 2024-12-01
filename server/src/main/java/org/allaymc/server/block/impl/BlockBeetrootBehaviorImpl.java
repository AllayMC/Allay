package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBeetrootBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBeetrootBehaviorImpl extends BlockBehaviorImpl implements BlockBeetrootBehavior {
    public BlockBeetrootBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
