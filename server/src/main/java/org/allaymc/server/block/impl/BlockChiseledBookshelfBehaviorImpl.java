package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledBookshelfBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockChiseledBookshelfBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledBookshelfBehavior {
    public BlockChiseledBookshelfBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
