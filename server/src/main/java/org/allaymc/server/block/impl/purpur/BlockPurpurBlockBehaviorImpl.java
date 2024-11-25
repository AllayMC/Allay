package org.allaymc.server.block.impl.purpur;

import org.allaymc.api.block.interfaces.purpur.BlockPurpurBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockPurpurBlockBehaviorImpl extends BlockBehaviorImpl implements BlockPurpurBlockBehavior {
    public BlockPurpurBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
