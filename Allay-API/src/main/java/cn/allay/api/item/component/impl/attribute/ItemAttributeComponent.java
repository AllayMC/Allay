package cn.allay.api.item.component.impl.attribute;

import cn.allay.api.component.annotation.Inject;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemAttributeComponent {
    @Inject
    ItemAttributes getItemAttributes();
}
