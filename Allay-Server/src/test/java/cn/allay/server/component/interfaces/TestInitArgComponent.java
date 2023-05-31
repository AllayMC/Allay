package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/21 <br>
 * Allay Project <br>
 */
public interface TestInitArgComponent {
    @Inject
    int getData();
}
