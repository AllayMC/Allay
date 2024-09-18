package org.allaymc.api.server;

import com.google.common.collect.Sets;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
public class Whitelist extends OkaeriConfig {
    @Comment("Whitelisted player list. The value can be player's name or uuid")
    private Set<String> whitelist = Sets.newConcurrentHashSet();
}
