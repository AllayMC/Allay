package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCakeBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCakeBehaviorImpl extends BlockBehaviorImpl implements BlockCakeBehavior {
    public BlockCakeBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
