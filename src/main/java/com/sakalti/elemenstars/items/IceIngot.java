package com.sakalti.inflationcraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IceIngot extends Item {
    public IceIngot() {
        super(new Item.Properties().group(ItemGroup.MATERIALS));
    }
}
