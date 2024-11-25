package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockScaffoldingBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockScaffoldingBehaviorImpl extends BlockBehaviorImpl implements BlockScaffoldingBehavior {
    public BlockScaffoldingBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
