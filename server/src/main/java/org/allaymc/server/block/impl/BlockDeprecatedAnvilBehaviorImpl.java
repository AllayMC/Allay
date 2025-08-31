package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeprecatedAnvilBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeprecatedAnvilBehaviorImpl extends BlockBehaviorImpl implements BlockDeprecatedAnvilBehavior {
    public BlockDeprecatedAnvilBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
