package org.allaymc.server.block.impl.carpet;

import java.util.List;
import org.allaymc.api.block.interfaces.carpet.BlockBlackCarpetBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBlackCarpetBehaviorImpl extends BlockBehaviorImpl implements BlockBlackCarpetBehavior {
    public BlockBlackCarpetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
