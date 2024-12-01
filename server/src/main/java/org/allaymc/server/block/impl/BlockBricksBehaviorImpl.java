package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBricksBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBricksBehaviorImpl extends BlockBehaviorImpl implements BlockBricksBehavior {
    public BlockBricksBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
