package cn.allay.component;

import cn.allay.component.injector.SimpleComponentInjector;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.ComponentedObject;
import cn.allay.component.impl.SimpleAttackComponent;
import cn.allay.component.impl.SimpleHealthComponent;
import cn.allay.component.impl.SimpleNameComponent;
import cn.allay.component.impl.SimpleTestDependencyComponent;
import cn.allay.component.interfaces.AttackComponent;
import cn.allay.component.interfaces.HealthComponent;
import cn.allay.component.interfaces.NameComponent;
import cn.allay.component.interfaces.Sheep;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
class ComponentTest {

    protected static Class<Sheep> parentClass;
    protected static List<ComponentImpl> components;
    protected static Sheep sheep;

    @BeforeAll
    static void init() {
        parentClass = Sheep.class;
        components = List.of(
                new SimpleNameComponent("Sheep"),
                new SimpleHealthComponent(20),
                new SimpleAttackComponent(),
                new SimpleTestDependencyComponent());
    }

    @SneakyThrows
    @Test
    void testInjector() {
        sheep = new SimpleComponentInjector<Sheep>()
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
    }
}