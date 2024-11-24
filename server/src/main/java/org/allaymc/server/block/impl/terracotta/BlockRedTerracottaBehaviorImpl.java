package org.allaymc.server.block.impl.terracotta;

import java.util.List;
import org.allaymc.api.block.interfaces.terracotta.BlockRedTerracottaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockRedTerracottaBehaviorImpl extends BlockBehaviorImpl implements BlockRedTerracottaBehavior {
    public BlockRedTerracottaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
