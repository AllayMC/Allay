package org.allaymc.server.block.impl.purpur;

import java.util.List;
import org.allaymc.api.block.interfaces.purpur.BlockPurpurBlockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPurpurBlockBehaviorImpl extends BlockBehaviorImpl implements BlockPurpurBlockBehavior {
    public BlockPurpurBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
