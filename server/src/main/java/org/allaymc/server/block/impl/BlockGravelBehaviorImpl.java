package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGravelBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGravelBehaviorImpl extends BlockBehaviorImpl implements BlockGravelBehavior {
    public BlockGravelBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
