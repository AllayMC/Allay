package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPointedDripstoneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPointedDripstoneBehaviorImpl extends BlockBehaviorImpl implements BlockPointedDripstoneBehavior {
    public BlockPointedDripstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
