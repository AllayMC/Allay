package cn.allay.server.block.component;

import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.component.annotation.Inject;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public interface TestAutoRegisterComponent {
    @Inject
    boolean getTestFlag();
}
