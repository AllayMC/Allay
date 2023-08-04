package cn.allay.server.component;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.exception.ComponentInjectException;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.component.interfaces.ComponentedObject;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.impl.*;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.component.interfaces.AttackComponent;
import cn.allay.server.component.interfaces.HealthComponent;
import cn.allay.server.component.interfaces.NameComponent;
import cn.allay.server.component.interfaces.Sheep;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
class ComponentTest {

    protected static Class<Sheep> interfaceClass;
    protected static List<ComponentProvider<?>> componentProviders;
    protected static Sheep sheep;

    @SneakyThrows
    @BeforeAll
    static void init() {
        interfaceClass = Sheep.class;
        componentProviders = new ArrayList<>();
        componentProviders.add(ComponentProvider.of(() -> new SimpleNameComponent("Sheep"), SimpleNameComponent.class));
        componentProviders.add(ComponentProvider.of(() -> new SimpleHealthComponent(20), SimpleHealthComponent.class));
        componentProviders.add(ComponentProvider.of(SimpleAttackComponent::new, SimpleAttackComponent.class));
        componentProviders.add(ComponentProvider.of(SimpleTestDependencyComponent::new, SimpleTestDependencyComponent.class));
        componentProviders.add(ComponentProvider.of(info -> {
            var casted = (TestComponentInitInfo) info;
            return new SimpleTestInitArgComponent(casted.data());
        }, SimpleTestInitArgComponent.class));
        componentProviders.add(ComponentProvider.of(SimpleTestEventTriggerComponent::new, SimpleTestEventTriggerComponent.class));
        componentProviders.add(ComponentProvider.ofSingleton(new SimpleTestEventListenerComponent()));
        //testInjector :)
        sheep = new AllayComponentInjector<Sheep>()
                .interfaceClass(interfaceClass)
                .component(componentProviders)
                .inject(false)
                .getDeclaredConstructor(ComponentInitInfo.class)
                .newInstance(new TestComponentInitInfo(114514));
    }

    @Test
    void testCommonFunctions() {
        assertEquals("Sheep", sheep.getName());
        assertEquals(20, sheep.getHealth());
        sheep.attack(10);
        assertEquals(10, sheep.getHealth());
        var runtime = (ComponentedObject) sheep;
        assertEquals(componentProviders.stream().map(ComponentProvider::getComponentClass).toList(), runtime.getComponents().stream().map(Object::getClass).toList());
        Assertions.assertEquals(sheep.getName(), ((NameComponent) sheep.getNameComponent()).getName());
        Assertions.assertEquals(sheep.getHealth(), ((HealthComponent) sheep.getHealthComponent()).getHealth());
        assertEquals(sheep.getMaxHealth(), ((HealthComponent) sheep.getHealthComponent()).getMaxHealth());
        ((AttackComponent) sheep.getAttackComponent()).attack(10);
        assert sheep.isDead();
        assertEquals(114514, sheep.getData());
    }

    @Test
    void testIllegalInject() {
        var componentProvidersV2 = new ArrayList<>(componentProviders);
        componentProvidersV2.add(ComponentProvider.of(() -> new SimpleNameComponentV2("SmallSheep"), SimpleNameComponentV2.class));
        assertThrows(
                ComponentInjectException.class,
                () -> new AllayComponentInjector<Sheep>()
                        .interfaceClass(interfaceClass)
                        .component(componentProvidersV2)
                        .inject(false));

    }

    @Test
    void testListener() {
        Assertions.assertEquals("testListener() accepted to the event!", sheep.triggerEvent("origin message").getMessage());
    }

    public static class SimpleNameComponentV2 extends SimpleNameComponent {

        @ComponentIdentifier
        public static final Identifier IDENTIFIER = new Identifier("minecraft:name_component_2");

        public SimpleNameComponentV2(String name) {
            super(name);
        }
    }
}