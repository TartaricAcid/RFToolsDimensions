package mcjty.rftoolsdim.dimensions.dimlets.types;

import mcjty.rftoolsdim.config.Settings;
import mcjty.rftoolsdim.dimensions.dimlets.DimletKey;
import mcjty.rftoolsdim.dimensions.dimlets.KnownDimletConfiguration;
import net.minecraft.item.ItemStack;

public class DimletCraftingTools {
    public static boolean matchDimletRecipe(DimletKey key, ItemStack stackController, ItemStack stackMemory, ItemStack stackEnergy) {
        Settings settings = KnownDimletConfiguration.getSettings(key);
        if (settings == null) {
            return false;
        }
        int rarity = settings.getRarity();
        if (stackController.getItemDamage() != rarity) {
            return false;
        }
        int level = calculateItemLevelFromRarity(rarity);
        if (stackMemory.getItemDamage() != level) {
            return false;
        }
        if (stackEnergy.getItemDamage() != level) {
            return false;
        }

        return true;
    }

    public static int calculateItemLevelFromRarity(int rarity) {
        int level;
        if (rarity <= 1) {
            level = 0;
        } else if (rarity <= 3) {
            level = 1;
        } else {
            level = 2;
        }
        return level;
    }
}
