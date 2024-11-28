package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGoldOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGoldOreBehaviorImpl extends BlockBehaviorImpl implements BlockGoldOreBehavior {
    public BlockGoldOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
