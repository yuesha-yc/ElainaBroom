package com.teammoeg.elainabroom.entity;

import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class BroomEntity extends AnimalEntity implements Saddleable {

    private int eatingGrassTicks;
    private int eatingTicks;
    private int angryTicks;
    public int tailWagTicks;
    public int field_6958;
    protected boolean inAir;
    protected SimpleInventory items;
    protected int temper;
    protected float jumpStrength;
    private boolean jumping;
    private float eatingGrassAnimationProgress;
    private float lastEatingGrassAnimationProgress;
    private float angryAnimationProgress;
    private float lastAngryAnimationProgress;
    private float eatingAnimationProgress;
    private float lastEatingAnimationProgress;
    protected boolean playExtraHorseSounds = true;
    protected int soundTicks;


    public BroomEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean isInAir() {
        return this.inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public double getJumpStrength() {
        return this.getAttributeValue(EntityAttributes.HORSE_JUMP_STRENGTH);
    }

    @Override
    @Nullable
    public Entity getPrimaryPassenger() {
        return this.getPassengerList().isEmpty() ? null : (Entity)this.getPassengerList().get(0);
    }

    @Override
    public boolean canWalkOnFluid(Fluid fluid) {
        return FluidTags.WATER.contains(fluid);
    }

    @Override
    public void travel(Vec3d movementInput) {

        // pig
        if (!this.isAlive()) {
            Entity entity2 = this.getPassengerList().isEmpty() ? null : (Entity)this.getPassengerList().get(0);
            if (this.hasPassengers() && this.canBeControlledByRider() && entity2 instanceof PlayerEntity) {
                this.yaw = entity2.yaw;
                this.prevYaw = this.yaw;
                this.pitch = entity2.pitch * 0.5F;
                this.setRotation(this.yaw, this.pitch);
                this.bodyYaw = this.yaw;
                this.headYaw = this.yaw;
                this.stepHeight = 1.0F;
                this.flyingSpeed = this.getMovementSpeed() * 0.1F;

                if (this.isLogicalSideForUpdatingMovement()) {
                    float f = (float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * 0.225F;

                    this.setMovementSpeed(f);
                    super.travel(new Vec3d(0.0D, 0.0D, 1.0D));
//                    entity.bodyTrackingIncrements = 0;
                } else {
                    this.method_29242(this, false);
                    this.setVelocity(Vec3d.ZERO);
                }
            } else {
                this.stepHeight = 0.5F;
                this.flyingSpeed = 0.02F;
                super.travel(movementInput);
            }
        }


        // horse
//        if (this.isAlive()) {
//            if (this.hasPassengers() && this.canBeControlledByRider() && this.isSaddled()) {
//                LivingEntity livingEntity = (LivingEntity)this.getPrimaryPassenger();
//                assert livingEntity != null;
//                this.yaw = livingEntity.yaw;
//                this.prevYaw = this.yaw;
//                this.pitch = livingEntity.pitch * 0.5F;
//                this.setRotation(this.yaw, this.pitch);
//                this.bodyYaw = this.yaw;
//                this.headYaw = this.bodyYaw;
//                float f = livingEntity.sidewaysSpeed * 0.5F;
//                float g = livingEntity.forwardSpeed;
//                if (g <= 0.0F) {
//                    g *= 0.25F;
//                    this.soundTicks = 0;
//                }
//
//                if (this.onGround && this.jumpStrength == 0.0F && !this.jumping) {
//                    f = 0.0F;
//                    g = 0.0F;
//                }
//
//                if (this.jumpStrength > 0.0F && !this.isInAir() && this.onGround) {
//                    double d = this.getJumpStrength() * (double)this.jumpStrength * (double)this.getJumpVelocityMultiplier();
//                    double h;
//                    if (this.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
//                        h = d + (double)((float)(this.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
//                    } else {
//                        h = d;
//                    }
//
//                    Vec3d vec3d = this.getVelocity();
//                    this.setVelocity(vec3d.x, h, vec3d.z);
//                    this.setInAir(true);
//                    this.velocityDirty = true;
//                    if (g > 0.0F) {
//                        float i = MathHelper.sin(this.yaw * 0.017453292F);
//                        float j = MathHelper.cos(this.yaw * 0.017453292F);
//                        this.setVelocity(this.getVelocity().add((double)(-0.4F * i * this.jumpStrength), 0.0D, (double)(0.4F * j * this.jumpStrength)));
//                    }
//
//                    this.jumpStrength = 0.0F;
//                }
//
//                this.flyingSpeed = this.getMovementSpeed() * 0.1F;
//                if (this.isLogicalSideForUpdatingMovement()) {
//                    this.setMovementSpeed((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
//                    super.travel(new Vec3d((double)f, movementInput.y, (double)g));
//                } else if (livingEntity instanceof PlayerEntity) {
//                    this.setVelocity(Vec3d.ZERO);
//                }
//
//                if (this.onGround) {
//                    this.jumpStrength = 0.0F;
//                    this.setInAir(false);
//                }
//
//                this.method_29242(this, false);
//            } else {
//                this.flyingSpeed = 0.02F;
//                super.travel(movementInput);
//            }
//        }
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
        return this.getPrimaryPassenger() instanceof LivingEntity;
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if(!player.getEntityWorld().isClient && player.getStackInHand(hand)==ItemStack.EMPTY && hand==Hand.MAIN_HAND)  //todo: changed
        {
            player.startRiding(this,true);
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


    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean canBeSaddled() {
        return true;
    }

    @Override
    public void saddle(@Nullable SoundCategory sound) {
        this.items.setStack(0, new ItemStack(Items.SADDLE));
        if (sound != null) {
            this.world.playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_HORSE_SADDLE, sound, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return true;
    }
}
