package cn.allay.api.entity.component.impl.playercontroller;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.player.Client;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public interface EntityPlayerControllerComponent {
    @Inject
    @Nullable
    Client getClient();

    @Inject
    void setClient(@Nullable Client client);
}
