package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockCopperBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperBehaviorImpl extends BlockBehaviorImpl implements BlockCopperBehavior {
    @Delegate
    protected BlockOxidationComponent oxidizableComponent;

    public BlockCopperBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
