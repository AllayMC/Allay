package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBookshelfBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBookshelfBehaviorImpl extends BlockBehaviorImpl implements BlockBookshelfBehavior {
    public BlockBookshelfBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
