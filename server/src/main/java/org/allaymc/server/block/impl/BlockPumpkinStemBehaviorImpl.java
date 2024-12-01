package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPumpkinStemBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPumpkinStemBehaviorImpl extends BlockBehaviorImpl implements BlockPumpkinStemBehavior {
    public BlockPumpkinStemBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
