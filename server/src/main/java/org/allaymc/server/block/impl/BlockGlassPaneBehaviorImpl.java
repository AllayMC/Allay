package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockGlassPaneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockGlassPaneBehaviorImpl extends BlockBehaviorImpl implements BlockGlassPaneBehavior {
    public BlockGlassPaneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
