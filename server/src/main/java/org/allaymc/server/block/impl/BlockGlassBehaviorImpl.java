package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGlassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockGlassBehaviorImpl extends BlockBehaviorImpl implements BlockGlassBehavior {
    public BlockGlassBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
