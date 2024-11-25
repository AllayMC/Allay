package org.allaymc.server.block.impl.amethystbud;

import org.allaymc.api.block.interfaces.amethystbud.BlockLargeAmethystBudBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockLargeAmethystBudBehaviorImpl extends BlockBehaviorImpl implements BlockLargeAmethystBudBehavior {
    public BlockLargeAmethystBudBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
