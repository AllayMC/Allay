package org.allaymc.server.pdc;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.pdc.ListPersistentDataType;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author IWareQ
 */
@ExtendWith(AllayTestExtension.class)
public class PersistentDataContainerTest {
    private static Identifier VALID_KEY;

    @BeforeAll
    public static void setup() {
        VALID_KEY = new Identifier("test", "validkey");
    }

    private static Stream<Arguments> testListTypeArgumentSource() {
        var first = createNewItemStack().getPersistentDataContainer();
        var second = first.getAdapterContext().newPersistentDataContainer();
        first.set(PersistentDataContainerTest.requestKey("a"), PersistentDataType.STRING, "hello world");
        second.set(PersistentDataContainerTest.requestKey("b"), PersistentDataType.BOOLEAN, true);

        BiConsumer<Object, Object> objectAssertion = Assertions::assertEquals;
        BiConsumer<byte[], byte[]> byteArrayAssertion = Assertions::assertArrayEquals;
        BiConsumer<int[], int[]> intArrayAssertion = Assertions::assertArrayEquals;
        BiConsumer<long[], long[]> longArrayAssertion = Assertions::assertArrayEquals;

        return Stream.of(
                Arguments.of(PersistentDataType.LIST.bytes(), List.of((byte) 1, (byte) 2, (byte) 3), objectAssertion),
                Arguments.of(PersistentDataType.LIST.shorts(), List.of((short) 1, (short) 2, (short) 3), objectAssertion),
                Arguments.of(PersistentDataType.LIST.integers(), List.of(1, 2, 3), objectAssertion),
                Arguments.of(PersistentDataType.LIST.longs(), List.of(1L, 2L, 3L), objectAssertion),
                Arguments.of(PersistentDataType.LIST.floats(), List.of(1F, 2F, 3F), objectAssertion),
                Arguments.of(PersistentDataType.LIST.doubles(), List.of(1D, 2D, 3D), objectAssertion),
                Arguments.of(PersistentDataType.LIST.booleans(), List.of(true, true, false), objectAssertion),
                Arguments.of(PersistentDataType.LIST.strings(), List.of("a", "b", "c"), objectAssertion),
                Arguments.of(PersistentDataType.LIST.byteArrays(), List.of(new byte[]{1, 2, 3}, new byte[]{4, 5, 6}), byteArrayAssertion),
                Arguments.of(PersistentDataType.LIST.integerArrays(), List.of(new int[]{1, 2, 3}, new int[]{4, 5, 6}), intArrayAssertion),
                Arguments.of(PersistentDataType.LIST.longArrays(), List.of(new long[]{1, 2, 3}, new long[]{4, 5, 6}), longArrayAssertion),
                Arguments.of(PersistentDataType.LIST.dataContainers(), List.of(first, second), objectAssertion)
        );
    }

    private static Identifier requestKey(String keyName) {
        return new Identifier("test-plugin", keyName.toLowerCase(Locale.ROOT));
    }

    private static ItemStack createNewItemStack() {
        return ItemTypes.DIAMOND.createItemStack(1);
    }

    private ItemStack createComplexItemStack() {
        var itemStack = createNewItemStack();
        itemStack.setCustomName("Item Display Name");

        //Add random primitive values
        itemStack.getPersistentDataContainer().set(requestKey("custom-long"), PersistentDataType.LONG, 4L);
        itemStack.getPersistentDataContainer().set(requestKey("custom-byte-array"), PersistentDataType.BYTE_ARRAY, new byte[]{
                0, 1, 2, 10
        });
        itemStack.getPersistentDataContainer().set(requestKey("custom-string"), PersistentDataType.STRING, "Hello there world");
        itemStack.getPersistentDataContainer().set(requestKey("custom-int"), PersistentDataType.INTEGER, 3);
        itemStack.getPersistentDataContainer().set(requestKey("custom-double"), PersistentDataType.DOUBLE, 3.123);
        itemStack.getPersistentDataContainer().set(
                requestKey("custom-list-string"), PersistentDataType.LIST.strings(), List.of("first[]", "second{}", "third()")
        );
        itemStack.getPersistentDataContainer().set(
                requestKey("custom-list-bytes"), PersistentDataType.LIST.bytes(), List.of((byte) 1, (byte) 2, (byte) 3)
        );

        var innerContainer = itemStack.getPersistentDataContainer().getAdapterContext().newPersistentDataContainer();
        innerContainer.set(VALID_KEY, PersistentDataType.LONG, 5L);
        itemStack.getPersistentDataContainer().set(requestKey("custom-inner-compound"), PersistentDataType.TAG_CONTAINER, innerContainer);
        return itemStack;
    }

    @Test
    public void testSetNoAdapter() {
        var itemStack = createNewItemStack();
        assertThrows(IllegalArgumentException.class, () -> itemStack.getPersistentDataContainer().set(VALID_KEY, new PrimitiveDataType<>(boolean.class), true));
    }

    @Test
    public void testHasNoAdapter() {
        var itemStack = createNewItemStack();
        itemStack.getPersistentDataContainer().set(PersistentDataContainerTest.VALID_KEY, PersistentDataType.INTEGER, 1);
        assertThrows(IllegalArgumentException.class, () -> itemStack.getPersistentDataContainer().has(VALID_KEY, new PrimitiveDataType<>(boolean.class)));
    }

    @Test
    public void testHasNoType() {
        var itemStack = createNewItemStack();
        itemStack.getPersistentDataContainer().set(PersistentDataContainerTest.VALID_KEY, PersistentDataType.INTEGER, 1);
        assertTrue(itemStack.getPersistentDataContainer().has(PersistentDataContainerTest.VALID_KEY));
    }

    @Test
    public void testGetNoAdapter() {
        var itemStack = createNewItemStack();
        itemStack.getPersistentDataContainer().set(PersistentDataContainerTest.VALID_KEY, PersistentDataType.INTEGER, 1);
        assertThrows(IllegalArgumentException.class, () -> itemStack.getPersistentDataContainer().get(VALID_KEY, new PrimitiveDataType<>(boolean.class)));
    }

    @Test
    public void testGetWrongType() {
        var itemStack = createNewItemStack();
        itemStack.getPersistentDataContainer().set(PersistentDataContainerTest.VALID_KEY, PersistentDataType.INTEGER, 1);
        assertThrows(IllegalArgumentException.class, () -> itemStack.getPersistentDataContainer().get(VALID_KEY, PersistentDataType.STRING));
    }

    @Test
    public void testDifferentNamespace() {
        var identifierA = new Identifier("plugin-a", "damage");
        var identifierB = new Identifier("plugin-b", "damage");

        var itemStack = createNewItemStack();
        itemStack.getPersistentDataContainer().set(identifierA, PersistentDataType.LONG, 15L);
        itemStack.getPersistentDataContainer().set(identifierB, PersistentDataType.LONG, 160L);

        assertEquals(15L, itemStack.getPersistentDataContainer().get(identifierA, PersistentDataType.LONG));
        assertEquals(160L, itemStack.getPersistentDataContainer().get(identifierB, PersistentDataType.LONG));
    }

    @Test
    public void testNBTTagStoring() {
        var itemStack = createComplexItemStack();
        assertEquals(
                itemStack.saveExtraTag(),
                NbtMap.builder()
                        .putCompound("display", NbtMap.builder()
                                .putString("Name", "Item Display Name")
                                .build()
                        )
                        .putCompound("PDC", NbtMap.builder()
                                .putByteArray("test-plugin:custom-byte-array", new byte[]{0, 1, 2, 10})
                                .putCompound("test-plugin:custom-inner-compound", NbtMap.builder()
                                        .putLong("test:validkey", 5L)
                                        .build()
                                )
                                .putInt("test-plugin:custom-int", 3)
                                .putList("test-plugin:custom-list-string", NbtType.STRING, List.of("first[]", "second{}", "third()"))
                                .putLong("test-plugin:custom-long", 4L)
                                .putList("test-plugin:custom-list-bytes", NbtType.BYTE, List.of((byte) 1, (byte) 2, (byte) 3))
                                .putString("test-plugin:custom-string", "Hello there world")
                                .putDouble("test-plugin:custom-double", 3.123d)
                                .build()
                        )
                        .build()
        );
    }

    @Test
    public void testCorrectType() {
        var complexItemStack = createComplexItemStack();
        complexItemStack.getPersistentDataContainer().set(PersistentDataContainerTest.requestKey("int"), PersistentDataType.STRING, "1");
        complexItemStack.getPersistentDataContainer().set(PersistentDataContainerTest.requestKey("double"), PersistentDataType.STRING, "1.33");

        var newItemStack = createNewItemStack();
        newItemStack.loadExtraTag(complexItemStack.saveExtraTag());

        assertEquals(complexItemStack.saveExtraTag(), newItemStack.saveExtraTag());
    }

    @Test
    public void testStringEdgeCases() {
        var itemStack = createNewItemStack();

        var arrayLookalike = "[\"UnicornParticle\",\"TotemParticle\",\"AngelParticle\",\"ColorSwitchParticle\"]";
        var jsonLookalike = """
                {
                 "key": 'A value wrapped in single quotes',
                 "other": "A value with normal quotes",
                 "array": ["working", "unit", "tests"]
                }
                """;

        var pdc = itemStack.getPersistentDataContainer();
        pdc.set(PersistentDataContainerTest.requestKey("string_int"), PersistentDataType.STRING, "5i");
        pdc.set(PersistentDataContainerTest.requestKey("string_true"), PersistentDataType.STRING, "true");
        pdc.set(PersistentDataContainerTest.requestKey("string_byte_array"), PersistentDataType.STRING, "[B;-128B]");
        pdc.set(PersistentDataContainerTest.requestKey("string_array_lookalike"), PersistentDataType.STRING, arrayLookalike);
        pdc.set(PersistentDataContainerTest.requestKey("string_json_lookalike"), PersistentDataType.STRING, jsonLookalike);


        var newItemStack = createNewItemStack();
        newItemStack.loadExtraTag(itemStack.saveExtraTag());

        var loadedPdc = newItemStack.getPersistentDataContainer();
        assertEquals("5i", loadedPdc.get(PersistentDataContainerTest.requestKey("string_int"), PersistentDataType.STRING));
        assertEquals("true", loadedPdc.get(PersistentDataContainerTest.requestKey("string_true"), PersistentDataType.STRING));
        assertEquals(arrayLookalike, loadedPdc.get(PersistentDataContainerTest.requestKey("string_array_lookalike"), PersistentDataType.STRING));
        assertEquals(jsonLookalike, loadedPdc.get(PersistentDataContainerTest.requestKey("string_json_lookalike"), PersistentDataType.STRING));
    }

    @Test
    public void storeUUIDOnItemTest() {
        var itemStack = createNewItemStack();
        var uuidDataType = new UUIDDataType();

        var randomUUID = UUID.randomUUID();
        itemStack.getPersistentDataContainer().set(PersistentDataContainerTest.VALID_KEY, uuidDataType, randomUUID);
        assertTrue(itemStack.getPersistentDataContainer().has(PersistentDataContainerTest.VALID_KEY, uuidDataType));
        assertEquals(randomUUID, itemStack.getPersistentDataContainer().get(PersistentDataContainerTest.VALID_KEY, uuidDataType));
    }

    @Test
    public void encapsulatedContainers() {
        var innerKey = new Identifier("plugin-a", "inner");

        var itemStack = createNewItemStack();
        var context = itemStack.getPersistentDataContainer().getAdapterContext();

        var thirdContainer = context.newPersistentDataContainer();
        thirdContainer.set(PersistentDataContainerTest.VALID_KEY, PersistentDataType.LONG, 3L);

        var secondContainer = context.newPersistentDataContainer();
        secondContainer.set(PersistentDataContainerTest.VALID_KEY, PersistentDataType.LONG, 2L);
        secondContainer.set(innerKey, PersistentDataType.TAG_CONTAINER, thirdContainer);

        itemStack.getPersistentDataContainer().set(PersistentDataContainerTest.VALID_KEY, PersistentDataType.LONG, 1L);
        itemStack.getPersistentDataContainer().set(innerKey, PersistentDataType.TAG_CONTAINER, secondContainer);

        assertEquals(3L, itemStack.getPersistentDataContainer()
                .get(innerKey, PersistentDataType.TAG_CONTAINER)
                .get(innerKey, PersistentDataType.TAG_CONTAINER)
                .get(PersistentDataContainerTest.VALID_KEY, PersistentDataType.LONG));

        assertEquals(2L, itemStack.getPersistentDataContainer()
                .get(innerKey, PersistentDataType.TAG_CONTAINER)
                .get(PersistentDataContainerTest.VALID_KEY, PersistentDataType.LONG));

        assertEquals(1L, itemStack.getPersistentDataContainer()
                .get(PersistentDataContainerTest.VALID_KEY, PersistentDataType.LONG));
    }

    @Test
    public void testPrimitiveCustomTags() {
        var itemStack = createNewItemStack();
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.BYTE, (byte) 1);
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.SHORT, (short) 1);
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.INTEGER, 1);
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.LONG, 1L);
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.FLOAT, 1.34F);
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.DOUBLE, 151.123);
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.STRING, "test");

        this.testPrimitiveCustomTag(itemStack, PersistentDataType.BYTE_ARRAY, new byte[]{
                1, 4, 2, Byte.MAX_VALUE
        });
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.INTEGER_ARRAY, new int[]{
                1, 4, 2, Integer.MAX_VALUE
        });
        this.testPrimitiveCustomTag(itemStack, PersistentDataType.LONG_ARRAY, new long[]{
                1L, 4L, 2L, Long.MAX_VALUE
        });
    }

    private <T, Z> void testPrimitiveCustomTag(ItemStack itemStack, PersistentDataType<T, Z> type, Z value) {
        var tagKey = new Identifier("test", String.valueOf(type.hashCode()));

        itemStack.getPersistentDataContainer().set(tagKey, type, value);
        assertTrue(itemStack.getPersistentDataContainer().has(tagKey, type));

        var foundValue = itemStack.getPersistentDataContainer().get(tagKey, type);
        if (foundValue.getClass().isArray()) { // Compare arrays using reflection access
            var length = Array.getLength(foundValue);
            var originalLength = Array.getLength(value);
            for (int i = 0; i < length && i < originalLength; i++) {
                assertEquals(Array.get(value, i), Array.get(foundValue, i));
            }
        } else {
            assertEquals(foundValue, value);
        }

        itemStack.getPersistentDataContainer().remove(tagKey);
        assertFalse(itemStack.getPersistentDataContainer().has(tagKey, type));
    }

    @ParameterizedTest
    @MethodSource("testListTypeArgumentSource")
    public <T> void testListType(@NotNull final ListPersistentDataType<T, T> type, @NotNull final List<T> list, @NotNull final BiConsumer<T, T> equalsCheck) {
        var itemStack = createNewItemStack();

        var container = itemStack.getPersistentDataContainer();
        container.set(requestKey("list"), type, list);

        var returnedList = container.get(requestKey("list"), type);

        assertNotNull(returnedList);
        assertEquals(list.size(), returnedList.size());

        for (int i = 0; i < list.size(); i++) {
            var expectedValue = list.get(i);
            var foundValue = returnedList.get(i);
            equalsCheck.accept(expectedValue, foundValue);
        }
    }

    @Test
    public void testEmptyListApplicationToAnyType() {
        var itemStack = createNewItemStack();

        var container = itemStack.getPersistentDataContainer();
        container.set(requestKey("list"), PersistentDataType.LIST.strings(), List.of());

        assertTrue(container.has(requestKey("list"), PersistentDataType.LIST.strings()));
        assertTrue(container.has(requestKey("list"), PersistentDataType.LIST.bytes()));
        assertFalse(container.has(requestKey("list"), PersistentDataType.STRING));
        assertEquals(List.of(), container.get(requestKey("list"), PersistentDataType.LIST.strings()));

        var newItemStack = createNewItemStack();
        newItemStack.loadExtraTag(itemStack.saveExtraTag());

        var readContainer = newItemStack.getPersistentDataContainer();
        assertTrue(readContainer.has(requestKey("list"), PersistentDataType.LIST.strings()));
        assertTrue(readContainer.has(requestKey("list"), PersistentDataType.LIST.bytes()));
        assertFalse(readContainer.has(requestKey("list"), PersistentDataType.STRING));
        assertEquals(List.of(), readContainer.get(requestKey("list"), PersistentDataType.LIST.strings()));
    }
}
