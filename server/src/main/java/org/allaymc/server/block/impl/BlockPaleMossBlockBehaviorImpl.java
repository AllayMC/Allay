package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPaleMossBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPaleMossBlockBehaviorImpl extends BlockBehaviorImpl implements BlockPaleMossBlockBehavior {
    public BlockPaleMossBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
