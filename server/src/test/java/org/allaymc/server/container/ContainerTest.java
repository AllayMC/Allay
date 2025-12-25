package org.allaymc.server.container;

import org.allaymc.api.container.*;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.container.impl.BaseContainer;
import org.allaymc.server.container.impl.FakeContainerImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_STACK;
import static org.allaymc.api.item.type.ItemTypes.DIAMOND;
import static org.allaymc.api.item.type.ItemTypes.STONE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class ContainerTest {

    static final ContainerType<Container> TEST_CONTAINER_TYPE = new ContainerType<>(36);

    BaseContainer container;
    ContainerViewer viewer;

    @BeforeEach
    void setUp() {
        container = new BaseContainer(TEST_CONTAINER_TYPE);
        viewer = new FakeContainerViewer();
    }

    @Nested
    class BasicTests {

        @Test
        void testEmptySlotPlaceHolder() {
            assertEquals(0, AIR_STACK.getCount());
            assertEquals(0, AIR_STACK.getUniqueId());
        }

        @Test
        void testContainerEmptySlot() {
            for (var item : container.getItemStacks()) {
                assertEquals(AIR_STACK, item);
            }
        }

        @Test
        void testContainerType() {
            assertEquals(TEST_CONTAINER_TYPE, container.getContainerType());
            assertEquals(36, container.getContainerType().getSize());
        }

        @Test
        void testContainerTypeNotEqual() {
            var type1 = new ContainerType<>(0);
            var type2 = new ContainerType<>(0);
            assertNotEquals(type1, type2);
        }

        @Test
        void testSetItemStackNull() {
            assertThrows(NullPointerException.class, () ->
                    container.setItemStack(0, null)
            );
        }
    }

    @Nested
    class SlotOperationTests {

        @Test
        void testIsEmptySlot() {
            assertTrue(container.isEmpty(0));

            container.setItemStack(0, STONE.createItemStack());
            assertFalse(container.isEmpty(0));
        }

        @Test
        void testIsEmptyContainer() {
            assertTrue(container.isEmpty());

            container.setItemStack(0, STONE.createItemStack());
            assertFalse(container.isEmpty());
        }

        @Test
        void testSetAndGetItemStack() {
            var item = STONE.createItemStack(32);
            container.setItemStack(5, item);

            assertEquals(item, container.getItemStack(5));
            assertEquals(32, container.getItemStack(5).getCount());
        }

        @Test
        void testClearSlot() {
            container.setItemStack(0, STONE.createItemStack());
            assertFalse(container.isEmpty(0));

            container.clearSlot(0);
            assertTrue(container.isEmpty(0));
            assertEquals(AIR_STACK, container.getItemStack(0));
        }

        @Test
        void testClearAllSlots() {
            container.setItemStack(0, STONE.createItemStack());
            container.setItemStack(10, DIAMOND.createItemStack());
            container.setItemStack(35, STONE.createItemStack());

            assertFalse(container.isEmpty());

            container.clearAllSlots();

            assertTrue(container.isEmpty());
            for (int i = 0; i < 36; i++) {
                assertEquals(AIR_STACK, container.getItemStack(i));
            }
        }

        @Test
        void testGetItemStackArray() {
            var array = container.getItemStackArray();
            assertEquals(36, array.length);

            container.setItemStack(0, STONE.createItemStack());
            assertSame(container.getItemStack(0), array[0]);
        }

        @Test
        void testGetItemStacks() {
            var list = container.getItemStacks();
            assertEquals(36, list.size());

            assertThrows(UnsupportedOperationException.class, () ->
                    list.add(STONE.createItemStack())
            );
        }
    }

    @Nested
    class ViewerTests {

        @Test
        void testAddViewer() {
            assertTrue(container.addViewer(viewer));
            assertEquals(1, container.getViewers().size());
            assertTrue(container.getViewers().containsValue(viewer));
        }

        @Test
        void testRemoveViewer() {
            container.addViewer(viewer);
            assertTrue(container.removeViewer(viewer));
            assertEquals(0, container.getViewers().size());
        }

        @Test
        void testRemoveNonExistentViewer() {
            assertFalse(container.removeViewer(viewer));
        }

        @Test
        void testRemoveAllViewers() {
            var viewer2 = new FakeContainerViewer() {
                @Override
                public byte viewOpen(Container container) {
                    return 1;
                }
            };

            container.addViewer(viewer);
            container.addViewer(viewer2);
            assertEquals(2, container.getViewers().size());

            container.removeAllViewers();
            assertEquals(0, container.getViewers().size());
        }

        @Test
        void testGetViewersUnmodifiable() {
            container.addViewer(viewer);
            var viewers = container.getViewers();

            assertThrows(UnsupportedOperationException.class, () ->
                    viewers.put((byte) 99, new FakeContainerViewer())
            );
        }
    }

    @Nested
    class ListenerTests {

        @Test
        void testOpenListener() {
            AtomicBoolean opened = new AtomicBoolean(false);
            container.addOpenListener(v -> opened.set(true));

            container.addViewer(viewer);
            assertTrue(opened.get());
        }

        @Test
        void testCloseListener() {
            AtomicBoolean closed = new AtomicBoolean(false);
            container.addCloseListener(v -> closed.set(true));

            container.addViewer(viewer);
            container.removeViewer(viewer);
            assertTrue(closed.get());
        }

        @Test
        void testRemoveOpenListener() {
            AtomicInteger count = new AtomicInteger(0);
            Consumer<ContainerViewer> listener = v -> count.incrementAndGet();

            container.addOpenListener(listener);
            container.addViewer(viewer);
            assertEquals(1, count.get());

            container.removeViewer(viewer);
            container.removeOpenListener(listener);

            container.addViewer(viewer);
            assertEquals(1, count.get());
        }

        @Test
        void testRemoveCloseListener() {
            AtomicInteger count = new AtomicInteger(0);
            Consumer<ContainerViewer> listener = v -> count.incrementAndGet();

            container.addCloseListener(listener);
            container.addViewer(viewer);
            container.removeViewer(viewer);
            assertEquals(1, count.get());

            container.removeCloseListener(listener);
            container.addViewer(viewer);
            container.removeViewer(viewer);
            assertEquals(1, count.get());
        }

        @Test
        void testSlotChangeListener() {
            AtomicReference<ItemStack> changedItem = new AtomicReference<>();
            container.addSlotChangeListener(0, changedItem::set);

            var item = STONE.createItemStack();
            container.setItemStack(0, item);

            assertSame(item, changedItem.get());
        }

        @Test
        void testSlotChangeListenerOnlyForSpecificSlot() {
            AtomicBoolean slot0Changed = new AtomicBoolean(false);
            AtomicBoolean slot1Changed = new AtomicBoolean(false);

            container.addSlotChangeListener(0, item -> slot0Changed.set(true));
            container.addSlotChangeListener(1, item -> slot1Changed.set(true));

            container.setItemStack(0, STONE.createItemStack());

            assertTrue(slot0Changed.get());
            assertFalse(slot1Changed.get());
        }

        @Test
        void testRemoveSlotChangeListener() {
            AtomicInteger count = new AtomicInteger(0);
            Consumer<ItemStack> listener = item -> count.incrementAndGet();

            container.addSlotChangeListener(0, listener);
            container.setItemStack(0, STONE.createItemStack());
            assertEquals(1, count.get());

            container.removeSlotChangeListener(0, listener);
            container.setItemStack(0, DIAMOND.createItemStack());
            assertEquals(1, count.get());
        }

        @Test
        void testMultipleSlotChangeListeners() {
            AtomicInteger count = new AtomicInteger(0);

            container.addSlotChangeListener(0, item -> count.incrementAndGet());
            container.addSlotChangeListener(0, item -> count.incrementAndGet());

            container.setItemStack(0, STONE.createItemStack());
            assertEquals(2, count.get());
        }
    }

    @Nested
    class TryAddItemTests {

        @Test
        void testTryAddItemToEmptyContainer() {
            var item = STONE.createItemStack(32);
            int slot = container.tryAddItem(item);

            assertEquals(0, slot);
            assertEquals(32, container.getItemStack(0).getCount());
            assertEquals(0, item.getCount());
        }

        @Test
        void testTryAddItemMerge() {
            container.setItemStack(0, STONE.createItemStack(32));

            var item = STONE.createItemStack(16);
            int slot = container.tryAddItem(item);

            assertEquals(0, slot);
            assertEquals(48, container.getItemStack(0).getCount());
            assertEquals(0, item.getCount());
        }

        @Test
        void testTryAddItemToNextEmptySlot() {
            container.setItemStack(0, DIAMOND.createItemStack());

            var item = STONE.createItemStack(10);
            int slot = container.tryAddItem(item);

            assertEquals(1, slot);
            assertEquals(10, container.getItemStack(1).getCount());
        }

        @Test
        void testTryAddItemWithSlotRange() {
            var item = STONE.createItemStack(10);
            int slot = container.tryAddItem(item, 5, 10);

            assertEquals(5, slot);
            assertEquals(10, container.getItemStack(5).getCount());
        }

        @Test
        void testTryAddItemInvalidRange() {
            var item = STONE.createItemStack();

            assertThrows(IllegalArgumentException.class, () ->
                    container.tryAddItem(item, 10, 5)
            );
            assertThrows(IllegalArgumentException.class, () ->
                    container.tryAddItem(item, -1, 5)
            );
            assertThrows(IllegalArgumentException.class, () ->
                    container.tryAddItem(item, 0, 100)
            );
        }

        @Test
        void testTryAddItemToFullContainer() {
            for (int i = 0; i < 36; i++) {
                container.setItemStack(i, DIAMOND.createItemStack(64));
            }

            var item = STONE.createItemStack(10);
            int slot = container.tryAddItem(item);

            assertEquals(-1, slot);
            assertEquals(10, item.getCount());
        }
    }

    @Nested
    class NBTTests {

        @Test
        void testSaveEmptyContainerNBT() {
            var nbt = container.saveNBT();
            assertTrue(nbt.isEmpty());
        }

        @Test
        void testSaveAndLoadNBT() {
            container.setItemStack(0, STONE.createItemStack(32));
            container.setItemStack(10, DIAMOND.createItemStack(16));

            var nbt = container.saveNBT();
            assertEquals(2, nbt.size());

            var newContainer = new BaseContainer(TEST_CONTAINER_TYPE);
            newContainer.loadNBT(nbt);

            assertEquals(STONE.getIdentifier(), newContainer.getItemStack(0).getItemType().getIdentifier());
            assertEquals(32, newContainer.getItemStack(0).getCount());
            assertEquals(DIAMOND.getIdentifier(), newContainer.getItemStack(10).getItemType().getIdentifier());
            assertEquals(16, newContainer.getItemStack(10).getCount());
        }
    }

    @Nested
    class NotifyTests {

        @Test
        void testNotifySlotChangeWithSend() {
            AtomicInteger viewCount = new AtomicInteger(0);
            var trackingViewer = new FakeContainerViewer() {
                @Override
                public void viewSlot(Container container, int slot) {
                    viewCount.incrementAndGet();
                }
            };

            container.addViewer(trackingViewer);
            container.setItemStack(0, STONE.createItemStack(), true);

            assertEquals(1, viewCount.get());
        }

        @Test
        void testNotifySlotChangeWithoutSend() {
            AtomicInteger viewCount = new AtomicInteger(0);
            var trackingViewer = new FakeContainerViewer() {
                @Override
                public void viewSlot(Container container, int slot) {
                    viewCount.incrementAndGet();
                }
            };

            container.addViewer(trackingViewer);
            container.setItemStack(0, STONE.createItemStack(), false);

            assertEquals(0, viewCount.get());
        }
    }

    @Nested
    class FakeContainerTests {

        static Stream<Named<Supplier<FakeContainer>>> fakeContainerProvider() {
            return Stream.of(
                    Named.of("FakeChest", () -> FakeContainerFactory.getFactory().createFakeChestContainer()),
                    Named.of("FakeDoubleChest", () -> FakeContainerFactory.getFactory().createFakeDoubleChestContainer())
            );
        }

        @Test
        void testFakeContainerFactory() {
            var factory = FakeContainerFactory.getFactory();
            assertNotNull(factory);
            assertNotNull(factory.createFakeChestContainer());
            assertNotNull(factory.createFakeDoubleChestContainer());
        }

        @Test
        void testFakeChestContainerType() {
            var fakeChest = FakeContainerFactory.getFactory().createFakeChestContainer();
            assertEquals(ContainerTypes.FAKE_CHEST, fakeChest.getContainerType());
            assertEquals(27, fakeChest.getContainerType().getSize());
        }

        @Test
        void testFakeDoubleChestContainerType() {
            var fakeDoubleChest = FakeContainerFactory.getFactory().createFakeDoubleChestContainer();
            assertEquals(ContainerTypes.FAKE_DOUBLE_CHEST, fakeDoubleChest.getContainerType());
            assertEquals(54, fakeDoubleChest.getContainerType().getSize());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testCustomName(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();

            assertNull(fakeContainer.getCustomName());

            fakeContainer.setCustomName("Test Container");
            assertEquals("Test Container", fakeContainer.getCustomName());

            fakeContainer.setCustomName(null);
            assertNull(fakeContainer.getCustomName());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testAddClickListener(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            AtomicInteger clickCount = new AtomicInteger(0);
            Runnable listener = clickCount::incrementAndGet;

            fakeContainer.addClickListener(0, listener);
            ((FakeContainerImpl) fakeContainer).onClick(0);

            assertEquals(1, clickCount.get());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testMultipleClickListeners(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            AtomicInteger count = new AtomicInteger(0);

            fakeContainer.addClickListener(0, count::incrementAndGet);
            fakeContainer.addClickListener(0, count::incrementAndGet);
            ((FakeContainerImpl) fakeContainer).onClick(0);

            assertEquals(2, count.get());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testClickListenerOnDifferentSlots(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            AtomicBoolean slot0Clicked = new AtomicBoolean(false);
            AtomicBoolean slot1Clicked = new AtomicBoolean(false);

            fakeContainer.addClickListener(0, () -> slot0Clicked.set(true));
            fakeContainer.addClickListener(1, () -> slot1Clicked.set(true));

            ((FakeContainerImpl) fakeContainer).onClick(0);

            assertTrue(slot0Clicked.get());
            assertFalse(slot1Clicked.get());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testRemoveClickListener(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            AtomicInteger count = new AtomicInteger(0);
            Runnable listener = count::incrementAndGet;

            fakeContainer.addClickListener(0, listener);
            ((FakeContainerImpl) fakeContainer).onClick(0);
            assertEquals(1, count.get());

            fakeContainer.removeClickListener(0, listener);
            ((FakeContainerImpl) fakeContainer).onClick(0);
            assertEquals(1, count.get());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testRemoveAllClickListeners(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            AtomicInteger count = new AtomicInteger(0);

            fakeContainer.addClickListener(0, count::incrementAndGet);
            fakeContainer.addClickListener(1, count::incrementAndGet);
            fakeContainer.addClickListener(2, count::incrementAndGet);

            ((FakeContainerImpl) fakeContainer).onClick(0);
            ((FakeContainerImpl) fakeContainer).onClick(1);
            ((FakeContainerImpl) fakeContainer).onClick(2);
            assertEquals(3, count.get());

            fakeContainer.removeAllClickListeners();

            ((FakeContainerImpl) fakeContainer).onClick(0);
            ((FakeContainerImpl) fakeContainer).onClick(1);
            ((FakeContainerImpl) fakeContainer).onClick(2);
            assertEquals(3, count.get());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testOnClickWithNoListener(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            assertDoesNotThrow(() -> ((FakeContainerImpl) fakeContainer).onClick(0));
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testSetItemStackWithListener(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            AtomicBoolean clicked = new AtomicBoolean(false);
            var item = STONE.createItemStack(10);

            fakeContainer.setItemStackWithListener(5, item, () -> clicked.set(true));

            assertEquals(item, fakeContainer.getItemStack(5));

            ((FakeContainerImpl) fakeContainer).onClick(5);
            assertTrue(clicked.get());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testAddViewerThrowsException(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            assertThrows(UnsupportedOperationException.class, () ->
                    fakeContainer.addViewer(viewer)
            );
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testFakeContainerIsContainer(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();

            assertTrue(fakeContainer.isEmpty());

            fakeContainer.setItemStack(0, STONE.createItemStack());
            assertFalse(fakeContainer.isEmpty());

            fakeContainer.clearSlot(0);
            assertTrue(fakeContainer.isEmpty(0));
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testSlotCount(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            var expectedSize = fakeContainer.getContainerType().getSize();
            assertEquals(expectedSize, fakeContainer.getItemStackArray().length);
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testTryAddItem(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            var item = STONE.createItemStack(32);
            int slot = fakeContainer.tryAddItem(item);

            assertEquals(0, slot);
            assertEquals(32, fakeContainer.getItemStack(0).getCount());
            assertEquals(0, item.getCount());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testClearAllSlots(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();

            fakeContainer.setItemStack(0, STONE.createItemStack());
            int lastSlot = fakeContainer.getContainerType().getSize() - 1;
            fakeContainer.setItemStack(lastSlot, DIAMOND.createItemStack());

            assertFalse(fakeContainer.isEmpty());

            fakeContainer.clearAllSlots();
            assertTrue(fakeContainer.isEmpty());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testClickListenerOnLastSlot(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            AtomicBoolean clicked = new AtomicBoolean(false);
            int lastSlot = fakeContainer.getContainerType().getSize() - 1;

            fakeContainer.addClickListener(lastSlot, () -> clicked.set(true));
            ((FakeContainerImpl) fakeContainer).onClick(lastSlot);

            assertTrue(clicked.get());
        }

        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testFactoryCreatesNewInstances(Supplier<FakeContainer> containerSupplier) {
            var container1 = containerSupplier.get();
            var container2 = containerSupplier.get();
            assertNotSame(container1, container2);
        }
    }
}
