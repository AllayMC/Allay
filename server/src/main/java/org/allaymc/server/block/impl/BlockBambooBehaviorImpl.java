package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBambooBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBambooBehaviorImpl extends BlockBehaviorImpl implements BlockBambooBehavior {
    public BlockBambooBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
