package mcjty.rftoolsdim.network;

import mcjty.lib.tools.MinecraftTools;
import mcjty.lib.varia.Logging;
import mcjty.rftoolsdim.config.DimletRules;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SyncRulesHelper {

    public static void syncRulesFromServer(PacketSyncRules message) {
        World world = MinecraftTools.getWorld(Minecraft.getMinecraft());
        Logging.log("Received dimlet rules from server");
        DimletRules.syncRulesFromServer(message.getRules());
    }

}
