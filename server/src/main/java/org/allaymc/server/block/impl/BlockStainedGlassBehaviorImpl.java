package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockStainedGlassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockStainedGlassBehaviorImpl extends BlockBehaviorImpl implements BlockStainedGlassBehavior {
    public BlockStainedGlassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
