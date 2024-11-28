package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeepslateDiamondOreBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeepslateDiamondOreBehaviorImpl extends BlockBehaviorImpl implements BlockDeepslateDiamondOreBehavior {
    public BlockDeepslateDiamondOreBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
