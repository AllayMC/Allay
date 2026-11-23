---
comments: true
---

# Understanding the Component System

AllayMC uses a compile-time and build-time component architecture to compose modular behavior across blocks, items,
entities, and block entities without deep single-inheritance hierarchies.

## Overview

Instead of scanning reflection metadata or allocating an `EventBus` for every created object, Allay compiles an
immutable `ComponentBlueprint` once inside `Allay*Type.Builder.build()`:

1. **Contract-class keying**: Components are keyed automatically by their top-level `Component` contract interface
   (such as `EntityPhysicsComponent`, `BlockFallableComponent`, or `ItemToolComponent`), so specialized subclasses
   automatically replace default providers without maintaining static `@Identifier.Component` fields. Event-only
   components that implement only a root marker (`ItemComponent`, `EntityComponent`, etc.) keep their concrete class as
   their key and coexist without collisions.
2. **Build-time fail-fast validation**: `ComponentBlueprint.compile(...)` validates all owner delegate fields,
   required `@Dependency` fields, `@ComponentObject` assignability, `@Manager` fields, singleton statelessness rules,
   and `@EventHandler` signatures when the type is registered.
3. **Zero-reflection wiring and type-level event dispatch**: Wiring uses pre-resolved `VarHandle`s and `MethodHandle`s
   in deterministic insertion order (with the base component placed first). Internal component events (`C*Event`)
   dispatch synchronously through a priority-ordered type-level table without allocating a per-instance event bus or
   swallowing handler exceptions.
4. **Compile-time delegation generation (`:processor`)**: Built-in server `*Impl` classes and plugin custom composed
   interfaces annotated with `@GenerateImpl` are generated in plain Java at compile time by the annotation processor,
   eliminating handwritten `@Delegate` boilerplate.

## Component Annotations & Lifecycle

- `@Dependency` (`optional = false` by default): injects a unique sibling component matching the field type.
- `@ComponentObject`: injects the owning composed object (`Entity`, `ItemStack`, `BlockBehavior`, or `BlockEntity`).
- `@Manager`: injects the lightweight `ComponentManager` used to dispatch internal `C*Event` notifications.
- `ComponentLifecycle<I>`: server-side interface providing `default void onInitFinish(I initInfo)` invoked in
  deterministic base-first order after wiring completes (regular Java inheritance applies, so subclasses overriding
  `onInitFinish` do not need an annotation).

## Usage Guide

### 1. Defining a Stateless Event-Only Component

Stateless components that obtain their owning stack from `CItem*Event.getItemStack()` can be registered once as a
shared singleton across all instances of an item type:

```java
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

public final class ItemSayHiComponentImpl implements ItemComponent {
    public static final ItemSayHiComponentImpl INSTANCE = new ItemSayHiComponentImpl();

    @EventHandler
    protected void onBreakBlock(CItemBreakBlockEvent event) {
        System.out.println("Hi from ItemSayHiComponent(" + event.getItemStack().getItemType().getIdentifier() + ")!");
    }
}
```

### 2. Customizing the Base Component

Extending `ItemBaseComponentImpl` automatically resolves to the `ItemBaseComponent` slot and replaces the default base
provider:

```java
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

public class ItemStickBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemStickBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        interactInfo.player().sendMessage("Hi from base component!");
        return super.useItemOnBlock(dimension, placeBlockPos, interactInfo);
    }
}
```

### 3. Registering Components on a Type

When no extra API interface methods are needed, use the parameterless `builder()` overload (defaulting to
`ItemStackImpl`, `BlockBehaviorImpl`, `EntityImpl`, or `BlockEntityImpl`):

```java
AllayItemType
        .builder()
        .identifier("myplugin:magic_stick")
        .addComponent(ItemStickBaseComponentImpl::new, ItemStickBaseComponentImpl.class)
        .addComponent(ItemSayHiComponentImpl.INSTANCE) // shared stateless singleton
        .build();
```

### 4. Generating a Custom Composed `*Impl` with `@GenerateImpl`

When a plugin exposes a custom composed API interface combining multiple component contracts, annotate the interface
with `@GenerateImpl` and add `annotationProcessor("org.allaymc.allay:processor:<version>")` to the build:

```java
import org.allaymc.api.component.annotation.GenerateImpl;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.component.ItemToolComponent;

@GenerateImpl
public interface MyMagicSword extends ItemStack, ItemToolComponent, ItemEdibleComponent {
}
```

The annotation processor generates `MyMagicSwordImpl` in the same package, and `AllayItemType.builder(MyMagicSword.class)`
resolves it automatically:

```java
AllayItemType
        .builder(MyMagicSword.class)
        .identifier("myplugin:magic_sword")
        .addComponent(ItemSwordToolComponentImpl.INSTANCE)
        .addComponent(new ItemEdibleComponentImpl(4, 2.4f))
        .build();
```
