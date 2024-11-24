package org.allaymc.server.block.impl.terracotta;

import java.util.List;
import org.allaymc.api.block.interfaces.terracotta.BlockLimeTerracottaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLimeTerracottaBehaviorImpl extends BlockBehaviorImpl implements BlockLimeTerracottaBehavior {
    public BlockLimeTerracottaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
