package cn.allay.component;

import cn.allay.component.exception.ComponentInjectException;
import cn.allay.component.impl.SimpleAttackComponent;
import cn.allay.component.impl.SimpleHealthComponent;
import cn.allay.component.impl.SimpleNameComponent;
import cn.allay.component.impl.SimpleTestDependencyComponent;
import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.component.interfaces.*;
import cn.allay.identifier.Identifier;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
class ComponentTest {

    protected static Class<Sheep> parentClass;
    protected static List<ComponentProvider<?>> components;
    protected static Sheep sheep;

    @BeforeAll
    static void init() {
        parentClass = Sheep.class;
        components = List.of(
                ComponentProvider.of(() -> new SimpleNameComponent("Sheep"), SimpleNameComponent.class),
                ComponentProvider.of(() -> new SimpleHealthComponent(20), SimpleHealthComponent.class),
                ComponentProvider.of(SimpleAttackComponent::new, SimpleAttackComponent.class),
                ComponentProvider.of(SimpleTestDependencyComponent::new, SimpleTestDependencyComponent.class)
        );
    }

    @SneakyThrows
    @Test
    void testInjector() {
        sheep = new AllayComponentInjector<Sheep>()
                .parentClass(parentClass)
                .withComponent(components)
                .inject()
                .getDeclaredConstructor()
                .newInstance();
        assertEquals("Sheep", sheep.getName());
        assertEquals(20, sheep.getHealth());
        sheep.attack(10);
        assertEquals(10, sheep.getHealth());
        var runtime = (ComponentedObject) sheep;
        assertEquals(components, runtime.getComponents());
        assertEquals(sheep.getName(), ((NameComponent) sheep.getNameComponent()).getName());
        assertEquals(sheep.getHealth(), ((HealthComponent) sheep.getHealthComponent()).getHealth());
        assertEquals(sheep.getMaxHealth(), ((HealthComponent) sheep.getHealthComponent()).getMaxHealth());
        ((AttackComponent) sheep.getAttackComponent()).attack(10);
        assert sheep.isDead();
        components.add(ComponentProvider.of(() -> new SimpleNameComponentV2("SmallSheep"), SimpleNameComponentV2.class));
        assertThrows(
                ComponentInjectException.class,
                () -> new AllayComponentInjector<Sheep>()
                        .parentClass(parentClass)
                        .withComponent(components)
                        .inject());
    }

    public static class SimpleNameComponentV2 extends SimpleNameComponent {

        private static final Identifier IDENTIDIER = new Identifier("minecraft:name_component_2");

        public SimpleNameComponentV2(String name) {
            super(name);
        }

        @Override
        public Identifier getNamespaceId() {
            return IDENTIDIER;
        }
    }
}