package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockCopperGolemStatueBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCopperGolemStatueBehaviorImpl extends BlockBehaviorImpl implements BlockCopperGolemStatueBehavior {
    @Delegate
    protected BlockOxidationComponent oxidationComponent;

    public BlockCopperGolemStatueBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
