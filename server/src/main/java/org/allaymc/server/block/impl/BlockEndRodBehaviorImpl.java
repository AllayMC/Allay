package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEndRodBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockEndRodBehaviorImpl extends BlockBehaviorImpl implements BlockEndRodBehavior {
    public BlockEndRodBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
