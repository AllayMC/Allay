package cn.allay.server.block.type;

import cn.allay.api.block.Block;
import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.impl.custom.CustomBlockComponent;
import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.server.block.component.TestAutoRegisterComponent;
import cn.allay.server.block.component.TestAutoRegisterComponentImpl;
import cn.allay.server.block.component.TestComponent;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/16 <br>
 * Allay Project <br>
 */
public interface TestBlock extends Block, TestComponent, CustomBlockComponent, TestAutoRegisterComponent {

    @AutoRegister
    ComponentProvider<? extends BlockComponentImpl> TEST_AUTO_REGISTER_COMPONENT = ComponentProvider.ofSingleton(new TestAutoRegisterComponentImpl(true));
}
