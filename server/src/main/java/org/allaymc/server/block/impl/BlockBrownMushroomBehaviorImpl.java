package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockBrownMushroomBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBrownMushroomBehaviorImpl extends BlockBehaviorImpl implements BlockBrownMushroomBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockBrownMushroomBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
