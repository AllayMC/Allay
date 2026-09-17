package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockPaleMossBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPaleMossBlockBehaviorImpl extends BlockBehaviorImpl implements BlockPaleMossBlockBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockPaleMossBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
