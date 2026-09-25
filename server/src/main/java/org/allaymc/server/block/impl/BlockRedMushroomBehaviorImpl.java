package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockRedMushroomBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRedMushroomBehaviorImpl extends BlockBehaviorImpl implements BlockRedMushroomBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockRedMushroomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
