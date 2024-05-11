package org.allaymc.api.server;

import com.google.common.collect.Sets;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
public class BanInfo extends OkaeriConfig {
    @CustomKey("banned-players")
    @Comment("Banned player list. The value can be player's name or uuid")
    private Set<String> bannedPlayers = Sets.newConcurrentHashSet();

    @CustomKey("banned-ips")
    @Comment("Banned ip list")
    private Set<String> bannedIps = Sets.newConcurrentHashSet();
}
