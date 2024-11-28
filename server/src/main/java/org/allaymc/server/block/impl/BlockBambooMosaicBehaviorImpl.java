package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBambooMosaicBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBambooMosaicBehaviorImpl extends BlockBehaviorImpl implements BlockBambooMosaicBehavior {
    public BlockBambooMosaicBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
