package com.teammoeg.elainabroom.entity;

import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BroomEntity extends PigEntity {
    public BroomEntity(EntityType<? extends PigEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canWalkOnFluid(Fluid fluid) {
        if(this.getEquippedStack(EquipmentSlot.CHEST).getItem()==Items.OAK_PLANKS)
        {
            if(this.getEquippedStack(EquipmentSlot.CHEST).getTag().getInt("type")==0)
            {
                return FluidTags.WATER.contains(fluid);
            }
        }
        return false;
    }

    // Pig speed
    @Override
    public float getSaddledSpeed() {
        return 0.3f;
    }

    @Override
    protected void initGoals() { }

    @Override
    public boolean canBeRiddenInWater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
    }

    @Override
    protected SoundEvent getFallSound(int distance) {
        return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_BOAT_PADDLE_WATER;
    }

    @Override
    protected int computeFallDamage(float fallDistance, float damageMultiplier) {
        return 0;
    }

    @Override
    public int getSafeFallDistance() {
        return 400;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    protected boolean movesIndependently() {
        return false;
    }

    @Override
    public boolean canMoveVoluntarily() {
        return false;
    }

    @Override
    public boolean canBeControlledByRider() {
        return true;
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if(!player.getEntityWorld().isClient && player.getStackInHand(hand)==ItemStack.EMPTY && hand==Hand.MAIN_HAND)  //todo: changed
        {
            player.startRiding(this,true);
            return ActionResult.SUCCESS;
        }
        if(!player.getEntityWorld().isClient && player.getStackInHand(hand).getItem()==Items.DIAMOND && hand==Hand.MAIN_HAND)  //todo: changed
        {
            this.getEquippedStack(EquipmentSlot.CHEST).getTag().putInt("offset",player.getStackInHand(hand).getTag().getInt("off"));
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return true;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    @Override
    public void updatePassengerPosition(Entity passenger) {
        int extra = 0;
        passenger.fallDistance=0;
        if(this.getEquippedStack(EquipmentSlot.CHEST).getItem()==Items.OAK_PLANKS)
        {
            extra = this.getEquippedStack(EquipmentSlot.CHEST).getTag().getInt("offset")-1;
        }
        if(this.getPrimaryPassenger() instanceof PlayerEntity)
        {
            if(passenger==this.getPrimaryPassenger())
            {
                passenger.updatePosition(this.getX(),this.getY()+0.5+extra,this.getZ());
            }
            else
            {
                Vector3f dir = this.getMovementDirection().getOpposite().getUnitVector();
                int vv = this.getPassengerList().indexOf(passenger);
                passenger.updatePosition(this.getPrimaryPassenger().getX()+dir.getX()*vv, this.getPrimaryPassenger().getY()+dir.getY()*vv, this.getPrimaryPassenger().getZ()+dir.getZ()*vv);
            }
        }
    }


}
