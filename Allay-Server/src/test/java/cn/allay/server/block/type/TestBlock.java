package cn.allay.server.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.impl.custom.CustomBlockComponent;
import cn.allay.server.block.component.TestComponent;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/16 <br>
 * Allay Project <br>
 */
public interface TestBlock extends Block, TestComponent, CustomBlockComponent {
}
