package org.allaymc.server.entity.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPaintingBaseComponent;
import org.allaymc.api.entity.data.PaintingType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityAfterDamageEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author PMMP Team | daoge_cmd
 */
public class EntityPaintingBaseComponentImpl extends EntityBaseComponentImpl implements EntityPaintingBaseComponent {

    protected static final String TAG_MOTIVE = "Motive";

    @Dependency
    protected EntityLivingComponent livingComponent;

    @Getter
    protected PaintingType paintingType;

    public EntityPaintingBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void setPaintingType(PaintingType paintingType) {
        Preconditions.checkNotNull(paintingType, "PaintingType cannot be null");
        this.paintingType = paintingType;
    }

    @Override
    public AABBdc getAABB() {
        if (this.paintingType == null) {
            return new AABBd(0, 0, 0, 0, 0, 0);
        }

        return this.paintingType.getAABB(getHorizontalFace());
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForString(TAG_MOTIVE, value -> this.paintingType = PaintingType.fromTitle(value));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putString(TAG_MOTIVE, this.paintingType.getTitle())
                .build();
    }

    @EventHandler
    protected void onDamage(CEntityAfterDamageEvent event) {
        if (!isSpawned()) {
            return;
        }

        var drops = true;
        var lastDamage = this.livingComponent.getLastDamage();
        if (lastDamage.getAttacker() instanceof EntityPlayer player &&
            player.getGameMode() == GameMode.CREATIVE) {
            drops = false;
        }

        var dimension = getDimension();
        dimension.addParticle(this.location.add(0.5, 0.5, 0.5, new Vector3d()), new BlockBreakParticle(BlockTypes.OAK_PLANKS.getDefaultState()));
        if (drops) {
            dimension.dropItem(ItemTypes.PAINTING.createItemStack(), this.location);
        }
        remove();
    }
}
