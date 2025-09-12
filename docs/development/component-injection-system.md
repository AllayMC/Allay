---
comments: true
---

# Understanding the Component Injection System

The AllayMC includes a lightweight yet powerful component injection system designed to manage dependencies and
lifecycle events for modular components. This system simplifies the construction of complex objects by automating
dependency resolution and initialization. This article explains how it works, its key concepts, and how to use it
effectively.

## Overview

The component injection system in AllayMC is inspired by dependency injection framework like **Guice** but
tailored for simplicity and performance in a game server context. It allows developers to define modular `Component`
implementations, specify their dependencies, and let the system handle instantiation and wiring. This reduces
boilerplate code and ensures consistent initialization.

The system uses annotations and reflection to manage dependencies, making it flexible yet easy to understand once you
grasp the core concepts.

## How It Works

The injection process begins with the creation of component instances. A `ComponentClass` receives a list of
`ComponentProvider` and `ComponentInitInfo`, and each provider creates a `Component` instance using the provided
information.
The system then inspects fields annotated with `@Dependency` and resolves dependencies. If the identifier is not
provided,
the component is selected by type. If it is provided, the system performs an exact match search. If the dependency is
not found and is not marked as `optional`, a `ComponentInjectException` is thrown.

Once all dependencies are resolved, they are injected into the corresponding fields. Fields annotated with

- `@ComponentObject` - receive a reference to the owning `ComponentClass`.
- `@Manager` - receive a `ComponentManager`.

The system then registers the components as event listeners in the `EventBus`, allowing them to respond
to changes and interact with other parts of the system.

### Lifecycle Management

- **Post-Initialization**: Methods annotated with `@OnInitFinish` are called after all injections, receiving the
  `ComponentInitInfo`.

## Usage Guide

AllayMC provides an easy way to register and inject components into:

- Items
- Blocks
- Block entities
- Entities

As an example, we will consider component injection into **items**.

### Defining a Components

Implement the `ItemComponent` interface and define an identifier:

```java
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

public class ItemSayHiComponentImpl implements ItemComponent {
    @Identifier.Component/*(1)!*/
    public static final Identifier IDENTIFIER = new Identifier("my:say_hi_component");

    @ComponentObject/*(2)!*/
    private ItemStack thisItem;

    @EventHandler
    public void onItemSayHi(CItemBreakBlockEvent event) {
        System.out.println("Hi from ItemSayHiComponent(" + thisItem.getItemType().getIdentifier() + ")!");
    }
}
```

1. Used for the `@Dependency` annotation. Defining an identifier for a component is optional but recommended to avoid
   conflicts. If we have `ComponentA` and `ComponentB` without identifiers, `ComponentB` will override `ComponentA` upon
   addition.
2. This annotation injects the component owner into the component itself. If this component is added to
   `ItemTypes.STICK`,
   the `thisItem` field will reference `ItemStickStackImpl`.

```java
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

public class ItemStickBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemStickBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void rightClickItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        interactInfo.player().sendText("Hi from base component!");
        super.rightClickItemOn(dimension, placeBlockPos, interactInfo);
    }
}
```

!!! tip

    You don’t need to create an `IDENTIFIER` when extending `ItemBaseComponentImpl` because it already defines one:
    ```java
    public class ItemBaseComponentImpl implements ItemBaseComponent {
        @Identifier.Component
        public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");
    }
    ```

### Adding Components to Items

```java
ItemTypes.STICK = AllayItemType
        .builder(ItemStickStackImpl.class)
        .vanillaItem(ItemId.STICK)
        .addComponent(ItemStickBaseComponentImpl::new, ItemStickBaseComponentImpl.class)
        .addComponent(ItemSayHiComponentImpl::new, ItemSayHiComponentImpl.class)
        .build();

ItemTypes.CARROT_ON_A_STICK = AllayItemType
        .builder(ItemCarrotOnAStickStackImpl.class)
        .vanillaItem(ItemId.CARROT_ON_A_STICK)
        .addComponent(ItemSayHiComponentImpl::new, ItemSayHiComponentImpl.class)
        .build();
```

Here, `ItemStickBaseComponentImpl` is the **main component** that provides core functionality,
while `ItemSayHiComponentImpl` is an **additional component** that extends behavior.
A single main component can exist per item, but multiple additional components can be registered.

If we break a block using `STICK`, it will output:

```text
Hi from ItemSayHiComponent(minecraft:stick)!
```

And for `CARROT_ON_A_STICK`:

```text
Hi from ItemSayHiComponent(minecraft:carrot_on_a_stick)!
```

This is a basic example of working with components. More advanced examples can be
found [here](https://github.com/AllayMC/Allay/tree/master/server/src/main/java/org/allaymc/server/item/component).

## Conclusion

The AllayMC component injection system offers a flexible way to build modular, dependency-driven applications. By
understanding component creating, and the annotation-driven injection process, developers can create
maintainable and scalable game logic.

