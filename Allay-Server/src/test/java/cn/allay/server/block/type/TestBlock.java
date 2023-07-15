package cn.allay.server.block.type;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.custom.CustomBlockComponent;
import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.server.block.component.TestAutoRegisterComponent;
import cn.allay.server.block.component.TestAutoRegisterComponentImpl;
import cn.allay.server.block.component.TestComponent;

/**
 * Allay Project 2023/4/16
 *
 * @author daoge_cmd
 */
public interface TestBlock extends BlockBehavior, TestComponent, CustomBlockComponent, TestAutoRegisterComponent {

    @AutoRegister
    BlockComponentImpl TEST_AUTO_REGISTER_COMPONENT = new TestAutoRegisterComponentImpl(true);
}
