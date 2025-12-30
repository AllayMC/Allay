package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockInfestedBlockBaseComponent;
import org.allaymc.api.block.interfaces.BlockInfestedBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockInfestedBlockBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedBlockBehavior {

    @Delegate
    private BlockInfestedBlockBaseComponent infestedBlockBaseComponent;

    public BlockInfestedBlockBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
