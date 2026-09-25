package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockBeetrootBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBeetrootBehaviorImpl extends BlockBehaviorImpl implements BlockBeetrootBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockBeetrootBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
