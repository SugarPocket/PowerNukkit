package cn.nukkit.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandEnum;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.lang.TranslationKey;
import cn.nukkit.utils.TextFormat;

/**
 * @author Pub4Game
 * @since 23.01.2016
 */
public class EnchantCommand extends VanillaCommand {

    public EnchantCommand(String name) {
        super(name, "%nukkit.command.enchant.description", "%commands.enchant.usage");
        this.setPermission("nukkit.command.enchant");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET),
                CommandParameter.newType("enchantmentId", CommandParamType.INT),
                CommandParameter.newType("level", true, CommandParamType.INT)
        });
        this.commandParameters.put("byName", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET),
                CommandParameter.newEnum("enchantmentName", new CommandEnum("Enchant",
                        "protection", "fire_protection", "feather_falling", "blast_protection", "projectile_projection", "thorns", "respiration",
                        "aqua_affinity", "depth_strider", "sharpness", "smite", "bane_of_arthropods", "knockback", "fire_aspect", "looting", "efficiency",
                        "silk_touch", "durability", "fortune", "power", "punch", "flame", "infinity", "luck_of_the_sea", "lure", "frost_walker", "mending",
                        "binding_curse", "vanishing_curse", "impaling", "loyality", "riptide", "channeling", "multishot", "piercing", "quick_charge",
                        "soul_speed")),
                CommandParameter.newType("level", true, CommandParamType.INT)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (missingPermissionOrArgs(sender, args, 2)) {
            return false;
        }
        Player player = sender.getServer().getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(TranslationKey.Commands.GENERIC_PLAYER_NOTFOUND.with(TextFormat.RED));
            return false;
        }
        int enchantId;
        int enchantLevel;
        try {
            enchantId = getIdByName(args[1]);
            enchantLevel = args.length == 3 ? Integer.parseInt(args[2]) : 1;
        } catch (NumberFormatException e) {
            sender.sendMessage(TranslationKey.Commands.GENERIC_USAGE.with(this.usageMessage));
            return false;
        }
        Enchantment enchantment = Enchantment.getEnchantment(enchantId);
        if (enchantment == null) {
            sender.sendMessage(TranslationKey.Commands.ENCHANT_NOTFOUND.with(Integer.toString(enchantId)));
            return false;
        }
        enchantment.setLevel(enchantLevel);
        Item item = player.getInventory().getItemInHand();
        if (item.isNull()) {
            sender.sendMessage(TranslationKey.Commands.ENCHANT_NOITEM.container());
            return false;
        }
        if (item.getId() != ItemID.BOOK) {
            item.addEnchantment(enchantment);
            player.getInventory().setItemInHand(item);
        } else {
            Item enchanted = Item.get(ItemID.ENCHANTED_BOOK, 0, 1, item.getCompoundTag());
            enchanted.addEnchantment(enchantment);
            Item clone = item.clone();
            clone.count--;
            PlayerInventory inventory = player.getInventory();
            inventory.setItemInHand(clone);
            player.giveItem(enchanted);
        }
        Command.broadcastCommandMessage(sender, TranslationKey.Commands.ENCHANT_SUCCESS.with(args[1]));
        return true;
    }

    public int getIdByName(String value) throws NumberFormatException {
        switch (value) {
            case "protection":
                return 0;
            case "fire_protection":
                return 1;
            case "feather_falling":
                return 2;
            case "blast_protection":
                return 3;
            case "projectile_projection":
                return 4;
            case "thorns":
                return 5;
            case "respiration":
                return 6;
            case "aqua_affinity":
                return 7;
            case "depth_strider":
                return 8;
            case "sharpness":
                return 9;
            case "smite":
                return 10;
            case "bane_of_arthropods":
                return 11;
            case "knockback":
                return 12;
            case "fire_aspect":
                return 13;
            case "looting":
                return 14;
            case "efficiency":
                return 15;
            case "silk_touch":
                return 16;
            case "durability":
                return 17;
            case "fortune":
                return 18;
            case "power":
                return 19;
            case "punch":
                return 20;
            case "flame":
                return 21;
            case "infinity":
                return 22;
            case "luck_of_the_sea":
                return 23;
            case "lure":
                return 24;
            case "frost_walker":
                return 25;
            case "mending":
                return 26;
            case "binding_curse":
                return 27;
            case "vanishing_curse":
                return 28;
            case "impaling":
                return 29;
            case "loyality":
                return 30;
            case "riptide":
                return 31;
            case "channeling":
                return 32;
            case "multishot":
                return 33;
            case "piercing":
                return 34;
            case "quick_charge":
                return 35;
            case "soul_speed":
                return 36;
            default:
                return Integer.parseInt(value);
        }
    }
}
