package org.allaymc.server.block.impl.carpet;

import java.util.List;
import org.allaymc.api.block.interfaces.carpet.BlockOrangeCarpetBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOrangeCarpetBehaviorImpl extends BlockBehaviorImpl implements BlockOrangeCarpetBehavior {
    public BlockOrangeCarpetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
