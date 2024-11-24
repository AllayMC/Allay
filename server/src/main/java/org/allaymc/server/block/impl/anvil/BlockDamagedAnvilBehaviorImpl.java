package org.allaymc.server.block.impl.anvil;

import java.util.List;
import org.allaymc.api.block.interfaces.anvil.BlockDamagedAnvilBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDamagedAnvilBehaviorImpl extends BlockBehaviorImpl implements BlockDamagedAnvilBehavior {
    public BlockDamagedAnvilBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
