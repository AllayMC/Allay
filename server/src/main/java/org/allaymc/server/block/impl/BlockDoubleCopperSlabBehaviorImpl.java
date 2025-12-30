package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.interfaces.BlockDoubleCopperSlabBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDoubleCopperSlabBehaviorImpl extends BlockDoubleSlabBehaviorImpl implements BlockDoubleCopperSlabBehavior {
    @Delegate
    private BlockOxidationComponent oxidationComponent;

    public BlockDoubleCopperSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
