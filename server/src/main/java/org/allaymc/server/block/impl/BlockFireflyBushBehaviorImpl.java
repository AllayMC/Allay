package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockFireflyBushBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFireflyBushBehaviorImpl extends BlockBehaviorImpl implements BlockFireflyBushBehavior {
    public BlockFireflyBushBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
