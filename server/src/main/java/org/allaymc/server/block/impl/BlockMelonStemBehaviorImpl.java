package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMelonStemBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMelonStemBehaviorImpl extends BlockBehaviorImpl implements BlockMelonStemBehavior {
    public BlockMelonStemBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
