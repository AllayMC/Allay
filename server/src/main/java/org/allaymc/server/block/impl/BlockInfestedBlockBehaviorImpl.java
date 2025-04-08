package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockInfestedBlockBaseComponent;
import org.allaymc.api.block.interfaces.BlockInfestedBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockInfestedBlockBehaviorImpl extends BlockBehaviorImpl implements BlockInfestedBlockBehavior {
    public BlockInfestedBlockBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }

    @Delegate
    @Override
    public BlockInfestedBlockBaseComponent getBaseComponent() {
        return (BlockInfestedBlockBaseComponent) super.getBaseComponent();
    }
}
