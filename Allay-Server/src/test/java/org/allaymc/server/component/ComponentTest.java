package org.allaymc.server.component;

import lombok.SneakyThrows;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentedObject;
import org.allaymc.server.utils.exception.ComponentInjectException;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.impl.*;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.component.interfaces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
                .inject(true)
                .getDeclaredConstructor(ComponentInitInfo.class)
                .newInstance(new TestComponentInitInfo(114514));
    }

    @Test
    void testCommonFunctions() {
        assertEquals("Sheep", sheep.getName());
        assertEquals(20, sheep.getHealth());
        sheep.attack(10);
        assertEquals(10, sheep.getHealth());
        componentProviders.stream().map(ComponentProvider::findComponentIdentifier).forEach(identifier -> assertNotNull(sheep.getManager().getComponent(identifier)));
        assertNotNull(sheep.getManager().getComponent(SimpleNameComponent.IDENTIFIER));
        assertInstanceOf(ComponentedObject.class, sheep);
        Assertions.assertEquals(sheep.getName(), ((NameComponent) sheep.getNameComponent()).getName());
        Assertions.assertEquals(sheep.getHealth(), ((HealthComponent) sheep.getHealthComponent()).getHealth());
        assertEquals(sheep.getMaxHealth(), ((HealthComponent) sheep.getHealthComponent()).getMaxHealth());
        ((AttackComponent) sheep.getAttackComponent()).attack(10);
        assertTrue(sheep.isDead());
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
                        .inject(true));
    }

    @Test
    void testListener() {
        Assertions.assertEquals("testListener() accepted to the event!", sheep.triggerEvent("origin message").getMessage());
    }

    @Test
    void testAnonymousComponentClass() {
        var componentProvidersV3 = new ArrayList<>(componentProviders);
        componentProvidersV3.add(ComponentProvider.of(() -> new SimpleTestAnonymousClassComponent() {
            @Override
            public void testMethod() {
                // Nothing here
            }
        }, SimpleTestAnonymousClassComponent.class));
        assertDoesNotThrow(() -> {
            new AllayComponentInjector<Sheep>()
                    .interfaceClass(interfaceClass)
                    .component(componentProvidersV3)
                    .inject(true)
                    .getDeclaredConstructor(ComponentInitInfo.class)
                    .newInstance(new TestComponentInitInfo(114514))
                    .testMethod();
        });
    }

    public static class SimpleNameComponentV2 extends SimpleNameComponent {

        @Identifier
        public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:name_component_2");

        public SimpleNameComponentV2(String name) {
            super(name);
        }
    }
}