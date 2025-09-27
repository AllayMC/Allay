package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSmoothBasaltBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSmoothBasaltBehaviorImpl extends BlockBehaviorImpl implements BlockSmoothBasaltBehavior {
    public BlockSmoothBasaltBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
