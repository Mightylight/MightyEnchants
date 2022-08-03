package me.mightylight.mightyenchants;

import me.mightylight.mightyenchants.customenchants.TripleShotEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class MightyEnchants extends JavaPlugin {

    static MightyEnchants instance;
    public static TripleShotEnchantment tripleshot;

    public static MightyEnchants getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        tripleshot = new TripleShotEnchantment();
        Bukkit.getPluginManager().registerEvents(tripleshot,this);
        registerEnchantment(tripleshot);
    }

    @Override
    public void onDisable() {
        //code for disabling an enchantment
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(tripleshot.getKey())) {
                byKey.remove(tripleshot.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(tripleshot.getName())) {
                byName.remove(tripleshot.getName());
            }
        } catch (Exception ignored) { }

        Bukkit.getLogger().info("Shutting down");
    }


    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }
}
