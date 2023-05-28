package cn.allay.component.impl;

import cn.allay.component.annotation.Impl;
import cn.allay.component.annotation.Manager;
import cn.allay.component.event.TestEvent;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.ComponentManager;
import cn.allay.component.interfaces.TestEventTriggerComponent;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/6 <br>
 * Allay Project <br>
 */
public class SimpleTestEventTriggerComponent implements TestEventTriggerComponent, ComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_event_trigger_component");
    @Manager
    protected ComponentManager manager;

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @Impl
    @Override
    public TestEvent triggerEvent(String message) {
        return manager.callEvent(new TestEvent(message));
    }
}
