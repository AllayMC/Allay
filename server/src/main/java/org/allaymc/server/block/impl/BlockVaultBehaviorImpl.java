package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockVaultBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockVaultBehaviorImpl extends BlockBehaviorImpl implements BlockVaultBehavior {
    public BlockVaultBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
