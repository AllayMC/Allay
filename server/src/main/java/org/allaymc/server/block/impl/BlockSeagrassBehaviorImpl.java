package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockSeagrassBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSeagrassBehaviorImpl extends BlockBehaviorImpl implements BlockSeagrassBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockSeagrassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
