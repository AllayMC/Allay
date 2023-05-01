package cn.allay.block.component.impl.attribute;

import cn.allay.component.annotation.Inject;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
public interface BlockAttributeComponent {
    @Inject
    BlockAttributes getBlockAttributes();
}
