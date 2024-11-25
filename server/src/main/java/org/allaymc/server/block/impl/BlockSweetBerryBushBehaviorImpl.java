package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSweetBerryBushBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSweetBerryBushBehaviorImpl extends BlockBehaviorImpl implements BlockSweetBerryBushBehavior {
    public BlockSweetBerryBushBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
