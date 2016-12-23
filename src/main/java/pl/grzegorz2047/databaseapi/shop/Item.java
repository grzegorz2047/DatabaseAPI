package pl.grzegorz2047.databaseapi.shop;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
    private final int itemid;
    private final String name;
    private final Material material;
    private final int amount;
    private final String[] ench1;
    private final String[] ench2;
    private final String type;
    private final int slot;
    private final int price;
    private final short data;

    public Item(int itemid, String name, Material material, int amount, String[] ench1, String[] ench2, String type, int slot, int price, short data) {
        this.itemid = itemid;
        this.name = name;
        this.material = material;
        this.amount = amount;
        this.ench1 = ench1;
        this.ench2 = ench2;
        this.type = type;
        this.slot = slot;
        this.price = price;
        this.data = data;
    }

    public int getItemid() {
        return this.itemid;
    }

    public String getName() {
        return this.name;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getAmount() {
        return this.amount;
    }

    public String[] getEnch1() {
        return this.ench1;
    }

    public String[] getEnch2() {
        return this.ench2;
    }

    public String getType() {
        return this.type;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getPrice() {
        return this.price;
    }

    public ItemStack toItemStack() {
        ItemStack itemStack;
        if(this.data != 0) {
            itemStack = new ItemStack(this.material, this.amount, this.data);
        } else {
            itemStack = new ItemStack(this.material, this.amount);
        }

        if(this.material.name().equals("ENCHANTED_BOOK")) {
            ItemMeta im = itemStack.getItemMeta();
            EnchantmentStorageMeta esim = (EnchantmentStorageMeta)im;
            if(this.ench1 != null && this.ench1.length != 0) {
                esim.addStoredEnchant(Enchantment.getByName(this.ench1[0]), Integer.parseInt(this.ench1[1]), true);
            }

            if(this.ench2 != null && this.ench2.length != 0) {
                itemStack.addEnchantment(Enchantment.getByName(this.ench2[0]), Integer.parseInt(this.ench2[1]));
            }

            itemStack.setItemMeta(esim);
        } else {
            if(this.ench1 != null && this.ench1.length != 0) {
                itemStack.addEnchantment(Enchantment.getByName(this.ench1[0]), Integer.parseInt(this.ench1[1]));
            }

            if(this.ench2 != null && this.ench2.length != 0) {
                itemStack.addEnchantment(Enchantment.getByName(this.ench2[0]), Integer.parseInt(this.ench2[1]));
            }
        }

        return itemStack;
    }

    public short getData() {
        return this.data;
    }
}