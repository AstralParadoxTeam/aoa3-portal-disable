package coutcincerrclog.aoa3portaldisable;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = AoA3PortalDisable.MODID)
public class AdventOfAscensionPortalBlocker {

    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickBlock event) {
        ResourceLocation item = event.getItemStack().getItem().getRegistryName();
        if (!item.getResourceDomain().equals("aoa3") || !item.getResourcePath().endsWith("realmstone"))
            return;
        ResourceLocation block = event.getWorld().getBlockState(event.getPos()).getBlock().getRegistryName();
        if (!block.getResourceDomain().equals("aoa3") || !block.getResourcePath().equals("carved_rune_power"))
            return;
        event.setUseBlock(Event.Result.DENY);
        if (event.getWorld().isRemote)
            event.getEntityPlayer().sendMessage(new TextComponentTranslation("info.aoa3portaldisable.teleporter_disabled"));
    }

}
