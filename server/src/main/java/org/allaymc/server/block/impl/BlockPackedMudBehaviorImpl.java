package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPackedMudBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPackedMudBehaviorImpl extends BlockBehaviorImpl implements BlockPackedMudBehavior {
    public BlockPackedMudBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
