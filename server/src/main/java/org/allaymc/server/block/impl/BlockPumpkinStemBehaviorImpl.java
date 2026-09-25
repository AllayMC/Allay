package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockPumpkinStemBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPumpkinStemBehaviorImpl extends BlockBehaviorImpl implements BlockPumpkinStemBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockPumpkinStemBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
