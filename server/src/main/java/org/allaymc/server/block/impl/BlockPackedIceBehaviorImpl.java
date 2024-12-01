package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPackedIceBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPackedIceBehaviorImpl extends BlockBehaviorImpl implements BlockPackedIceBehavior {
    public BlockPackedIceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
