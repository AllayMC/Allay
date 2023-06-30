package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;

/**
 * Allay Project 2023/4/21
 *
 * @author daoge_cmd
 */
public interface TestInitArgComponent {
    @Inject
    int getData();
}
