package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSculkShriekerBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSculkShriekerBehaviorImpl extends BlockBehaviorImpl implements BlockSculkShriekerBehavior {
    public BlockSculkShriekerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
