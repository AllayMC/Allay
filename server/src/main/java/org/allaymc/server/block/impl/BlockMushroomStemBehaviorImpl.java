package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMushroomStemBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockMushroomStemBehaviorImpl extends BlockBehaviorImpl implements BlockMushroomStemBehavior {
    public BlockMushroomStemBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
