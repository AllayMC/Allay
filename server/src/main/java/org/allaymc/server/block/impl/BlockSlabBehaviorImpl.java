package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockSlabBaseComponent;
import org.allaymc.api.block.interfaces.BlockSlabBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSlabBehaviorImpl extends BlockBehaviorImpl implements BlockSlabBehavior {
    public BlockSlabBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }

    @Delegate
    @Override
    public BlockSlabBaseComponent getBaseComponent() {
        return (BlockSlabBaseComponent) super.getBaseComponent();
    }
}
