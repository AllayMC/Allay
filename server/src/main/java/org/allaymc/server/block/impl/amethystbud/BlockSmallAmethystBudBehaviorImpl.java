package org.allaymc.server.block.impl.amethystbud;

import org.allaymc.api.block.interfaces.amethystbud.BlockSmallAmethystBudBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSmallAmethystBudBehaviorImpl extends BlockBehaviorImpl implements BlockSmallAmethystBudBehavior {
    public BlockSmallAmethystBudBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
