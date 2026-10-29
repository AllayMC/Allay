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
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class EntityItemFireTest {
    private Dimension dimension;
    private Block fire;

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

    @Test
    void regularItemShouldBurnWithinFortyFiveContactTicks() {
        var item = createItem(ItemTypes.STICK);

        setOnFire(item);
        for (int currentTick = 1; currentTick <= 45 && item.getHealth() > 0; currentTick++) {
            tick(item, currentTick);
            setOnFire(item);
        }

        assertEquals(0, item.getHealth());
    }

    @Test
    void fireproofItemShouldNotIgniteOrTakeFireDamage() {
        var item = createItem(ItemTypes.NETHERITE_INGOT);

        setOnFire(item);
        for (int currentTick = 1; currentTick <= 45; currentTick++) {
            tick(item, currentTick);
            setOnFire(item);
        }

        assertEquals(5, item.getHealth());
        assertEquals(0, item.getOnFireTicks());
    }

    private EntityItem createItem(ItemType<?> itemType) {
        var item = EntityTypes.ITEM.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .pos(0, 64, 0)
                .build());
        item.setItemStack(itemType.createItemStack());

        var baseComponent = getBaseComponent(item);
        assertTrue(baseComponent.setState(EntityState.SPAWNED_LATER));
        assertTrue(baseComponent.setState(EntityState.ALIVE));
        return item;
    }

    private void setOnFire(EntityItem item) {
        BlockTypes.FIRE.getBlockBehavior().onEntityInside(fire, item);
    }

    private void tick(EntityItem item, long currentTick) {
        getBaseComponent(item).tick(currentTick);
    }

    private EntityBaseComponentImpl getBaseComponent(EntityItem item) {
        return (EntityBaseComponentImpl) ((EntityImpl) item).getBaseComponent();
    }
}
