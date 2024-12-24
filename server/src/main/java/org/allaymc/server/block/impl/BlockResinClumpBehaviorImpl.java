package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockResinClumpBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockResinClumpBehaviorImpl extends BlockBehaviorImpl implements BlockResinClumpBehavior {
    public BlockResinClumpBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
