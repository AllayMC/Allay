package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLilyOfTheValleyBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLilyOfTheValleyBehaviorImpl extends BlockBehaviorImpl implements BlockLilyOfTheValleyBehavior {
    public BlockLilyOfTheValleyBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
