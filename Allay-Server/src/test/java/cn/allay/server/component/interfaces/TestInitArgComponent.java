package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/21 <br>
 * Allay Project <br>
 */
public interface TestInitArgComponent {
    @Inject
    int getData();
}
