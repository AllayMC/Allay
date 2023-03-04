package cn.allay.component.interfaces;

import cn.allay.component.annotation.Inject;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface NameComponent {
    @Inject
    String getName();
}
