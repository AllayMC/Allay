package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockResinBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockResinBlockBehaviorImpl extends BlockBehaviorImpl implements BlockResinBlockBehavior {
    public BlockResinBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
