package com.extra.power.item.capacitor;

import dev.dubhe.anvilcraft.api.item.IChargerDischargeable;
import dev.dubhe.anvilcraft.api.power.DynamicPowerComponent;
import dev.dubhe.anvilcraft.api.power.IDynamicPowerComponentHolder;
import dev.dubhe.anvilcraft.api.power.PowerGrid;
import dev.dubhe.anvilcraft.item.IInventoryCarriedAware;
import net.minecraft.network.HashedStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static com.extra.power.block.ModBlock.POTATO_BATTERY;
import static dev.dubhe.anvilcraft.item.armor.IonoCraftBackpackItem.addStackProvider;


public class PotatoBatteryItem extends BlockItem implements IChargerDischargeable {



    public PotatoBatteryItem(Block block, Properties properties) {
        super(block, properties);
        addStackProvider(player -> player.getItemBySlot(EquipmentSlot.HEAD));
    }
    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, LivingEntity entity) {
        return armorType == EquipmentSlot.HEAD;
    }


    @Override
    public ItemStack discharge(ItemStack input) {
        return new ItemStack(Items.BAKED_POTATO, 1);
    }
}