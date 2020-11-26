package com.teammoeg.elainabroom.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

public abstract class AbstractEntityFromItem extends LivingEntity {

    AbstractEntityFromItem(EntityType<? extends LivingEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    // Won't be damaged
    @Override
    public boolean isInvulnerable() {
        return true;
    }

    // Won't be damaged by any source
    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return true;
    }

    // No fall damage
    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    // No knockback
    @Override
    protected void knockback(LivingEntity target) {

    }

    // Default methods
    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return Items.AIR.getDefaultStack();
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

}
