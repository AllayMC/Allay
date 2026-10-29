---
comments: true
---

# Use Primitive Shapes

Primitive shape is a feature that is introduced in 1.21.90. It allows the server to draw a set of
different types of shapes client-side, which would be very useful.

## Adding Primitive Shapes to a Dimension

To add a primitive shape to a dimension, use the `addPrimitiveShape` method provided by the `Dimension` interface. Here's an example of adding a primitive box:

```java linenums="1" hl_lines="10"
// Create a PrimitiveBox
PrimitiveBox primitiveBox = new PrimitiveBox(
    new Vector3f(100, 64, 100), // Position
    Color.RED,                  // Color
    1.0f,                       // Scale
    new Vector3f(2, 2, 2)       // Box bounds
);

// Add the primitive shape to the dimension
dimension.addPrimitiveShape(primitiveBox);
```

After the primitive shape is added to the dimension, it will be sent to the player who joined the dimension,
and will be automatically removed from the player when the player leaves the dimension.

## Removing Primitive Shapes

To remove a primitive shape, use the `removePrimitiveShape` method:

```java linenums="1"
// Remove the primitive shape from the dimension
dimension.removePrimitiveShape(primitiveBox);
```

To remove all primitive shapes in a dimension, use:

```java linenums="1"
// Remove all primitive shapes
dimension.removeAllPrimitiveShapes();
```

## Types of Primitive Shapes

Allay provides several types of primitive shapes, each with unique properties:

### PrimitiveBox
A box with customizable position, color, scale, and bounds.

```java linenums="1"
PrimitiveBox primitiveBox = new PrimitiveBox(
    new Vector3f(100, 64, 100),
    Color.BLUE,
    1.5f,
    new Vector3f(3, 3, 3)
);
```

### PrimitiveLine
A line with a start and end position.

```java linenums="1"
PrimitiveLine primitiveLine = new PrimitiveLine(
    new Vector3f(100, 64, 100),
    Color.GREEN,
    new Vector3f(110, 64, 110)
);
```

### PrimitiveArrow
An arrow with a start and end position, and customizable arrowhead properties.

```java linenums="1"
PrimitiveArrow primitiveArrow = new PrimitiveArrow(
    new Vector3f(100, 64, 100),
    Color.YELLOW,
    new Vector3f(110, 64, 110),
    1.0f, // Arrowhead length
    0.5f, // Arrowhead radius
    4,    // Arrowhead segments
    1.0f  // Scale
);
```

### PrimitiveCircle
A circle with customizable position, color, scale, and segments.

```java linenums="1"
PrimitiveCircle primitiveCircle = new PrimitiveCircle(
    new Vector3f(100, 64, 100),
    Color.MAGENTA,
    2.0f, // Scale
    30    // Segments
);
```

### PrimitiveSphere
A sphere with customizable position, color, scale, and segments.

```java linenums="1"
PrimitiveSphere primitiveSphere = new PrimitiveSphere(
    new Vector3f(100, 64, 100),
    Color.CYAN,
    1.0f, // Scale
    20    // Segments
);
```

### PrimitiveText
A text marker with customizable position, color, scale, and text content.

```java linenums="1"
PrimitiveText primitiveText = new PrimitiveText(
    new Vector3f(100, 64, 100),
    Color.WHITE,
    "Hello, Primitive!",
    1.0f // Scale
);
```

## Viewing Primitive Shapes

Primitive shapes are visible to players who are added as viewers. Use the `addViewer` method to make a primitive shape visible to a specific player:

```java linenums="1"
// Add a viewer to the primitive shape
primitiveBox.addViewer(player);
```

To remove a viewer:

```java linenums="1"
// Remove a viewer from the primitive shape
primitiveBox.removeViewer(player);
```
