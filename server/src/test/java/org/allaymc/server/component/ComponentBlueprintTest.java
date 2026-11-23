package org.allaymc.server.component;

import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.server.block.component.BlockStrippableComponentImpl;
import org.allaymc.server.block.component.fallable.BlockSuspiciousFallableComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.utils.ComponentInjectException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ComponentBlueprintTest} verifies build-time validation, deterministic
 * lifecycle execution, contract-key resolution, type-level event dispatch,
 * singleton statelessness enforcement, and compile-time {@link GenerateImpl}
 * delegation without booting {@code Server.getInstance()}.
 *
 * @author daoge_cmd
 */
class ComponentBlueprintTest {

    /**
     * Compiles a composed object with base, sibling, and shared singleton
     * components without initializing the global server. Wiring must finish after
     * the subclass no-arg constructor runs, execute lifecycle hooks in base-first
     * order, and preserve subclass lifecycle overrides without annotations.
     */
    @Test
    void testBlueprintLifecycleOrderAndNoServerDependency() {
        List<String> log = new ArrayList<>();
        Map<Class<?>, ComponentProvider<? extends Component>> providers = new LinkedHashMap<>();
        var siblingProvider = ComponentProvider.of(() -> new RecordingSiblingComponentImpl(log), RecordingSiblingComponentImpl.class);
        providers.put(siblingProvider.getComponentKey(), siblingProvider);
        var defaultBase = ComponentProvider.of(() -> new DerivedBaseComponentImpl(log), DerivedBaseComponentImpl.class);
        var ordered = ComponentBlueprint.orderWithBaseFirst(providers, BaseFixtureComponent.class, defaultBase);

        var blueprint = ComponentBlueprint.compile(FixtureOwner.class, ordered, FixtureInitInfo.class);
        var owner = blueprint.newInstance(new FixtureInitInfo("hello"));

        assertTrue(owner.subclassFieldInitializedBeforeHook);
        assertEquals("derived:hello", owner.baseLabel());
        assertEquals("sibling:derived:hello", owner.siblingLabel());
        assertEquals(List.of("base:hello", "sibling:hello"), log);
        assertSame(owner.base, owner.getComponent(DerivedBaseComponentImpl.class));
    }

    /**
     * Dispatches an internal component event through a compiled blueprint with
     * multiple prioritized handlers and one throwing handler. Higher priorities
     * must execute first, equal priorities must preserve registration order, and
     * handler runtime exceptions must propagate rather than being swallowed.
     */
    @Test
    void testEventPriorityOrderingAndExceptionPropagation() {
        List<String> order = new ArrayList<>();
        var p1 = ComponentProvider.of(() -> new LowPriorityListener(order), LowPriorityListener.class);
        var p2 = ComponentProvider.of(() -> new HighPriorityListener(order), HighPriorityListener.class);
        var p3 = ComponentProvider.of(() -> new TieListener(order), TieListener.class);
        var blueprint = ComponentBlueprint.compile(EmptyOwner.class, List.of(p1, p2, p3), null);

        var owner = blueprint.newInstance(new FixtureInitInfo("evt"));
        owner.getManager().callEvent(new PingEvent("go"));
        assertEquals(List.of("high", "low", "tie"), order);

        var failingBlueprint = ComponentBlueprint.compile(
                EmptyOwner.class,
                List.of(ComponentProvider.singleton(new ThrowingListener())),
                null
        );
        var failingOwner = failingBlueprint.newInstance(new FixtureInitInfo("boom"));
        var ex = assertThrows(IllegalStateException.class, () -> failingOwner.getManager().callEvent(new PingEvent("go")));
        assertEquals("boom", ex.getMessage());
    }

    /**
     * Compiles invalid blueprints with missing required components, ambiguous
     * candidates, missing required dependencies, incompatible owner references,
     * and stateful singletons. Every misconfiguration must fail fast during
     * blueprint compilation before any instance is created.
     */
    @Test
    void testBuildTimeFailFastValidation() {
        // Missing required owner delegate field.
        assertThrows(ComponentInjectException.class, () ->
                ComponentBlueprint.compile(FixtureOwner.class, List.of(), FixtureInitInfo.class));

        // Ambiguous owner delegate match.
        assertThrows(ComponentInjectException.class, () ->
                ComponentBlueprint.compile(FixtureOwner.class, List.of(
                        ComponentProvider.of(() -> new BaseFixtureComponentImpl(new ArrayList<>()), BaseFixtureComponentImpl.class),
                        ComponentProvider.of(() -> new DerivedBaseComponentImpl(new ArrayList<>()), DerivedBaseComponentImpl.class),
                        ComponentProvider.of(() -> new RecordingSiblingComponentImpl(new ArrayList<>()), RecordingSiblingComponentImpl.class)
                ), FixtureInitInfo.class));

        // Missing required @Dependency on sibling component.
        assertThrows(ComponentInjectException.class, () ->
                ComponentBlueprint.compile(EmptyOwner.class, List.of(
                        ComponentProvider.of(() -> new RecordingSiblingComponentImpl(new ArrayList<>()), RecordingSiblingComponentImpl.class)
                ), FixtureInitInfo.class));

        // Incompatible @ComponentObject field type.
        assertThrows(ComponentInjectException.class, () ->
                ComponentBlueprint.compile(EmptyOwner.class, List.of(
                        ComponentProvider.of(WrongOwnerComponentImpl::new, WrongOwnerComponentImpl.class)
                ), FixtureInitInfo.class));

        // Singleton component illegally declaring @ComponentObject or ComponentLifecycle.
        assertThrows(ComponentInjectException.class, () ->
                ComponentBlueprint.compile(FixtureOwner.class, List.of(
                        ComponentProvider.singleton(new BaseFixtureComponentImpl(new ArrayList<>())),
                        ComponentProvider.of(() -> new RecordingSiblingComponentImpl(new ArrayList<>()), RecordingSiblingComponentImpl.class)
                ), FixtureInitInfo.class));

        // Optional dependency builds cleanly when absent.
        var optionalBlueprint = ComponentBlueprint.compile(EmptyOwner.class, List.of(
                ComponentProvider.of(OptionalDepComponentImpl::new, OptionalDepComponentImpl.class)
        ), null);
        var optionalOwner = optionalBlueprint.newInstance(new FixtureInitInfo("opt"));
        assertNull(optionalOwner.getComponent(OptionalDepComponentImpl.class).optionalBase);
    }

    /**
     * Resolves canonical contract keys across specialized subclasses and
     * event-only listener components. Subclasses must resolve to their domain
     * top-level contract interface while distinct marker-only listeners retain
     * their own concrete classes and coexist without collision.
     */
    @Test
    void testContractKeyResolution() {
        assertEquals(
                org.allaymc.api.entity.component.EntityBaseComponent.class,
                ComponentProvider.resolveComponentKey(EntityPlayerBaseComponentImpl.class)
        );
        assertEquals(
                org.allaymc.api.block.component.BlockFallableComponent.class,
                ComponentProvider.resolveComponentKey(BlockSuspiciousFallableComponentImpl.class)
        );
        assertEquals(
                org.allaymc.api.block.component.BlockStrippableComponent.class,
                ComponentProvider.resolveComponentKey(BlockStrippableComponentImpl.class)
        );
        assertEquals(LowPriorityListener.class, ComponentProvider.resolveComponentKey(LowPriorityListener.class));
        assertEquals(HighPriorityListener.class, ComponentProvider.resolveComponentKey(HighPriorityListener.class));
    }

    /**
     * Resolves and wires the compile-time generated implementation for an
     * interface annotated with {@link GenerateImpl}. The generated class must be
     * discoverable by interface lookup, delegate its custom component method, and
     * expose its generated component getter.
     */
    @Test
    void testGenerateImplAnnotationProcessor() {
        Class<? extends ComponentClass> resolved = ComponentBlueprint.resolveImplClass(
                CustomPluginComposedFixture.class,
                CustomPluginComposedFixture.class.getClassLoader()
        );
        assertEquals(CustomPluginComposedFixtureImpl.class, resolved);

        var blueprint = ComponentBlueprint.compile(
                resolved,
                List.of(ComponentProvider.singleton(new CustomBadgeComponentImpl("vip"))),
                null
        );
        var instance = (CustomPluginComposedFixture) blueprint.newInstance(new FixtureInitInfo("apt"));
        assertEquals("badge:vip", instance.getBadge());
        assertEquals("default-tag", instance.ownerDefaultTag());
        assertNotNull(((CustomPluginComposedFixtureImpl) instance).getCustomBadgeComponent());
    }

    /** Initialization payload fixture for blueprint unit tests. */
    record FixtureInitInfo(String tag) implements ComponentInitInfo {
    }

    /** Internal ping event fixture used to test dispatch order and exceptions. */
    static final class PingEvent extends Event {
        /** Payload tag carried by the event. */
        final String value;

        /** Creates a ping event carrying {@code value}. */
        PingEvent(String value) {
            this.value = value;
        }
    }

    /** Base component contract fixture. */
    interface BaseFixtureComponent extends Component {
        /** Returns the label populated during lifecycle initialization. */
        String baseLabel();
    }

    /** Sibling component contract fixture. */
    interface SiblingFixtureComponent extends Component {
        /** Returns a label combining sibling and base component state. */
        String siblingLabel();
    }

    /** Minimal owner class with no delegated component fields. */
    static class EmptyOwner extends ComponentClass {
        /** Creates an empty owner wired by a blueprint. */
        EmptyOwner() {
            super();
        }
    }

    /** Owner class verifying post-constructor wiring and subclass field initialization. */
    static class FixtureOwner extends ComponentClass {
        /** Flag initialized by the subclass constructor prior to blueprint wiring. */
        final boolean initializedInConstructor = true;
        /** Captures whether {@code initializedInConstructor} was already true during {@code onInitFinish}. */
        boolean subclassFieldInitializedBeforeHook;
        /** Injected base component fixture. */
        BaseFixtureComponent base;
        /** Injected sibling component fixture. */
        SiblingFixtureComponent sibling;

        /** Creates an owner whose field initializers run before blueprint wiring. */
        FixtureOwner() {
            super();
        }

        /** Delegates to the wired base component. */
        String baseLabel() {
            return base.baseLabel();
        }

        /** Delegates to the wired sibling component. */
        String siblingLabel() {
            return sibling.siblingLabel();
        }
    }

    /** Base component implementation recording lifecycle invocation and owner readiness. */
    static class BaseFixtureComponentImpl implements BaseFixtureComponent, ComponentLifecycle<FixtureInitInfo> {
        /** Shared invocation log. */
        protected final List<String> log;
        /** Stored label initialized in {@code onInitFinish}. */
        protected String label;
        /** Injected owning fixture. */
        @ComponentObject
        protected FixtureOwner owner;
        /** Injected component manager. */
        @Manager
        protected ComponentManager manager;

        /** Creates a base component writing hook traces to {@code log}. */
        BaseFixtureComponentImpl(List<String> log) {
            this.log = log;
        }

        /** Records lifecycle execution and verifies subclass construction completed first. */
        @Override
        public void onInitFinish(FixtureInitInfo initInfo) {
            owner.subclassFieldInitializedBeforeHook = owner.initializedInConstructor;
            label = "base:" + initInfo.tag();
            log.add("base:" + initInfo.tag());
        }

        /** Returns the initialized base label. */
        @Override
        public String baseLabel() {
            return label;
        }
    }

    /** Subclass overriding {@code onInitFinish} without any annotation to test inheritance. */
    static final class DerivedBaseComponentImpl extends BaseFixtureComponentImpl {
        /** Creates a derived base component writing hook traces to {@code log}. */
        DerivedBaseComponentImpl(List<String> log) {
            super(log);
        }

        /** Overrides lifecycle initialization to verify inherited {@link ComponentLifecycle} dispatch. */
        @Override
        public void onInitFinish(FixtureInitInfo initInfo) {
            super.onInitFinish(initInfo);
            label = "derived:" + initInfo.tag();
        }
    }

    /** Sibling component depending on {@link BaseFixtureComponent}. */
    static final class RecordingSiblingComponentImpl implements SiblingFixtureComponent, ComponentLifecycle<FixtureInitInfo> {
        /** Shared invocation log. */
        private final List<String> log;
        /** Required base component dependency. */
        @Dependency
        private BaseFixtureComponent base;

        /** Creates a sibling component writing hook traces to {@code log}. */
        RecordingSiblingComponentImpl(List<String> log) {
            this.log = log;
        }

        /** Records sibling lifecycle execution after the base component. */
        @Override
        public void onInitFinish(FixtureInitInfo initInfo) {
            log.add("sibling:" + initInfo.tag());
        }

        /** Returns a label combining sibling prefix with the wired base label. */
        @Override
        public String siblingLabel() {
            return "sibling:" + base.baseLabel();
        }
    }

    /** Component declaring an incompatible {@link ComponentObject} field type. */
    static final class WrongOwnerComponentImpl implements ItemComponent {
        /** Owner field incompatible with {@link EmptyOwner}. */
        @ComponentObject
        private Entity wrongOwner;
    }

    /** Component declaring an optional dependency that may be omitted. */
    static final class OptionalDepComponentImpl implements ItemComponent {
        /** Optional dependency allowed to remain {@code null}. */
        @Dependency(optional = true)
        private BaseFixtureComponent optionalBase;
    }

    /** Event-only component listening at default priority zero. */
    static final class LowPriorityListener implements ItemComponent {
        /** Shared dispatch order log. */
        private final List<String> order;

        /** Creates a listener appending to {@code order}. */
        LowPriorityListener(List<String> order) {
            this.order = order;
        }

        /** Records default-priority dispatch. */
        @EventHandler
        private void onPing(PingEvent event) {
            order.add("low");
        }
    }

    /** Event-only component listening at high priority ten. */
    static final class HighPriorityListener implements ItemComponent {
        /** Shared dispatch order log. */
        private final List<String> order;

        /** Creates a high-priority listener appending to {@code order}. */
        HighPriorityListener(List<String> order) {
            this.order = order;
        }

        /** Records high-priority dispatch. */
        @EventHandler(priority = 10)
        private void onPing(PingEvent event) {
            order.add("high");
        }
    }

    /** Second default-priority listener verifying registration-order tie breaking. */
    static final class TieListener implements ItemComponent {
        /** Shared dispatch order log. */
        private final List<String> order;

        /** Creates a tie-breaking listener appending to {@code order}. */
        TieListener(List<String> order) {
            this.order = order;
        }

        /** Records second default-priority dispatch. */
        @EventHandler
        private void onPing(PingEvent event) {
            order.add("tie");
        }
    }

    /** Stateless event listener throwing an unchecked exception during dispatch. */
    static final class ThrowingListener implements ItemComponent {
        /** Throws an {@link IllegalStateException} to verify propagation. */
        @EventHandler
        private void onPing(PingEvent event) {
            throw new IllegalStateException("boom");
        }
    }

    /** Custom component contract used to test compile-time {@link GenerateImpl} processing. */
    public interface CustomBadgeComponent extends EntityComponent {
        /** Returns the formatted badge string. */
        String getBadge();
    }

    /** Stateless implementation of {@link CustomBadgeComponent}. */
    static final class CustomBadgeComponentImpl implements CustomBadgeComponent {
        /** Badge suffix value. */
        private final String value;

        /** Creates a stateless badge component returning {@code "badge:" + value}. */
        CustomBadgeComponentImpl(String value) {
            this.value = value;
        }

        /** Returns the badge text prefixed with {@code "badge:"}. */
        @Override
        public String getBadge() {
            return "badge:" + value;
        }
    }

    /** Minimal base class used by the {@code GenerateImpl} test fixture. */
    public static class CustomBaseFixture extends ComponentClass {
        /** Creates an uninitialized fixture instance for blueprint wiring. */
        public CustomBaseFixture() {
            super();
        }
    }
}
