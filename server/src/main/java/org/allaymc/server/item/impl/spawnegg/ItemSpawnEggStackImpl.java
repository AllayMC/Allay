package org.allaymc.server.item.impl.spawnegg;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.spawnegg.*;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemSpawnEggStackImpl extends ItemStackImpl implements
        ItemTropicalFishSpawnEggStack, ItemShulkerSpawnEggStack, ItemHoglinSpawnEggStack, ItemArmadilloSpawnEggStack, ItemZombieSpawnEggStack,
        ItemIronGolemSpawnEggStack, ItemDonkeySpawnEggStack, ItemEndermanSpawnEggStack, ItemLlamaSpawnEggStack, ItemCreeperSpawnEggStack,
        ItemWitherSkeletonSpawnEggStack, ItemRabbitSpawnEggStack, ItemNpcSpawnEggStack, ItemPolarBearSpawnEggStack, ItemStraySpawnEggStack,
        ItemBoggedSpawnEggStack, ItemSnifferSpawnEggStack, ItemZombieHorseSpawnEggStack, ItemSilverfishSpawnEggStack, ItemWolfSpawnEggStack,
        ItemPiglinSpawnEggStack, ItemHuskSpawnEggStack, ItemWitherSpawnEggStack, ItemDrownedSpawnEggStack, ItemStriderSpawnEggStack,
        ItemZombieVillagerSpawnEggStack, ItemBatSpawnEggStack, ItemMooshroomSpawnEggStack, ItemSlimeSpawnEggStack, ItemCamelSpawnEggStack,
        ItemTadpoleSpawnEggStack, ItemCowSpawnEggStack, ItemCaveSpiderSpawnEggStack, ItemCodSpawnEggStack, ItemVillagerSpawnEggStack,
        ItemAllaySpawnEggStack, ItemZoglinSpawnEggStack, ItemSkeletonSpawnEggStack, ItemGoatSpawnEggStack, ItemElderGuardianSpawnEggStack,
        ItemBeeSpawnEggStack, ItemPufferfishSpawnEggStack, ItemSpiderSpawnEggStack, ItemSnowGolemSpawnEggStack, ItemRavagerSpawnEggStack,
        ItemZombiePigmanSpawnEggStack, ItemMuleSpawnEggStack, ItemHorseSpawnEggStack, ItemPiglinBruteSpawnEggStack, ItemSheepSpawnEggStack,
        ItemEnderDragonSpawnEggStack, ItemOcelotSpawnEggStack, ItemChickenSpawnEggStack, ItemGlowSquidSpawnEggStack, ItemParrotSpawnEggStack,
        ItemTurtleSpawnEggStack, ItemCatSpawnEggStack, ItemVindicatorSpawnEggStack, ItemSkeletonHorseSpawnEggStack, ItemGuardianSpawnEggStack,
        ItemTraderLlamaSpawnEggStack, ItemSalmonSpawnEggStack, ItemAgentSpawnEggStack, ItemPigSpawnEggStack, ItemPhantomSpawnEggStack,
        ItemWardenSpawnEggStack, ItemVexSpawnEggStack, ItemFrogSpawnEggStack, ItemSquidSpawnEggStack, ItemBlazeSpawnEggStack,
        ItemGhastSpawnEggStack, ItemWitchSpawnEggStack, ItemDolphinSpawnEggStack, ItemEvokerSpawnEggStack, ItemFoxSpawnEggStack,
        ItemSpawnEggStack, ItemPillagerSpawnEggStack, ItemWanderingTraderSpawnEggStack, ItemEndermiteSpawnEggStack, ItemMagmaCubeSpawnEggStack,
        ItemPandaSpawnEggStack, ItemAxolotlSpawnEggStack, ItemBreezeSpawnEggStack {
    public ItemSpawnEggStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
