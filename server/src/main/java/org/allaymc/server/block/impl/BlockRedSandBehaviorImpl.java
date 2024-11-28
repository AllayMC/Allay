package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRedSandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockRedSandBehaviorImpl extends BlockBehaviorImpl implements BlockRedSandBehavior {
    public BlockRedSandBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
