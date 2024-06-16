package org.allaymc.data;

import org.allaymc.data.chore.DownloadUtil;

/**
 * Allay Project 2024/6/16
 *
 * @author Cool_Loong
 */
public class DownloadResource {
    public static void main(String[] args) {
        DownloadUtil.download("https://github.com/CloudburstMC/Data/raw/master/block_palette.nbt",
                "Allay-Data/resources/block_palette.nbt");
        DownloadUtil.download("https://github.com/CloudburstMC/Data/raw/master/biome_definitions.dat",
                "Allay-Data/resources/biome_definitions.nbt");
        DownloadUtil.download("https://github.com/CloudburstMC/Data/raw/master/entity_identifiers.dat",
                "Allay-Data/resources/entity_identifiers.nbt");
        DownloadUtil.download("https://github.com/CloudburstMC/Data/raw/master/runtime_item_states.json",
                "Allay-Data/resources/unpacked/runtime_item_states.json");
        DownloadUtil.download("https://github.com/CloudburstMC/Data/raw/master/creative_items.json",
                "Allay-Data/resources/creative_items.json");
    }
}
