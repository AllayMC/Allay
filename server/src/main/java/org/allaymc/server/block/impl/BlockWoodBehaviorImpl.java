package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.interfaces.BlockWoodBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWoodBehaviorImpl extends BlockBehaviorImpl implements BlockWoodBehavior {
    @Delegate
    private BlockStrippableComponent strippableComponent;

    public BlockWoodBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
