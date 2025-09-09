package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockCopperGrateBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperGrateBehaviorImpl extends BlockBehaviorImpl implements BlockCopperGrateBehavior {
    @Delegate
    private BlockOxidationComponent oxidationComponent;

    public BlockCopperGrateBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
