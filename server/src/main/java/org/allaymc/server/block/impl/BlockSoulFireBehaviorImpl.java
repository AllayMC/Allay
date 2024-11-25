package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSoulFireBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSoulFireBehaviorImpl extends BlockBehaviorImpl implements BlockSoulFireBehavior {
    public BlockSoulFireBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
