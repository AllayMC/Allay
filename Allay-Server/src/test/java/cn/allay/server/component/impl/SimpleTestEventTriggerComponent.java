package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.ComponentImpl;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.event.TestEvent;
import cn.allay.server.component.interfaces.TestEventTriggerComponent;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/6 <br>
 * Allay Project <br>
 */
public class SimpleTestEventTriggerComponent implements TestEventTriggerComponent, ComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_event_trigger_component");
    @Manager
    protected ComponentManager manager;

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @Impl
    @Override
    public TestEvent triggerEvent(String message) {
        return manager.callEvent(new TestEvent(message));
    }
}
