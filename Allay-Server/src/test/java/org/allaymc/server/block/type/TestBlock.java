package org.allaymc.server.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.CustomBlockComponent;
import org.allaymc.server.block.component.TestComponent;

/**
 * @author daoge_cmd
 */
public interface TestBlock extends BlockBehavior, TestComponent, CustomBlockComponent {
}
