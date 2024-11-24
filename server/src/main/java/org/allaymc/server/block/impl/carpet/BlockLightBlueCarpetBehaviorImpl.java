package org.allaymc.server.block.impl.carpet;

import java.util.List;
import org.allaymc.api.block.interfaces.carpet.BlockLightBlueCarpetBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightBlueCarpetBehaviorImpl extends BlockBehaviorImpl implements BlockLightBlueCarpetBehavior {
    public BlockLightBlueCarpetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
