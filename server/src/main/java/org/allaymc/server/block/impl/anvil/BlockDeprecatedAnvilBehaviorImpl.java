package org.allaymc.server.block.impl.anvil;

import java.util.List;
import org.allaymc.api.block.interfaces.anvil.BlockDeprecatedAnvilBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeprecatedAnvilBehaviorImpl extends BlockBehaviorImpl implements BlockDeprecatedAnvilBehavior {
    public BlockDeprecatedAnvilBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
