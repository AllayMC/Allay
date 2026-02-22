package org.allaymc.server.entity.component.sheep;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.item.type.ItemTypes;

import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntitySheepBaseComponentImpl extends EntityBaseComponentImpl implements EntitySheepBaseComponent {

    protected static final String TAG_SHEARED = "Sheared";

    @Dependency
    protected EntityDyeableComponent dyeableComponent;
    @Dependency
    protected EntityAnimalComponent animalComponent;
    @Dependency
    protected EntityBabyComponent babyComponent;
    @Dependency
    protected EntityLivingComponent livingComponent;
    @Dependency(optional = true)
    protected EntityAIComponent aiComponent;

    protected boolean sheared;

    public EntitySheepBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.45, 0.0, -0.45, 0.45, 1.3, 0.45);
    }

    @Override
    public boolean isSheared() {
        return sheared;
    }

    @Override
    public void setSheared(boolean sheared) {
        this.sheared = sheared;
        broadcastState();
    }

    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        if (itemStack == null) return false;

        // Shearing
        if (itemStack.getItemType() == ItemTypes.SHEARS && !sheared && !babyComponent.isBaby()) {
            setSheared(true);
            dropWool();
            itemStack.tryIncreaseDamage(1);
            player.getContainer(org.allaymc.api.container.ContainerTypes.INVENTORY)
                    .notifySlotChange(player.getContainer(org.allaymc.api.container.ContainerTypes.INVENTORY).getHandSlot());
            getDimension().addSound(
                    new Vector3d(location.x(), location.y(), location.z()),
                    new CustomSound(SoundNames.MOB_SHEEP_SHEAR)
            );
            return true;
        }

        // Dyeing
        if (itemStack instanceof ItemDyeComponent dyeComponent) {
            var newColor = dyeComponent.getDyeColor();
            if (newColor != dyeableComponent.getColor()) {
                dyeableComponent.setColor(newColor);
                player.tryConsumeItemInHand();
                return true;
            }
        }

        // Feeding wheat (breeding)
        if (animalComponent.isBreedingItem(itemStack) && aiComponent != null) {
            aiComponent.getMemoryStorage().put(MemoryTypes.LAST_BE_FEED_TIME, thisEntity.getTick());
            aiComponent.getMemoryStorage().put(MemoryTypes.LAST_FEED_PLAYER, player.getRuntimeId());
            player.tryConsumeItemInHand();
            return true;
        }

        return false;
    }

    protected void dropWool() {
        var rand = ThreadLocalRandom.current();
        int count = rand.nextInt(1, 4); // 1-3 wool
        var woolType = getWoolItemForColor(dyeableComponent.getColor());
        if (woolType != null) {
            var woolStack = woolType.createItemStack(count);
            getDimension().dropItem(woolStack, new Vector3d(location.x(), location.y() + 0.5, location.z()));
        }
    }

    protected org.allaymc.api.item.type.ItemType<?> getWoolItemForColor(DyeColor color) {
        return switch (color) {
            case WHITE -> ItemTypes.WHITE_WOOL;
            case ORANGE -> ItemTypes.ORANGE_WOOL;
            case MAGENTA -> ItemTypes.MAGENTA_WOOL;
            case LIGHT_BLUE -> ItemTypes.LIGHT_BLUE_WOOL;
            case YELLOW -> ItemTypes.YELLOW_WOOL;
            case LIME -> ItemTypes.LIME_WOOL;
            case PINK -> ItemTypes.PINK_WOOL;
            case GRAY -> ItemTypes.GRAY_WOOL;
            case LIGHT_GRAY -> ItemTypes.LIGHT_GRAY_WOOL;
            case CYAN -> ItemTypes.CYAN_WOOL;
            case PURPLE -> ItemTypes.PURPLE_WOOL;
            case BLUE -> ItemTypes.BLUE_WOOL;
            case BROWN -> ItemTypes.BROWN_WOOL;
            case GREEN -> ItemTypes.GREEN_WOOL;
            case RED -> ItemTypes.RED_WOOL;
            case BLACK -> ItemTypes.BLACK_WOOL;
        };
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putBoolean(TAG_SHEARED, sheared);
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForBoolean(TAG_SHEARED, v -> this.sheared = v);
    }

}
