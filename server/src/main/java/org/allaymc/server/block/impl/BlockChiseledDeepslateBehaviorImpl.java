package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockChiseledDeepslateBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockChiseledDeepslateBehaviorImpl extends BlockBehaviorImpl implements BlockChiseledDeepslateBehavior {
    public BlockChiseledDeepslateBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
