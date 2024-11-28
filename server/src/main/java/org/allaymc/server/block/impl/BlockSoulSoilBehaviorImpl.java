package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSoulSoilBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSoulSoilBehaviorImpl extends BlockBehaviorImpl implements BlockSoulSoilBehavior {
    public BlockSoulSoilBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
