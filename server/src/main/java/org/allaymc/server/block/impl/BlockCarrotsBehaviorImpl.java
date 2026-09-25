package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockCarrotsBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCarrotsBehaviorImpl extends BlockBehaviorImpl implements BlockCarrotsBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockCarrotsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
