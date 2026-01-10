package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockRepeaterBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockRepeaterBehaviorImpl extends BlockBehaviorImpl implements BlockRepeaterBehavior {
    public BlockRepeaterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
