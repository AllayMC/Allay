package org.allaymc.server.block.impl.carpet;

import java.util.List;
import org.allaymc.api.block.interfaces.carpet.BlockPurpleCarpetBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPurpleCarpetBehaviorImpl extends BlockBehaviorImpl implements BlockPurpleCarpetBehavior {
    public BlockPurpleCarpetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
