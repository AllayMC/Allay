package org.allaymc.server.container;

import org.allaymc.api.container.*;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.container.impl.BaseContainer;
import org.allaymc.server.container.impl.DoubleChestContainerImpl;
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
 * {@code ContainerTest} verifies container storage, viewer membership, listener
 * delivery, persistence, fake interfaces, and combined chest delegation. Each ordinary
 * case starts with fresh empty storage and a passive viewer, while nested fixtures
 * configure the specialized containers needed for their assertions.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class ContainerTest {

    /**
     * {@code TEST_CONTAINER_TYPE} provides a shared thirty-six-slot identity for
     * ordinary container fixtures, allowing tests to distinguish layout size from type
     * reference equality.
     */
    static final ContainerType<Container> TEST_CONTAINER_TYPE = new ContainerType<>(36);

    /**
     * Fresh ordinary container under test.
     */
    BaseContainer container;
    /**
     * Passive viewer used by each fixture.
     */
    ContainerViewer viewer;

    /**
     * Creates fresh ordinary container storage and a passive viewer before each test.
     */
    @BeforeEach
    void setUp() {
        container = new BaseContainer(TEST_CONTAINER_TYPE);
        viewer = new FakeContainerViewer();
    }

    /**
     * {@code BasicTests} groups regression checks for air placeholders, container
     * identities, and rejected assignments.
     */
    @Nested
    class BasicTests {

        /**
         * Examines the shared air placeholder used by empty container slots. Its count
         * and network identity must both remain zero so empty storage has the expected
         * sentinel values.
         */
        @Test
        void testEmptySlotPlaceHolder() {
            assertEquals(0, AIR_STACK.getCount());
            assertEquals(0, AIR_STACK.getUniqueId());
        }

        /**
         * Inspects every slot immediately after base-container construction. Each entry
         * must expose the shared air placeholder so fresh storage is consistently
         * recognized as empty by container operations.
         */
        @Test
        void testContainerEmptySlot() {
            for (var item : container.getItemStacks()) {
                assertEquals(AIR_STACK, item);
            }
        }

        /**
         * Reads the identity and slot count of the newly constructed fixture. The
         * container must retain the supplied type token and allocate its expected
         * thirty-six-slot layout.
         */
        @Test
        void testContainerType() {
            assertEquals(TEST_CONTAINER_TYPE, container.getContainerType());
            assertEquals(36, container.getContainerType().getSize());
        }

        /**
         * Constructs two type tokens with identical zero-slot sizes. They must compare
         * as distinct identities so holder keys cannot accidentally merge separately
         * defined container types by size.
         */
        @Test
        void testContainerTypeNotEqual() {
            var type1 = new ContainerType<>(0);
            var type2 = new ContainerType<>(0);
            assertNotEquals(type1, type2);
        }

        /**
         * Attempts to assign a {@code null} stack to a valid empty slot. The setter must
         * reject the value with a {@code null}-pointer exception instead of installing
         * an invalid storage entry.
         */
        @Test
        void testSetItemStackNull() {
            assertThrows(NullPointerException.class, () ->
                    container.setItemStack(0, null)
            );
        }
    }

    /**
     * {@code SlotOperationTests} groups regression checks for ordinary slot access,
     * clearing, and shared storage views.
     */
    @Nested
    class SlotOperationTests {

        /**
         * Checks a fresh slot and then assigns a stone stack. The empty-slot predicate
         * must change from {@code true} to {@code false} when ordinary item storage
         * replaces the air sentinel.
         */
        @Test
        void testIsEmptySlot() {
            assertTrue(container.isEmpty(0));

            container.setItemStack(0, STONE.createItemStack());
            assertFalse(container.isEmpty(0));
        }

        /**
         * Checks fresh storage and then fills one slot with stone. Whole-container
         * emptiness must reflect that single occupied slot without requiring all slots
         * to become populated.
         */
        @Test
        void testIsEmptyContainer() {
            assertTrue(container.isEmpty());

            container.setItemStack(0, STONE.createItemStack());
            assertFalse(container.isEmpty());
        }

        /**
         * Assigns a counted stone stack at a nonzero slot. Lookup must return that item
         * and its preserved count so assignment and retrieval address the same storage
         * entry.
         */
        @Test
        void testSetAndGetItemStack() {
            var item = STONE.createItemStack(32);
            container.setItemStack(5, item);

            assertEquals(item, container.getItemStack(5));
            assertEquals(32, container.getItemStack(5).getCount());
        }

        /**
         * Fills one slot before clearing it through the convenience operation. The slot
         * must become empty and expose the shared air sentinel after the clear
         * completes.
         */
        @Test
        void testClearSlot() {
            container.setItemStack(0, STONE.createItemStack());
            assertFalse(container.isEmpty(0));

            container.clearSlot(0);
            assertTrue(container.isEmpty(0));
            assertEquals(AIR_STACK, container.getItemStack(0));
        }

        /**
         * Populates separated slots including the final inventory entry. Clearing all
         * slots must restore the air sentinel everywhere rather than leave gaps between
         * the occupied test positions.
         */
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

        /**
         * Retains the backing array before replacing a container slot. The array must
         * expose the later assigned stack reference, protecting its live relationship
         * with ordinary slot storage.
         */
        @Test
        void testGetItemStackArray() {
            var array = container.getItemStackArray();
            assertEquals(36, array.length);

            container.setItemStack(0, STONE.createItemStack());
            assertSame(container.getItemStack(0), array[0]);
        }

        /**
         * Obtains the list view of fresh container contents. The list must expose every
         * slot and reject structural additions so callers cannot resize the underlying
         * container through it.
         */
        @Test
        void testGetItemStacks() {
            var list = container.getItemStacks();
            assertEquals(36, list.size());

            assertThrows(UnsupportedOperationException.class, () ->
                    list.add(STONE.createItemStack())
            );
        }
    }

    /**
     * {@code ViewerTests} groups regression checks for container viewer membership and
     * its unmodifiable exposed map.
     */
    @Nested
    class ViewerTests {

        /**
         * Opens the fixture container for its passive viewer. The operation must succeed
         * and record exactly one membership containing that viewer in the assigned-ID
         * map.
         */
        @Test
        void testAddViewer() {
            assertTrue(container.addViewer(viewer));
            assertEquals(1, container.getViewers().size());
            assertTrue(container.getViewers().containsValue(viewer));
        }

        /**
         * Registers a viewer before requesting its removal. Removal must report success
         * and leave no viewer membership so the container can complete an ordinary
         * open-and-close lifetime.
         */
        @Test
        void testRemoveViewer() {
            container.addViewer(viewer);
            assertTrue(container.removeViewer(viewer));
            assertEquals(0, container.getViewers().size());
        }

        /**
         * Requests removal of a viewer that has never joined the container. The
         * operation must return {@code false} so callers can distinguish absent
         * membership from a completed close.
         */
        @Test
        void testRemoveNonExistentViewer() {
            assertFalse(container.removeViewer(viewer));
        }

        /**
         * Registers two viewers whose opening callbacks return distinct window IDs. Bulk
         * removal must close both memberships without losing one while the live viewer
         * map changes during iteration.
         */
        @Test
        void testRemoveAllViewers() {
            var viewer2 = new FakeContainerViewer() {
                @Override
                public byte viewContainerOpen(Container container) {
                    return 1;
                }
            };

            container.addViewer(viewer);
            container.addViewer(viewer2);
            assertEquals(2, container.getViewers().size());

            container.removeAllViewers();
            assertEquals(0, container.getViewers().size());
        }

        /**
         * Obtains the viewer map after opening the container. Direct insertion through
         * that view must fail so membership changes continue to use the container
         * opening and removal paths.
         */
        @Test
        void testGetViewersUnmodifiable() {
            container.addViewer(viewer);
            var viewers = container.getViewers();

            assertThrows(UnsupportedOperationException.class, () ->
                    viewers.put((byte) 99, new FakeContainerViewer())
            );
        }
    }

    /**
     * {@code ListenerTests} groups regression checks for container lifecycle callbacks
     * and slot-specific listener registration.
     */
    @Nested
    class ListenerTests {

        /**
         * Registers an open listener before adding the fixture viewer. Opening must
         * invoke the callback so observers can react to successful container membership
         * creation.
         */
        @Test
        void testOpenListener() {
            AtomicBoolean opened = new AtomicBoolean(false);
            container.addOpenListener(v -> opened.set(true));

            container.addViewer(viewer);
            assertTrue(opened.get());
        }

        /**
         * Registers a close listener, then opens and removes a viewer. The callback must
         * run during removal so ordinary closure reaches registered lifecycle observers.
         */
        @Test
        void testCloseListener() {
            AtomicBoolean closed = new AtomicBoolean(false);
            container.addCloseListener(v -> closed.set(true));

            container.addViewer(viewer);
            container.removeViewer(viewer);
            assertTrue(closed.get());
        }

        /**
         * Opens a viewer once, removes the open listener, and opens again. The callback
         * count must remain unchanged after deregistration so later lifetimes cannot
         * notify that listener.
         */
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

        /**
         * Closes a viewer once, removes the close listener, and repeats the lifetime.
         * The callback count must remain unchanged after deregistration so later
         * closures no longer notify it.
         */
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

        /**
         * Registers a slot listener and assigns a concrete item stack. The callback must
         * receive that exact stored reference so listeners observe the value installed
         * by assignment.
         */
        @Test
        void testSlotChangeListener() {
            AtomicReference<ItemStack> changedItem = new AtomicReference<>();
            container.addSlotChangeListener(0, changedItem::set);

            var item = STONE.createItemStack();
            container.setItemStack(0, item);

            assertSame(item, changedItem.get());
        }

        /**
         * Registers separate callbacks for two slots before changing only slot zero.
         * Only its callback may run so one slot notification does not reach unrelated
         * registrations.
         */
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

        /**
         * Changes a slot, removes its listener, and assigns a different item. The
         * callback count must stop increasing after removal so subsequent slot
         * assignments cannot notify it.
         */
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

        /**
         * Registers two distinct callbacks on the same slot before assignment. Both must
         * run once so the slot listener set preserves multiple independent observers.
         */
        @Test
        void testMultipleSlotChangeListeners() {
            AtomicInteger count = new AtomicInteger(0);

            container.addSlotChangeListener(0, item -> count.incrementAndGet());
            container.addSlotChangeListener(0, item -> count.incrementAndGet());

            container.setItemStack(0, STONE.createItemStack());
            assertEquals(2, count.get());
        }
    }

    /**
     * {@code TryAddItemTests} groups regression checks for stack merging, insertion
     * ranges, and unavailable storage capacity.
     */
    @Nested
    class TryAddItemTests {

        /**
         * Inserts a counted stack into entirely empty storage. The first slot must
         * receive the full quantity and the caller stack must become empty after
         * successful insertion.
         */
        @Test
        void testTryAddItemToEmptyContainer() {
            var item = STONE.createItemStack(32);
            int slot = container.tryAddItem(item);

            assertEquals(0, slot);
            assertEquals(32, container.getItemStack(0).getCount());
            assertEquals(0, item.getCount());
        }

        /**
         * Inserts stone into a partially filled compatible stone slot. Insertion must
         * merge the counts in that slot and empty the supplied stack rather than consume
         * another empty slot.
         */
        @Test
        void testTryAddItemMerge() {
            container.setItemStack(0, STONE.createItemStack(32));

            var item = STONE.createItemStack(16);
            int slot = container.tryAddItem(item);

            assertEquals(0, slot);
            assertEquals(48, container.getItemStack(0).getCount());
            assertEquals(0, item.getCount());
        }

        /**
         * Places a different item in the first slot before inserting stone. Insertion
         * must select the next empty slot and preserve the requested count rather than
         * overwrite incompatible storage.
         */
        @Test
        void testTryAddItemToNextEmptySlot() {
            container.setItemStack(0, DIAMOND.createItemStack());

            var item = STONE.createItemStack(10);
            int slot = container.tryAddItem(item);

            assertEquals(1, slot);
            assertEquals(10, container.getItemStack(1).getCount());
        }

        /**
         * Restricts insertion to an inclusive subrange of otherwise empty storage. The
         * helper must choose that range's first slot instead of using an earlier empty
         * entry.
         */
        @Test
        void testTryAddItemWithSlotRange() {
            var item = STONE.createItemStack(10);
            int slot = container.tryAddItem(item, 5, 10);

            assertEquals(5, slot);
            assertEquals(10, container.getItemStack(5).getCount());
        }

        /**
         * Supplies reversed, negative, and out-of-bounds insertion ranges. Each request
         * must fail with an argument exception so invalid ranges do not enter the normal
         * insertion algorithm.
         */
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

        /**
         * Fills every slot with maximum-size diamonds before inserting stone. The helper
         * must return failure and preserve the uninserted stack count when no compatible
         * or empty capacity exists.
         */
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

    /**
     * {@code NBTTests} groups regression checks for item NBT omission and container
     * persistence round trips.
     */
    @Nested
    class NBTTests {

        /**
         * Serializes a fresh empty container using the default save operation. The
         * result must contain no entries so omitted air slots do not inflate ordinary
         * item persistence.
         */
        @Test
        void testSaveEmptyContainerNBT() {
            var nbt = container.saveNBT();
            assertTrue(nbt.isEmpty());
        }

        /**
         * Saves different counted items from separated slots and loads them into fresh
         * storage. Their types, counts, and original slot positions must survive the NBT
         * round trip.
         */
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

    /**
     * {@code NotifyTests} groups regression checks for the viewer update flag on
     * ordinary slot assignments.
     */
    @Nested
    class NotifyTests {

        /**
         * Assigns an item while ordinary viewer sending is enabled. A tracking viewer
         * must receive one slot callback so assignment publishes its changed contents
         * through the viewer path.
         */
        @Test
        void testNotifySlotChangeWithSend() {
            AtomicInteger viewCount = new AtomicInteger(0);
            var trackingViewer = new FakeContainerViewer() {
                @Override
                public void viewContainerSlot(Container container, int slot) {
                    viewCount.incrementAndGet();
                }
            };

            container.addViewer(trackingViewer);
            container.setItemStack(0, STONE.createItemStack(), true);

            assertEquals(1, viewCount.get());
        }

        /**
         * Assigns an item while ordinary viewer sending is suppressed. The tracking
         * viewer must receive no slot callback so the send flag controls the base
         * container update path.
         */
        @Test
        void testNotifySlotChangeWithoutSend() {
            AtomicInteger viewCount = new AtomicInteger(0);
            var trackingViewer = new FakeContainerViewer() {
                @Override
                public void viewContainerSlot(Container container, int slot) {
                    viewCount.incrementAndGet();
                }
            };

            container.addViewer(trackingViewer);
            container.setItemStack(0, STONE.createItemStack(), false);

            assertEquals(0, viewCount.get());
        }
    }

    /**
     * {@code FakeContainerTests} groups regression checks for synthetic chest layout,
     * naming, storage, and click registrations.
     */
    @Nested
    class FakeContainerTests {

        /**
         * Returns named factory suppliers for both synthetic chest sizes.
         */
        static Stream<Named<Supplier<FakeContainer>>> fakeContainerProvider() {
            return Stream.of(
                    Named.of("FakeChest", () -> FakeContainerFactory.getFactory().createFakeChestContainer()),
                    Named.of("FakeDoubleChest", () -> FakeContainerFactory.getFactory().createFakeDoubleChestContainer())
            );
        }

        /**
         * Obtains the installed fake-container factory and requests both synthetic
         * sizes. The factory and both returned containers must exist so plugin
         * interfaces can create either chest layout.
         */
        @Test
        void testFakeContainerFactory() {
            var factory = FakeContainerFactory.getFactory();
            assertNotNull(factory);
            assertNotNull(factory.createFakeChestContainer());
            assertNotNull(factory.createFakeDoubleChestContainer());
        }

        /**
         * Creates a synthetic single chest through the installed factory. Its type must
         * be the shared fake-chest identity with twenty-seven slots so its network
         * layout matches factory allocation.
         */
        @Test
        void testFakeChestContainerType() {
            var fakeChest = FakeContainerFactory.getFactory().createFakeChestContainer();
            assertEquals(ContainerTypes.FAKE_CHEST, fakeChest.getContainerType());
            assertEquals(27, fakeChest.getContainerType().getSize());
        }

        /**
         * Creates a synthetic large chest through the installed factory. Its type must
         * be the shared fake-double-chest identity with fifty-four slots so the larger
         * storage layout remains distinct.
         */
        @Test
        void testFakeDoubleChestContainerType() {
            var fakeDoubleChest = FakeContainerFactory.getFactory().createFakeDoubleChestContainer();
            assertEquals(ContainerTypes.FAKE_DOUBLE_CHEST, fakeDoubleChest.getContainerType());
            assertEquals(54, fakeDoubleChest.getContainerType().getSize());
        }

        /**
         * Runs the same title lifecycle against both fake-container sizes. Each
         * container must start unnamed, retain a supplied title, and return to an
         * unnamed state when cleared.
         */
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

        /**
         * Registers one click callback for each supplied fake-container implementation.
         * Explicitly dispatching that slot click must invoke the callback once so
         * registration reaches the fake interaction path.
         */
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

        /**
         * Registers two independent callbacks on one fake slot. Dispatching the click
         * must invoke both registrations so the fake listener set retains multiple
         * observers for one interface item.
         */
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

        /**
         * Registers callbacks on two different fake slots and clicks only the first.
         * Only its callback may run so fake interactions preserve their slot-specific
         * registration boundary.
         */
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

        /**
         * Dispatches a registered fake-slot callback, removes it, and clicks again. The
         * count must remain unchanged after removal so stale click registrations cannot
         * survive deregistration.
         */
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

        /**
         * Registers callbacks on three fake slots and verifies their initial delivery.
         * Bulk removal must prevent later clicks on every tested slot from invoking any
         * of those callbacks.
         */
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

        /**
         * Dispatches a click on a fake slot without registered callbacks. The operation
         * must complete without an exception so empty listener sets behave as an
         * ordinary ignored interaction.
         */
        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testOnClickWithNoListener(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            assertDoesNotThrow(() -> ((FakeContainerImpl) fakeContainer).onClick(0));
        }

        /**
         * Installs an item and callback together on a fake slot. Lookup must expose the
         * stored item and later click dispatch must invoke the attached callback for
         * that slot.
         */
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

        /**
         * Attempts ordinary viewer opening on both fake-container sizes. Each
         * implementation must reject that operation so callers use the fake-block
         * publication and delayed player-opening path instead.
         */
        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testAddViewerThrowsException(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            assertThrows(UnsupportedOperationException.class, () ->
                    fakeContainer.addViewer(viewer)
            );
        }

        /**
         * Exercises ordinary empty-state, assignment, and clear operations through each
         * fake container. The interface storage must follow the same air-sentinel
         * behavior as the base container implementation.
         */
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

        /**
         * Obtains the stored array from each synthetic chest layout. Its length must
         * match the shared type's declared size so fake storage allocation follows its
         * selected container identity.
         */
        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testSlotCount(Supplier<FakeContainer> containerSupplier) {
            var fakeContainer = containerSupplier.get();
            var expectedSize = fakeContainer.getContainerType().getSize();
            assertEquals(expectedSize, fakeContainer.getItemStackArray().length);
        }

        /**
         * Inserts stone into each fresh synthetic chest layout. The helper must use slot
         * zero, preserve the inserted quantity, and clear the caller's remainder after
         * success.
         */
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

        /**
         * Fills the first and final entries of each fake layout. Bulk clearing must
         * restore whole-container emptiness across both chest sizes rather than stop at
         * a fixed single-chest boundary.
         */
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

        /**
         * Registers and dispatches a click on each layout's final slot. The callback
         * must run so fake click handling supports the complete declared storage range.
         */
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

        /**
         * Calls each supplied factory operation twice for the same layout. The results
         * must be different container objects so independent plugin interfaces do not
         * share one factory singleton.
         */
        @ParameterizedTest
        @MethodSource("fakeContainerProvider")
        void testFactoryCreatesNewInstances(Supplier<FakeContainer> containerSupplier) {
            var container1 = containerSupplier.get();
            var container2 = containerSupplier.get();
            assertNotSame(container1, container2);
        }
    }

    /**
     * {@code DoubleChestContainerTests} groups regression checks for combined-chest
     * storage delegation, viewers, and listener migration.
     */
    @Nested
    class DoubleChestContainerTests {

        /**
         * {@code CHEST_SIZE} is the single-chest slot count used to construct expected
         * combined sizes and offset right-half indices throughout this fixture.
         */
        static final int CHEST_SIZE = ContainerTypes.CHEST.getSize();

        /**
         * Combined chest view under test.
         */
        DoubleChestContainerImpl doubleChest;
        /**
         * Storage backing the first chest half.
         */
        BaseContainer leftChest;
        /**
         * Storage backing the second chest half.
         */
        BaseContainer rightChest;

        /**
         * Creates two empty single chests and configures their shared combined view.
         */
        @BeforeEach
        void setUp() {
            doubleChest = new DoubleChestContainerImpl();
            leftChest = new BaseContainer(ContainerTypes.CHEST);
            rightChest = new BaseContainer(ContainerTypes.CHEST);
            doubleChest.setLeft(leftChest);
            doubleChest.setRight(rightChest);
        }

        /**
         * Examines a configured combined chest view backed by two single chests. Its
         * identity and declared size must describe the shared double-chest layout rather
         * than either underlying half.
         */
        @Test
        void testContainerType() {
            assertEquals(ContainerTypes.DOUBLE_CHEST, doubleChest.getContainerType());
            assertEquals(CHEST_SIZE * 2, doubleChest.getContainerType().getSize());
        }

        /**
         * Changes the underlying left chest directly before combined lookup. The
         * combined left slot must return the same stack reference so the view does not
         * duplicate item storage.
         */
        @Test
        void testGetItemStackDelegatesToLeft() {
            var item = STONE.createItemStack(10);
            leftChest.setItemStack(5, item);

            assertSame(item, doubleChest.getItemStack(5));
        }

        /**
         * Changes a local right-chest slot before combined lookup. Adding the
         * single-chest offset must return that same stack reference so right-side lookup
         * translates virtual indices correctly.
         */
        @Test
        void testGetItemStackDelegatesToRight() {
            var item = DIAMOND.createItemStack(20);
            rightChest.setItemStack(10, item);

            assertSame(item, doubleChest.getItemStack(CHEST_SIZE + 10));
        }

        /**
         * Assigns an item through a left-side combined slot. The underlying left chest
         * must receive the same reference so combined writes delegate to the correct
         * storage half.
         */
        @Test
        void testSetItemStackDelegatesToLeft() {
            var item = STONE.createItemStack(15);
            doubleChest.setItemStack(3, item);

            assertSame(item, leftChest.getItemStack(3));
        }

        /**
         * Assigns an item through an offset right-side combined slot. The underlying
         * right chest must receive that reference at the local index so writes translate
         * the half boundary.
         */
        @Test
        void testSetItemStackDelegatesToRight() {
            var item = DIAMOND.createItemStack(25);
            doubleChest.setItemStack(CHEST_SIZE + 5, item);

            assertSame(item, rightChest.getItemStack(5));
        }

        /**
         * Populates both underlying halves before requesting combined item storage. The
         * returned list must contain the full two-chest slot count so neither half is
         * omitted from aggregation.
         */
        @Test
        void testGetItemStacksCombinesBothChests() {
            leftChest.setItemStack(0, STONE.createItemStack());
            rightChest.setItemStack(0, DIAMOND.createItemStack());

            var items = doubleChest.getItemStacks();
            assertEquals(CHEST_SIZE * 2, items.size());
        }

        /**
         * Requests the array representation of a configured combined view. Its length
         * must span both single-chest halves so callers can enumerate the full virtual
         * container layout.
         */
        @Test
        void testGetItemStackArray() {
            var array = doubleChest.getItemStackArray();
            assertEquals(CHEST_SIZE * 2, array.length);
        }

        /**
         * Opens a configured combined view for the fixture viewer. The operation must
         * succeed and record the viewer in its own membership map rather than require
         * separate half-window registrations.
         */
        @Test
        void testAddViewer() {
            assertTrue(doubleChest.addViewer(viewer));
            assertEquals(1, doubleChest.getViewers().size());
            assertTrue(doubleChest.getViewers().containsValue(viewer));
        }

        /**
         * Opens the combined view and removes its registered viewer. Removal must
         * succeed and clear combined membership so its ordinary lifecycle matches the
         * container viewer contract.
         */
        @Test
        void testRemoveViewer() {
            doubleChest.addViewer(viewer);
            assertTrue(doubleChest.removeViewer(viewer));
            assertEquals(0, doubleChest.getViewers().size());
        }

        /**
         * Registers a combined-window open callback before adding a viewer. The callback
         * must run after opening so listeners can observe the combined interface's
         * viewer lifetime.
         */
        @Test
        void testOpenListener() {
            AtomicBoolean opened = new AtomicBoolean(false);
            doubleChest.addOpenListener(v -> opened.set(true));

            doubleChest.addViewer(viewer);
            assertTrue(opened.get());
        }

        /**
         * Registers a combined-window close callback before opening and removal. The
         * callback must run when the combined viewer leaves so its close listener set
         * participates in normal removal.
         */
        @Test
        void testCloseListener() {
            AtomicBoolean closed = new AtomicBoolean(false);
            doubleChest.addCloseListener(v -> closed.set(true));

            doubleChest.addViewer(viewer);
            doubleChest.removeViewer(viewer);
            assertTrue(closed.get());
        }

        /**
         * Registers a callback on a left-side combined index before assignment. The
         * listener must receive the assigned reference so external registration
         * delegates to the underlying left storage slot.
         */
        @Test
        void testSlotChangeListenerOnLeftSlot() {
            AtomicReference<ItemStack> changedItem = new AtomicReference<>();
            doubleChest.addSlotChangeListener(5, changedItem::set);

            var item = STONE.createItemStack();
            doubleChest.setItemStack(5, item);

            assertSame(item, changedItem.get());
        }

        /**
         * Registers a callback using an offset right-side combined index. Assignment
         * must deliver the exact stored stack so external listener registration
         * translates to the right chest's local slot.
         */
        @Test
        void testSlotChangeListenerOnRightSlot() {
            AtomicReference<ItemStack> changedItem = new AtomicReference<>();
            doubleChest.addSlotChangeListener(CHEST_SIZE + 10, changedItem::set);

            var item = DIAMOND.createItemStack();
            doubleChest.setItemStack(CHEST_SIZE + 10, item);

            assertSame(item, changedItem.get());
        }

        /**
         * Registers a combined-slot callback, assigns once, then removes it before
         * another assignment. The count must stop increasing so deregistration reaches
         * the underlying half as well as bookkeeping.
         */
        @Test
        void testRemoveSlotChangeListener() {
            AtomicInteger count = new AtomicInteger(0);
            Consumer<ItemStack> listener = item -> count.incrementAndGet();

            doubleChest.addSlotChangeListener(0, listener);
            doubleChest.setItemStack(0, STONE.createItemStack());
            assertEquals(1, count.get());

            doubleChest.removeSlotChangeListener(0, listener);
            doubleChest.setItemStack(0, DIAMOND.createItemStack());
            assertEquals(1, count.get());
        }

        /**
         * Opens a combined view and changes the left chest directly. Its tracking viewer
         * must receive one update so synchronization listeners bridge underlying storage
         * changes into the combined window.
         */
        @Test
        void testSyncListenersNotifyViewersOnLeftChange() {
            AtomicInteger viewCount = new AtomicInteger(0);
            var trackingViewer = new FakeContainerViewer() {
                @Override
                public void viewContainerSlot(Container container, int slot) {
                    viewCount.incrementAndGet();
                }
            };

            doubleChest.addViewer(trackingViewer);

            leftChest.setItemStack(0, STONE.createItemStack());

            assertEquals(1, viewCount.get());
        }

        /**
         * Opens a combined view and changes the right chest directly. Its tracking
         * viewer must receive one update so right-half synchronization reaches the
         * combined window without a combined setter.
         */
        @Test
        void testSyncListenersNotifyViewersOnRightChange() {
            AtomicInteger viewCount = new AtomicInteger(0);
            var trackingViewer = new FakeContainerViewer() {
                @Override
                public void viewContainerSlot(Container container, int slot) {
                    viewCount.incrementAndGet();
                }
            };

            doubleChest.addViewer(trackingViewer);

            rightChest.setItemStack(5, DIAMOND.createItemStack());

            assertEquals(1, viewCount.get());
        }

        /**
         * Attempts to serialize the combined virtual chest through its container API.
         * The operation must reject that request so persistence remains the
         * responsibility of the underlying chest storage owners.
         */
        @Test
        void testSaveNBTThrowsException() {
            assertThrows(UnsupportedOperationException.class, () -> doubleChest.saveNBT());
        }

        /**
         * Attempts to load item NBT through the combined virtual chest. The operation
         * must reject the request even with a {@code null} argument so no combined
         * persistence path is implied.
         */
        @Test
        void testLoadNBTThrowsException() {
            assertThrows(UnsupportedOperationException.class, () -> doubleChest.loadNBT(null));
        }

        /**
         * Obtains the viewer map after opening the combined interface. Direct insertion
         * must fail so callers cannot bypass combined opening and removal behavior
         * through its exposed membership view.
         */
        @Test
        void testGetViewersUnmodifiable() {
            doubleChest.addViewer(viewer);
            var viewers = doubleChest.getViewers();

            assertThrows(UnsupportedOperationException.class, () ->
                    viewers.put((byte) 99, new FakeContainerViewer())
            );
        }

        /**
         * Replaces the left chest after an external callback has already observed one
         * assignment. A change in the replacement half must reach that callback so
         * listener migration preserves registrations.
         */
        @Test
        void testSetLeftTransfersListeners() {
            AtomicInteger count = new AtomicInteger(0);
            Consumer<ItemStack> listener = item -> count.incrementAndGet();

            doubleChest.addSlotChangeListener(0, listener);
            leftChest.setItemStack(0, STONE.createItemStack());
            assertEquals(1, count.get());

            var newLeftChest = new BaseContainer(ContainerTypes.CHEST);
            doubleChest.setLeft(newLeftChest);

            newLeftChest.setItemStack(0, DIAMOND.createItemStack());
            assertEquals(2, count.get());
        }

        /**
         * Replaces the right chest after registering an offset combined-slot callback. A
         * local change in the replacement half must reach that callback so right-side
         * listener migration preserves index translation.
         */
        @Test
        void testSetRightTransfersListeners() {
            AtomicInteger count = new AtomicInteger(0);
            Consumer<ItemStack> listener = item -> count.incrementAndGet();

            doubleChest.addSlotChangeListener(CHEST_SIZE + 5, listener);
            rightChest.setItemStack(5, STONE.createItemStack());
            assertEquals(1, count.get());

            var newRightChest = new BaseContainer(ContainerTypes.CHEST);
            doubleChest.setRight(newRightChest);

            newRightChest.setItemStack(5, DIAMOND.createItemStack());
            assertEquals(2, count.get());
        }
    }
}
