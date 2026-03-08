package org.allaymc.server.item.recipe;

import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.data.FireworkType;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class FireworkRecipeTest {
    @Test
    void testCraftFireworkRocket() {
        assertEquals(
                1,
                ((ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                        paper(), gunpowder(),
                        air(), air()
                ))[0]).getDuration()
        );
        assertEquals(
                2,
                ((ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                        paper(), gunpowder(),
                        gunpowder(), air()
                ))[0]).getDuration()
        );
        assertEquals(
                3,
                ((ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                        paper(), gunpowder(),
                        gunpowder(), gunpowder()
                ))[0]).getDuration()
        );

        var explosion = new FireworkExplosion(
                FireworkType.SMALL_SPHERE, List.of(DyeColor.WHITE),
                Collections.emptyList(), true, true
        );
        var fireworkStar = fireworkStar();
        fireworkStar.setExplosion(explosion);
        var rocket = (ItemFireworkRocketStack) FireworkRecipe.INSTANCE.getOutputs(new CraftingRecipeInput(
                paper(), gunpowder(),
                gunpowder(), fireworkStar
        ))[0];
        assertEquals(2, rocket.getDuration());
        assertEquals(explosion, rocket.getExplosions().toArray()[0]);
    }

    @Test
    void testCraftFireworkStar() {
        var input = new CraftingRecipeInput(
                blueDye(), pinkDye(), whiteDye(),
                diamond(), gunpowder(), fireCharge(),
                air(), air(), air()
        );

        var output = (ItemFireworkStarStack) FireworkRecipe.INSTANCE.getOutputs(input)[0];
        var explosion = output.getExplosion();
        assertEquals(FireworkType.HUGE_SPHERE, explosion.type());
        assertEquals(List.of(DyeColor.BLUE, DyeColor.PINK, DyeColor.WHITE), explosion.colors());
        assertEquals(Collections.emptyList(), explosion.fadeColors());
        assertFalse(explosion.flicker());
        assertTrue(explosion.trail());
    }

    @Test
    void testAddFadeColorToFireStar() {
        var input = new CraftingRecipeInput(
                blueDye(), pinkDye(), whiteDye(),
                air(), fireworkStar(), air(),
                air(), air(), air()
        );
        var output = (ItemFireworkStarStack) FireworkRecipe.INSTANCE.getOutputs(input)[0];
        var explosion = output.getExplosion();
        assertEquals(List.of(DyeColor.BLUE, DyeColor.PINK, DyeColor.WHITE), explosion.fadeColors());
    }

    private ItemAirStack air() {
        return ItemTypes.AIR.createItemStack();
    }

    private ItemGunpowderStack gunpowder() {
        return ItemTypes.GUNPOWDER.createItemStack();
    }

    private ItemPaperStack paper() {
        return ItemTypes.PAPER.createItemStack();
    }

    private ItemDyeStack blueDye() {
        return ItemTypes.BLUE_DYE.createItemStack();
    }

    private ItemDyeStack pinkDye() {
        return ItemTypes.PINK_DYE.createItemStack();
    }

    private ItemDyeStack whiteDye() {
        return ItemTypes.WHITE_DYE.createItemStack();
    }

    private ItemFireworkStarStack fireworkStar() {
        return ItemTypes.FIREWORK_STAR.createItemStack();
    }

    private ItemDiamondStack diamond() {
        return ItemTypes.DIAMOND.createItemStack();
    }

    private ItemFireChargeStack fireCharge() {
        return ItemTypes.FIRE_CHARGE.createItemStack();
    }
}