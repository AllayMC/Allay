package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBricksBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBricksBehaviorImpl extends BlockBehaviorImpl implements BlockBricksBehavior {
    public BlockBricksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
