package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBasaltBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBasaltBehaviorImpl extends BlockBehaviorImpl implements BlockBasaltBehavior {
    public BlockBasaltBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
