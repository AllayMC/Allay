package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockCrimsonFungusBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCrimsonFungusBehaviorImpl extends BlockBehaviorImpl implements BlockCrimsonFungusBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockCrimsonFungusBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
