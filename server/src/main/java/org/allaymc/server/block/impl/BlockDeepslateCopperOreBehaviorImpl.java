package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateCopperOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateCopperOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateCopperOreBehavior {
    public BlockDeepslateCopperOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
