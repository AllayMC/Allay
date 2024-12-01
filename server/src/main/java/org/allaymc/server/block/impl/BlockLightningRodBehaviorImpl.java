package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLightningRodBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLightningRodBehaviorImpl extends BlockBehaviorImpl implements BlockLightningRodBehavior {
    public BlockLightningRodBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
