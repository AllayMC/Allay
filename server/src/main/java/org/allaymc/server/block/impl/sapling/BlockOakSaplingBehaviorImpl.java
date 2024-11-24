package org.allaymc.server.block.impl.sapling;

import java.util.List;
import org.allaymc.api.block.interfaces.sapling.BlockOakSaplingBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOakSaplingBehaviorImpl extends BlockBehaviorImpl implements BlockOakSaplingBehavior {
    public BlockOakSaplingBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
