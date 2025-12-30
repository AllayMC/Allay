package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockDoubleSlabBaseComponent;
import org.allaymc.api.block.interfaces.BlockDoubleSlabBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDoubleSlabBehaviorImpl extends BlockBehaviorImpl implements BlockDoubleSlabBehavior {

    @Delegate
    private BlockDoubleSlabBaseComponent doubleSlabBaseComponent;

    public BlockDoubleSlabBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
