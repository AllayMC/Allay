package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockLabTableBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLabTableBehaviorImpl extends BlockBehaviorImpl implements BlockLabTableBehavior {
    public BlockLabTableBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
