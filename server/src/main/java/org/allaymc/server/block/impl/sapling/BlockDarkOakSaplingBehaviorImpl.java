package org.allaymc.server.block.impl.sapling;

import java.util.List;
import org.allaymc.api.block.interfaces.sapling.BlockDarkOakSaplingBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDarkOakSaplingBehaviorImpl extends BlockBehaviorImpl implements BlockDarkOakSaplingBehavior {
    public BlockDarkOakSaplingBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
