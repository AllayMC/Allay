package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGlazedTerracottaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGlazedTerracottaBehaviorImpl extends BlockBehaviorImpl implements BlockGlazedTerracottaBehavior {
    public BlockGlazedTerracottaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
