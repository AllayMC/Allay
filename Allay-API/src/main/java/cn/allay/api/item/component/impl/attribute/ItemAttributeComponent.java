package cn.allay.api.item.component.impl.attribute;

import cn.allay.api.component.annotation.Inject;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemAttributeComponent {
    @Inject
    ItemAttributes getItemAttributes();
}
