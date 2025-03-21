package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSculkShriekerBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSculkShriekerBehaviorImpl extends BlockBehaviorImpl implements BlockSculkShriekerBehavior {
    public BlockSculkShriekerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
