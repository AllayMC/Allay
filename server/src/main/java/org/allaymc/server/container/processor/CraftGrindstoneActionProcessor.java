package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.container.GrindstoneTakeResultEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
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
    public ActionResponse handle(CraftGrindstoneAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(FullContainerType.GRINDSTONE);
        if (container == null) {
            log.warn("Received a CraftGrindstoneAction without an opened container!");
            return error();
        }

        var input = container.getInput();
        var additional = container.getAdditional();
        if (input.isEmptyOrAir() && additional.isEmptyOrAir()) {
            log.warn("Input and additional item is empty");
            return error();
        }

        var resultItem = !input.isEmptyOrAir() ? input.copy() : additional.copy();

        // Case 1: Enchanted book
        if (resultItem.getItemType() == ItemTypes.ENCHANTED_BOOK) {
            var hasCurse = resultItem.getEnchantments().stream().anyMatch(ench -> ench.getType().isCursed());
            if (!hasCurse) {
                resultItem = ItemTypes.BOOK.createItemStack();
            }
        } else if (input.getItemType() == additional.getItemType()) {
            // Case 2: Merge items. input > additional
            // Step 1: Merge NBT
            var inputNbt = input.saveNBT();
            var additionalNbt = additional.saveNBT();

            var mergedNbt = additionalNbt.toBuilder();
            mergedNbt.putAll(inputNbt);
            resultItem.loadExtraTag(mergedNbt.build());

            // Step 2: Merge durability at 5%
            var maxDurability = input.getItemType().getItemData().maxDamage();

            var remainingDurabilityInput = maxDurability - input.getDurability();
            var remainingDurabilityAdditional = maxDurability - additional.getDurability();

            var totalRemainingDurability = remainingDurabilityInput + remainingDurabilityAdditional + (maxDurability * 5 / 100);

            var finalDurability = Math.min(totalRemainingDurability, maxDurability);
            resultItem.setDurability(maxDurability - finalDurability);
        }

        // Case 3: Just remove enchantments (except curses)
        resultItem.removeAllEnchantments();
        for (var ench : Stream.concat(input.getEnchantments().stream(), additional.getEnchantments().stream()).toList()) {
            if (ench.getType().isCursed()) {
                resultItem.addEnchantment(ench.getType(), ench.getLevel());
            }
        }

        var xp = calculateExperienceFromEnchantments(input);
        xp += calculateExperienceFromEnchantments(additional);

        var event = new GrindstoneTakeResultEvent(player, container, resultItem, xp);
        if (!event.call()) {
            return error();
        }

        xp = event.getExperienceAmount();

        if (xp > 0) {
            var blockPos = container.getBlockPos();
            player.getDimension().dropXpOrb(new Vector3d(
                    blockPos.x() + 0.5,
                    blockPos.y() + 1.5,
                    blockPos.z() + 0.5
            ), xp);
        }

        player.getContainer(FullContainerType.CREATED_OUTPUT).setItemStack(event.getResultItem());
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
