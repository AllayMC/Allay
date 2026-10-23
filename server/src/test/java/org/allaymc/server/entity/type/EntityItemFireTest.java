package org.allaymc.server.entity.type;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@code EntityItemFireTest} compares ordinary and fireproof dropped-item
 * responses to repeated fire contact.
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class EntityItemFireTest {
    /**
     * Mock dimension supplying world fire-damage rules.
     */
    private Dimension dimension;
    /**
     * Fire block fixture at the item position.
     */
    private Block fire;

    /**
     * Creates a dimension fixture with fire damage enabled and a fire block at
     * the item location. Each test receives new mocks so repeated contact
     * exercises only its own entity state.
     */
    @BeforeEach
    void setUp() {
        var worldData = mock(WorldData.class);
        when(worldData.<Boolean>getGameRuleValue(GameRule.FIRE_DAMAGE)).thenReturn(true);

        var world = mock(World.class);
        when(world.getWorldData()).thenReturn(worldData);

        var dimensionType = mock(DimensionType.class);
        when(dimensionType.getMinHeight()).thenReturn(-64);

        dimension = mock(Dimension.class);
        when(dimension.getWorld()).thenReturn(world);
        when(dimension.getDimensionType()).thenReturn(dimensionType);

        fire = new Block(
                BlockTypes.FIRE.getDefaultState(),
                new Position3i(0, 64, 0, dimension),
                0
        );
    }

    /**
     * Keeps an ordinary dropped stick in repeated fire contact while ticking
     * its entity base. Fire damage must reduce its health to zero within the
     * forty-five-contact-tick test window.
     */
    @Test
    void regularItemShouldBurnWithinFortyFiveContactTicks() {
        var item = createItem(ItemTypes.STICK);

        setOnFire(item);
        // Bound ordinary-item burn verification while stopping contact once health
        // reaches zero.
        for (int currentTick = 1; currentTick <= 45 && item.getHealth() > 0; currentTick++) {
            tick(item, currentTick);
            setOnFire(item);
        }

        assertEquals(0, item.getHealth());
    }

    /**
     * Keeps a dropped netherite ingot in repeated fire contact for forty-five
     * ticks. The fireproof item must retain its initial health and never
     * accumulate an on-fire duration.
     */
    @Test
    void fireproofItemShouldNotIgniteOrTakeFireDamage() {
        var item = createItem(ItemTypes.NETHERITE_INGOT);

        setOnFire(item);
        // Use the same contact window as the ordinary-item test to compare
        // sustained fire immunity.
        for (int currentTick = 1; currentTick <= 45; currentTick++) {
            tick(item, currentTick);
            setOnFire(item);
        }

        assertEquals(5, item.getHealth());
        assertEquals(0, item.getOnFireTicks());
    }

    /**
     * Creates a dropped item at the fire position and advances its base
     * lifecycle to alive. The helper asserts both transitions so damage tests
     * cannot silently exercise an unspawned entity instead.
     */
    private EntityItem createItem(ItemType<?> itemType) {
        var item = EntityTypes.ITEM.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .pos(0, 64, 0)
                .build());
        item.setItemStack(itemType.createItemStack());

        var baseComponent = getBaseComponent(item);
        // Activate the entity through both legal lifecycle transitions so fire
        // callbacks run against the same alive state required by damage processing.
        assertTrue(baseComponent.setState(EntityState.SPAWNED_LATER));
        assertTrue(baseComponent.setState(EntityState.ALIVE));
        return item;
    }

    /**
     * Applies the fire block contact callback to the supplied dropped item.
     */
    private void setOnFire(EntityItem item) {
        BlockTypes.FIRE.getBlockBehavior().onEntityInside(fire, item);
    }

    /**
     * Advances the supplied item base by the requested test tick.
     */
    private void tick(EntityItem item, long currentTick) {
        getBaseComponent(item).tick(currentTick);
    }

    /**
     * Returns the concrete base component retained by the test item entity.
     */
    private EntityBaseComponentImpl getBaseComponent(EntityItem item) {
        return (EntityBaseComponentImpl) ((EntityImpl) item).getBaseComponent();
    }
}
