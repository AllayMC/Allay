package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.container.GrindstoneTakeResultEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftGrindstoneAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.joml.Vector3d;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * @author IWareQ
 */
@Slf4j
public class CraftGrindstoneActionProcessor implements ContainerActionProcessor<CraftGrindstoneAction> {
    @Override
    public ActionResponse handle(CraftGrindstoneAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(ContainerTypes.GRINDSTONE);
        if (container == null) {
            log.warn("Received a CraftGrindstoneAction without an opened container!");
            return error();
        }

        var inputItem = container.getInput();
        var additionalItem = container.getAdditional();
        if (inputItem.isEmptyOrAir() && additionalItem.isEmptyOrAir()) {
            log.warn("Input and additional item is empty");
            return error();
        }

        var resultItem = !inputItem.isEmptyOrAir() ? inputItem.copy() : additionalItem.copy();

        // Case 1: Enchanted book
        if (resultItem.getItemType() == ItemTypes.ENCHANTED_BOOK) {
            var hasCurse = resultItem.getEnchantments().stream().anyMatch(enchantment ->
                    enchantment.getType().isCursed()
            );
            if (!hasCurse) {
                resultItem = ItemTypes.BOOK.createItemStack();
            }
        } else if (inputItem.getItemType() == additionalItem.getItemType()) {
            // Case 2: Merge items. inputItem > additionalItem
            // Step 1: Merge NBT
            var inputNbt = inputItem.saveNBT();
            var additionalNbt = additionalItem.saveNBT();

            var mergedNbt = additionalNbt.toBuilder();
            mergedNbt.putAll(inputNbt);
            resultItem.loadExtraTag(mergedNbt.build());

            // Step 2: Merge durability at 5%
            var maxDamage = resultItem.getMaxDamage();
            var bonusDamage = maxDamage * 5 / 100;

            var mergedDamage = inputItem.getDamage() + additionalItem.getDamage() - bonusDamage;
            resultItem.setDamage(Math.max(0, mergedDamage - maxDamage));
        }

        // Case 3: Just remove enchantments (except curses)
        resultItem.removeAllEnchantments();
        for (var enchantment : Stream.concat(inputItem.getEnchantments().stream(), additionalItem.getEnchantments().stream()).toList()) {
            if (enchantment.getType().isCursed()) {
                resultItem.addEnchantment(enchantment.getType(), enchantment.getLevel());
            }
        }

        var xp = calculateExperienceFromEnchantments(inputItem);
        xp += calculateExperienceFromEnchantments(additionalItem);

        var event = new GrindstoneTakeResultEvent(player, container, resultItem, xp);
        if (!event.call()) {
            return error();
        }

        xp = event.getExperienceAmount();

        if (xp > 0) {
            var blockPos = container.getBlockPos();
            player.getControlledEntity().getDimension().dropXpOrb(new Vector3d(
                    blockPos.x() + 0.5,
                    blockPos.y() + 1.5,
                    blockPos.z() + 0.5
            ), xp);
        }

        player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, event.getResultItem(), false);
        return null;
    }

    protected int calculateExperienceFromEnchantments(ItemStack itemStack) {
        int totalCost = 0;
        var enchantments = itemStack.getEnchantments();
        for (var enchantment : enchantments) {
            if (!enchantment.getType().isCursed()) {
                totalCost += enchantment.getType().getMinModifiedLevel(enchantment.getLevel());
            }
        }

        if (totalCost == 0) {
            return 0;
        }

        var minExperience = (int) Math.ceil(totalCost / 2d);
        return minExperience + ThreadLocalRandom.current().nextInt(minExperience);
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT;
    }
}
