package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockReinforcedDeepslateBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockReinforcedDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockReinforcedDeepslateBehavior {
    public BlockReinforcedDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
