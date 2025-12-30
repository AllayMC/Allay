package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockCopperBulbBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperBulbBehaviorImpl extends BlockBehaviorImpl implements BlockCopperBulbBehavior {
    @Delegate
    private BlockOxidationComponent oxidationComponent;

    public BlockCopperBulbBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
