package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCaveVinesHeadWithBerriesBehaviorImpl extends BlockBehaviorImpl implements BlockCaveVinesHeadWithBerriesBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockCaveVinesHeadWithBerriesBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
