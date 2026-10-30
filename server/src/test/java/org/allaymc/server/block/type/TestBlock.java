package org.allaymc.server.block.type;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.server.block.component.TestComponent;

/**
 * {@code TestBlock} combines core block behavior with the test component contract.
 *
 * @author daoge_cmd
 */
public interface TestBlock extends BlockBehavior, TestComponent {
}
