---
comments: true
---

# Persistent Data Container (PDC)

The Persistent Data Container (PDC) is a way to store custom data on a whole range of objects, such as items, entities,
and block entities. The full list of classes that support the PDC are:

- World
- Entity
- BlockEntity
- ItemStack

## What is it used for?

In the past, developers resorted to a variety of methods to store custom data on objects:

- NBT tags: Requires reflection to access internals and was generally unreliable in the long term.
- Lore and display names: Prone to collisions as well.

The benefit of the PDC is that it allows for a more reliable and performant way to store arbitrary data on objects. It
also doesn't rely on accessing server internals, so it is less likely to break on future versions. It also removes the
need to manually track the data lifecycle, as, for example, with an entity, the PDC will be saved when the entity
unloads.

## Adding data

To store data in the PDC, there are a few things you need first. The first is a `Identifier`, which is used to identify
the data. The second is a `PersistentDataContainer`, which is the object you want to store the data on. The third is the
data itself.

```java linenums="1" hl_lines="8 12"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.utils.identifier.Identifier;

public class Example {
    public void example() {
        // Create an Identifier
        var key = new Identifier("allaymc", "example-key");

        // ItemStack implements PersistentDataHolder, so we can get the PDC from it
        var itemStack = ItemTypes.DIAMOND.createItemStack();
        itemStack.getPersistentDataContainer().set(key, PersistentDataType.STRING, "I love AllayMC");
    }
}
```

!!! tip

    It is considered good practice to reuse `Identifier` objects. They can be constructed with either:

    - A `Plugin` instance and a `String` identifier
    - A `String` namespace and a `String` identifier

    The first option is often preferred as it will automatically use the plugin's namespace.
    However, the second option can be used if you want to use a different namespace or access the data from another plugin.

## Getting data

To get data from the PDC, you need to know the `Identifier` and the `PersistentDataType` of the data.

```java linenums="1" hl_lines="13-14"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.utils.identifier.Identifier;

public class Example {
    public void example() {
        // Create an Identifier
        var key = new Identifier("allaymc", "example-key");

        var itemStack = ...// Retrieve the item from before
        // Get the data from the PDC
        var pdc = itemStack.getPersistentDataContainer();
        if (pdc.has(key, PersistentDataType.STRING)) {
            var value = pdc.get(key, PersistentDataType.STRING);
            // Do something with the value
            player.sendMessage(value);
        }
    }
}
```

## Data types

The PDC supports a wide range of data types, such as:

- `Byte`, `Byte Array`
- `Double`
- `Float`
- `Integer`, `Integer Array`
- `Long`, `Long Array`
- `Short`
- `String`
- `Boolean`
- `PersistentDataContainer` - a way to nest PDCs within each other.
  To create a new `PersistentDataContainer`, you can use:
  ```java linenums="1"
  // Get the existing container
  PersistentDataContainer container = ...;
  // Create a new container
  PersistentDataContainer newContainer = container.getAdapterContext().newPersistentDataContainer();
  ```
- `List` - a way to represent lists of data that can be stored via another persistent data type.
  You may create them via:
  ```java linenums="1"
  // Storing a list of strings in a container by verbosely creating
  // a list data type wrapping the string data type.
  container.set(
      key,
      PersistentDataType.LIST.listTypeFrom(PersistentDataType.STRING),
      List.of("a", "list", "of", "strings")
  );
  
  // Storing a list of strings in a container by using the api
  // provided pre-definitions of commonly used list types.
  container.set(key, PersistentDataType.LIST.strings(), List.of("a", "list", "of", "strings"));
  
  // Retrieving a list of strings from the container.
  List<String> strings = container.get(key, PersistentDataType.LIST.strings());
  ```

!!! tip

    ## Boolean `PersistentDataType`

    The `Boolean` PDC type exists for convenience

    - you cannot make more complex types distill to a `Boolean`.

## Custom data types

You can store a wide range of data in the PDC with the native adapters.
However, if you need a more complex data type, you can implement your own `PersistentDataType` and use that instead.
The `PersistentDataType`'s job is to "deconstruct" a complex data type into something that is natively supported (see
above) and then vice versa.

Here is an example of how to do that for a UUID:

```java linenums="1"
import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataType;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDDataType implements PersistentDataType<byte[], UUID> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    @Override
    public byte[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
        var buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(complex.getMostSignificantBits());
        buffer.putLong(complex.getLeastSignificantBits());
        return buffer.array();
    }

    @Override
    public UUID fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        var buffer = ByteBuffer.wrap(primitive);
        var firstLong = buffer.getLong();
        var secondLong = buffer.getLong();
        return new UUID(firstLong, secondLong);
    }
}
```

!!! tip

    In order to use your own `PersistentDataType`, you must pass an instance of it to the `get`/ `set`/ `has` methods.
    ```java
    container.set(key, new UUIDDataType(), uuid);
    ```

## Storing on different objects

!!! caution

    Data is **not** copied across holders for you, and needs to be **manually** copied if 'moving' between `PersistentDataHolders`.
    
    
    E.g. Placing an ItemStack as a Block (with a BlockEntity) **does not** copy over PDC data.

Objects that can have a PDC implement the `PersistentDataHolder` interface and their PDC can be fetched with
`PersistentDataHolder#getPersistentDataContainer()`.