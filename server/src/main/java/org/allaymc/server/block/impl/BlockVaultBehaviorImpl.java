package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockVaultBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockVaultBehaviorImpl extends BlockBehaviorImpl implements BlockVaultBehavior {
    public BlockVaultBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
