package cn.allay.component;

import cn.allay.component.api.ComponentGroup;
import cn.allay.component.api.RuntimeComponentObject;
import cn.allay.component.group.SimpleComponentGroup;
import cn.allay.component.impl.SimpleAttackComponent;
import cn.allay.component.impl.SimpleHealthComponent;
import cn.allay.component.impl.SimpleNameComponent;
import cn.allay.component.injector.SimpleComponentInjector;
import cn.allay.component.interfaces.Sheep;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class ComponentTest {

    protected static ComponentGroup<Sheep> componentGroup;
    protected static Class<Sheep> parentClass;
    protected static List<Object> components;
    protected static Sheep sheep;

    @BeforeAll
    public static void init() {
        parentClass = Sheep.class;
        components = List.of(
                new SimpleNameComponent("Sheep"),
                new SimpleHealthComponent(20),
                new SimpleAttackComponent());
        componentGroup = new SimpleComponentGroup<>(
                parentClass,
                components
        );
    }

    @SneakyThrows
    @Test
    public void testInjector() {
        sheep = new SimpleComponentInjector<>(componentGroup)
                .inject()
                .getDeclaredConstructor()
                .newInstance();
        assertEquals("Sheep", sheep.getName());
        assertEquals(20, sheep.getHealth());
        sheep.attack(10);
        assertEquals(10, sheep.getHealth());
        var runtime = (RuntimeComponentObject) sheep;
        assertEquals(runtime.getComponents(), components);
    }

    @Test
    public void testComponentGroup() {
        assertEquals(componentGroup.getComponents(), components);
    }

    @Test
    public void testParentClass() {
        assertEquals(componentGroup.getParentClass(), parentClass);
    }
}