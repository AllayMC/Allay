package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGlowFrameBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGlowFrameBehaviorImpl extends BlockBehaviorImpl implements BlockGlowFrameBehavior {
    public BlockGlowFrameBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
