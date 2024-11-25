package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPolishedGraniteBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPolishedGraniteBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedGraniteBehavior {
    public BlockPolishedGraniteBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
