---
comments: true
---

# Use Debug Shapes

Debug shape is a feature that is introduced in 1.21.90. It allows the server to draw a set of
different types of shapes client-side, which would be very useful.

## Adding Debug Shapes to a Dimension

To add a debug shape to a dimension, use the `addDebugShape` method provided by the `Dimension` interface. Here's an example of adding a debug box:

```java linenums="1" hl_lines="10"
// Create a DebugBox
DebugBox debugBox = new DebugBox(
    new Vector3f(100, 64, 100), // Position
    Color.RED,                  // Color
    1.0f,                       // Scale
    new Vector3f(2, 2, 2)       // Box bounds
);

// Add the debug shape to the dimension
dimension.addDebugShape(debugBox);
```

After the debug shape is added to the dimension, it will be sent to the player who joined the dimension,
and will be automatically removed from the player when the player leaves the dimension.

## Removing Debug Shapes

To remove a debug shape, use the `removeDebugShape` method:

```java linenums="1"
// Remove the debug shape from the dimension
dimension.removeDebugShape(debugBox);
```

To remove all debug shapes in a dimension, use:

```java linenums="1"
// Remove all debug shapes
dimension.removeAllDebugShapes();
```

## Types of Debug Shapes

Allay provides several types of debug shapes, each with unique properties:

### DebugBox
A box with customizable position, color, scale, and bounds.

```java linenums="1"
DebugBox debugBox = new DebugBox(
    new Vector3f(100, 64, 100),
    Color.BLUE,
    1.5f,
    new Vector3f(3, 3, 3)
);
```

### DebugLine
A line with a start and end position.

```java linenums="1"
DebugLine debugLine = new DebugLine(
    new Vector3f(100, 64, 100),
    Color.GREEN,
    new Vector3f(110, 64, 110)
);
```

### DebugArrow
An arrow with a start and end position, and customizable arrowhead properties.

```java linenums="1"
DebugArrow debugArrow = new DebugArrow(
    new Vector3f(100, 64, 100),
    Color.YELLOW,
    new Vector3f(110, 64, 110),
    1.0f, // Arrowhead length
    0.5f, // Arrowhead radius
    4,    // Arrowhead segments
    1.0f  // Arrowhead scale
);
```

### DebugCircle
A circle with customizable position, color, scale, and segments.

```java linenums="1"
DebugCircle debugCircle = new DebugCircle(
    new Vector3f(100, 64, 100),
    Color.MAGENTA,
    2.0f, // Scale
    30    // Segments
);
```

### DebugSphere
A sphere with customizable position, color, scale, and segments.

```java linenums="1"
DebugSphere debugSphere = new DebugSphere(
    new Vector3f(100, 64, 100),
    Color.CYAN,
    1.0f, // Scale
    20    // Segments
);
```

### DebugText
A text marker with customizable position, color, scale, and text content.

```java linenums="1"
DebugText debugText = new DebugText(
    new Vector3f(100, 64, 100),
    Color.WHITE,
    "Hello, Debug!",
    1.0f // Scale
);
```

## Viewing Debug Shapes

Debug shapes are visible to players who are added as viewers. Use the `addViewer` method to make a debug shape visible to a specific player:

```java linenums="1"
// Add a viewer to the debug shape
debugBox.addViewer(player);
```

To remove a viewer:

```java linenums="1"
// Remove a viewer from the debug shape
debugBox.removeViewer(player);
```
