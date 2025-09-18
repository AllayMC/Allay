package org.allaymc.api.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemDiamondStack;
import org.allaymc.api.item.interfaces.ItemGrassBlockStack;
import org.allaymc.api.item.recipe.impl.FurnaceRecipe;
import org.allaymc.api.item.recipe.impl.ShapedRecipe;
import org.allaymc.api.item.recipe.impl.ShapelessRecipe;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.allaymc.api.item.type.ItemTypes.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class RecipeTest {
    public static final Map<Character, ItemDescriptor> GRASS_KEY = Map.of('x', new ItemTypeDescriptor(GRASS_BLOCK));

    @Test
    void testShapedRecipe() {
        var grassMagic1 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_1"))
                .tag("test_tag")
                .pattern(
                        ShapedRecipe.PatternHelper.build(
                                "xx",
                                "xx"
                        )
                )
                .keys(GRASS_KEY)
                .outputs(new ItemStack[]{diamond()})
                .build();

        assertEquals(new Identifier("minecraft:grass_magic_1"), grassMagic1.getIdentifier());
        assertEquals(DIAMOND, grassMagic1.getOutputs()[0].getItemType());
        assertEquals("test_tag", grassMagic1.getTag());

        var input1 = new CraftingRecipeInput(
                grass(), grass(), air(),
                grass(), grass(), air(),
                air(), air(), air()
        );
        var input2 = new CraftingRecipeInput(
                grass(), grass(),
                grass(), grass()
        );
        var input3 = new CraftingRecipeInput(
                air(), grass(), grass(),
                air(), grass(), grass(),
                air(), air(), air()
        );
        var input4 = new CraftingRecipeInput(
                air(), air(), air(),
                air(), grass(), grass(),
                air(), grass(), grass()
        );
        var input5 = new CraftingRecipeInput(
                air(), air(), air(),
                grass(), grass(), air(),
                grass(), grass(), air()
        );

        assertTrue(grassMagic1.match(input1));
        assertTrue(grassMagic1.match(input2));
        assertTrue(grassMagic1.match(input3));
        assertTrue(grassMagic1.match(input4));
        assertTrue(grassMagic1.match(input5));

        var input6 = new CraftingRecipeInput(
                grass(), grass(), grass(),
                grass(), grass(), grass(),
                grass(), grass(), grass()
        );
        var input7 = new CraftingRecipeInput(
                air(), air(), air(),
                air(), air(), air(),
                air(), air(), air()
        );

        assertFalse(grassMagic1.match(input6));
        assertFalse(grassMagic1.match(input7));

        var grassMagic2 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_2"))
                .pattern(
                        ShapedRecipe.PatternHelper.build(
                                "x",
                                "x"
                        )
                )
                .keys(GRASS_KEY)
                .outputs(new ItemStack[]{diamond()})
                .build();

        var input8 = new CraftingRecipeInput(
                grass(), air(), air(),
                grass(), air(), air(),
                air(), air(), air()
        );
        var input9 = new CraftingRecipeInput(
                air(), grass(), air(),
                air(), grass(), air(),
                air(), air(), air()
        );
        var input10 = new CraftingRecipeInput(
                air(), air(), grass(),
                air(), air(), grass(),
                air(), air(), air()
        );
        var input11 = new CraftingRecipeInput(
                air(), air(), air(),
                grass(), air(), air(),
                grass(), air(), air()
        );
        var input12 = new CraftingRecipeInput(
                air(), air(), air(),
                air(), grass(), air(),
                air(), grass(), air()
        );
        var input13 = new CraftingRecipeInput(
                air(), air(), air(),
                air(), air(), grass(),
                air(), air(), grass()
        );

        assertTrue(grassMagic2.match(input8));
        assertTrue(grassMagic2.match(input9));
        assertTrue(grassMagic2.match(input10));
        assertTrue(grassMagic2.match(input11));
        assertTrue(grassMagic2.match(input12));
        assertTrue(grassMagic2.match(input13));

        assertFalse(grassMagic2.match(input1));
        assertFalse(grassMagic2.match(input2));
        assertFalse(grassMagic2.match(input3));
        assertFalse(grassMagic2.match(input4));
        assertFalse(grassMagic2.match(input5));
        assertFalse(grassMagic2.match(input6));
        assertFalse(grassMagic2.match(input7));

        var grassMagic3 = ShapedRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_3"))
                .pattern(
                        ShapedRecipe.PatternHelper.build(
                                'x'
                        )
                )
                .keys(GRASS_KEY)
                .outputs(new ItemStack[]{diamond()})
                .build();

        var input14 = new CraftingRecipeInput(
                grass(), air(),
                air(), air()
        );
        var input15 = new CraftingRecipeInput(
                air(), grass(),
                air(), air()
        );
        var input16 = new CraftingRecipeInput(
                air(), air(),
                grass(), air()
        );
        var input17 = new CraftingRecipeInput(
                air(), air(),
                air(), grass()
        );

        assertTrue(grassMagic3.match(input14));
        assertTrue(grassMagic3.match(input15));
        assertTrue(grassMagic3.match(input16));
        assertTrue(grassMagic3.match(input17));
    }

    @Test
    void testShapelessRecipe() {
        var grassMagic1 = ShapelessRecipe
                .builder()
                .identifier(new Identifier("minecraft:grass_magic_1"))
                .ingredients(
                        new ItemDescriptor[]{
                                new ItemTypeDescriptor(GRASS_BLOCK),
                                new ItemTypeDescriptor(GRASS_BLOCK),
                                new ItemTypeDescriptor(GRASS_BLOCK)
                        }
                )
                .outputs(new ItemStack[]{diamond()})
                .build();

        var input1 = new CraftingRecipeInput(grass(), grass(), grass(), air());

        assertTrue(grassMagic1.match(input1));

        var input2 = new CraftingRecipeInput(
                grass(), grass(),
                grass(), grass());
        var input3 = new CraftingRecipeInput(
                grass(), grass(), grass(),
                diamond());

        assertFalse(grassMagic1.match(input2));
        assertFalse(grassMagic1.match(input3));
    }

    @Test
    void testFurnaceRecipe() {
        var grassMagic1 = FurnaceRecipe
                .builder()
                .ingredient(new ItemTypeDescriptor(GRASS_BLOCK))
                .tag(FurnaceRecipe.FURNACE_TAG)
                .output(diamond())
                .build();

        var input1 = new FurnaceRecipeInput(grass(), FurnaceRecipe.FURNACE_TAG);

        assertTrue(grassMagic1.match(input1));

        var grassMagic2 = FurnaceRecipe
                .builder()
                .ingredient(new ItemTypeDescriptor(GRASS_BLOCK))
                .tag(FurnaceRecipe.BLAST_FURNACE_TAG)
                .output(diamond())
                .build();

        var input2 = new FurnaceRecipeInput(grass(), FurnaceRecipe.FURNACE_TAG);

        assertFalse(grassMagic2.match(input2));
        assertEquals(grassMagic2.getIdentifier(), new Identifier(GRASS_BLOCK.getIdentifier() + "_" + FurnaceRecipe.BLAST_FURNACE_TAG));

        var input3 = new FurnaceRecipeInput(grass(), FurnaceRecipe.BLAST_FURNACE_TAG);

        assertTrue(grassMagic1.match(input3));
    }

    private ItemDiamondStack diamond() {
        return DIAMOND.createItemStack(
                ItemStackInitInfo.builder().count(1).build()
        );
    }

    private ItemGrassBlockStack grass() {
        return GRASS_BLOCK.createItemStack(
                ItemStackInitInfo.builder().count(1).build()
        );
    }

    private ItemAirStack air() {
        return AIR.createItemStack(
                ItemStackInitInfo.builder().count(1).build()
        );
    }
}