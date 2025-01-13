package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockDoubleCopperSlabBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDoubleCopperSlabBehaviorImpl extends BlockDoubleSlabBehaviorImpl implements BlockDoubleCopperSlabBehavior {
    @Delegate
    protected BlockOxidationComponent oxidationComponent;

    public BlockDoubleCopperSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
