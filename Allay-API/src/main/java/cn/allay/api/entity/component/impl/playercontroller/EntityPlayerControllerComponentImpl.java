package cn.allay.api.entity.component.impl.playercontroller;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.player.Client;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class EntityPlayerControllerComponentImpl implements EntityPlayerControllerComponent, EntityComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_controller_component");

    protected Client client;

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    @Nullable
    @Impl
    public Client getClient() {
        return client;
    }

    @Override
    @Impl
    public void setClient(@Nullable Client client) {
        this.client = client;
    }
}
