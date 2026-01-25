package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.interfaces.BlockPointedDripstoneBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPointedDripstoneBehaviorImpl extends BlockBehaviorImpl implements BlockPointedDripstoneBehavior {

    @Delegate
    private BlockFallableComponent fallableComponent;

    public BlockPointedDripstoneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
