package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockSculkBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSculkBehaviorImpl extends BlockBehaviorImpl implements BlockSculkBehavior {
    public BlockSculkBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
