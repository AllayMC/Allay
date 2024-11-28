package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateLapisOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateLapisOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateLapisOreBehavior {
    public BlockDeepslateLapisOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
