package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockCopperStairsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCopperStairsBehaviorImpl extends BlockBehaviorImpl implements BlockCopperStairsBehavior {
    @Delegate
    protected BlockOxidationComponent oxidationComponent;

    public BlockCopperStairsBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
