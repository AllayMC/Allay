package cn.allay.server.block.type;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.custom.CustomBlockComponent;
import cn.allay.server.block.component.TestComponent;

/**
 * Allay Project 2023/4/16
 *
 * @author daoge_cmd
 */
public interface TestBlock extends BlockBehavior, TestComponent, CustomBlockComponent {
}
