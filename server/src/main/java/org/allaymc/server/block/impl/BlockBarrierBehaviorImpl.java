package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBarrierBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBarrierBehaviorImpl extends BlockBehaviorImpl implements BlockBarrierBehavior {
    public BlockBarrierBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
