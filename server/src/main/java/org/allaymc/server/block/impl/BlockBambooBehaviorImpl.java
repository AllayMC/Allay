package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBambooBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBambooBehaviorImpl extends BlockBehaviorImpl implements BlockBambooBehavior {
    public BlockBambooBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
