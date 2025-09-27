package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGlassBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGlassBehaviorImpl extends BlockBehaviorImpl implements BlockGlassBehavior {
    public BlockGlassBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
