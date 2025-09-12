package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSoulSoilBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSoulSoilBehaviorImpl extends BlockBehaviorImpl implements BlockSoulSoilBehavior {
    public BlockSoulSoilBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
