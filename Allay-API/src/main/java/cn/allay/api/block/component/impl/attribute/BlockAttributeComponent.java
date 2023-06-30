package cn.allay.api.block.component.impl.attribute;

import cn.allay.api.component.annotation.Inject;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface BlockAttributeComponent {
    @Inject
    BlockAttributes getBlockAttributes();
}
