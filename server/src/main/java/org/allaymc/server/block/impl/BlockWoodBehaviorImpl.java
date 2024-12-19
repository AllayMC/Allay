package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.interfaces.BlockWoodBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockWoodBehaviorImpl extends BlockBehaviorImpl implements BlockWoodBehavior {
    @Delegate
    protected BlockStrippableComponent strippableComponent;

    public BlockWoodBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
