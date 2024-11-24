package org.allaymc.server.block.impl.carpet;

import java.util.List;
import org.allaymc.api.block.interfaces.carpet.BlockLimeCarpetBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLimeCarpetBehaviorImpl extends BlockBehaviorImpl implements BlockLimeCarpetBehavior {
    public BlockLimeCarpetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
