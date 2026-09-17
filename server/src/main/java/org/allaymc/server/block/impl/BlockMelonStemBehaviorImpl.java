package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockMelonStemBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMelonStemBehaviorImpl extends BlockBehaviorImpl implements BlockMelonStemBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockMelonStemBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
