package me.mightylight.mightyenchants.customenchants;

import io.papermc.paper.enchantments.EnchantmentRarity;
import me.mightylight.mightyenchants.MightyEnchants;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Set;

public class TripleShotEnchantment extends Enchantment implements Listener {


    public TripleShotEnchantment(){
        super(new NamespacedKey(MightyEnchants.getInstance(),"triple_shot"));
    }

    @EventHandler
    public void OnArrowShot(EntityShootBowEvent event){
        if(event.getProjectile() instanceof Arrow){
            if(event.getEntity() instanceof Player){
                if(event.getBow().getEnchantments().containsKey(Enchantment.getByKey(MightyEnchants.tripleshot.getKey()))) {
                    Arrow arrow = (Arrow) event.getProjectile();
                    Arrow arrow1 = event.getEntity().getWorld().spawn(event.getEntity().getEyeLocation(), Arrow.class);
                    arrow1.setShooter(event.getEntity());
                    arrow1.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(10)));
                    Arrow arrow2 = event.getEntity().getWorld().spawn(event.getEntity().getEyeLocation(), Arrow.class);
                    arrow2.setShooter(event.getEntity());
                    arrow2.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-10)));
                }
            }
        }
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event){
        Bukkit.getLogger().info("event triggered");
        ItemStack item = new ItemStack(Material.BOW,1);
        item.addUnsafeEnchantment(MightyEnchants.tripleshot,1);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Triple Shot I");
        meta.setLore(lore);
        item.setItemMeta(meta);
        event.getPlayer().getInventory().addItem(item);
    }

    @Override
    public @NotNull String getName() {
        return "TripleShot";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return true;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("TripleShot");
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return null;
    }

    @Override
    public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return null;
    }

    @Override
    public @NotNull String translationKey() {
        return null;
    }
}

