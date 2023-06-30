package cn.allay.server.block.component;

import cn.allay.api.component.annotation.Inject;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public interface TestAutoRegisterComponent {
    @Inject
    boolean getTestFlag();
}
