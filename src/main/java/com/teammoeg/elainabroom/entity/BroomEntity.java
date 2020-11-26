package com.teammoeg.elainabroom.entity;

import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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

    @Override
    public void travel(Vec3d movementInput) {
        // PigEntity control ? saddledcomponent
        super.travel(movementInput);
    }

    @Override
    public void setMovementInput(Vec3d movementInput) {

        if (this.getPrimaryPassenger() != null && this.getPrimaryPassenger() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) this.getPrimaryPassenger();

            // FlyingEntity travel
            if (this.isTouchingWater()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.800000011920929D));
            } else if (this.isInLava()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.5D));
            } else {
                float f = 0.91F;
                if (this.onGround) {
                    f = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock().getSlipperiness() * 0.91F;
                }

                float g = 0.16277137F / (f * f * f);
                f = 0.91F;
                if (this.onGround) {
                    f = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock().getSlipperiness() * 0.91F;
                }

                // Update the vel, but not y! (Pitch)
                float accair = 0.02F;
                this.updateVelocity(this.onGround ? 0.1F * g : accair, movementInput);
                // Move the entity
                this.move(MovementType.SELF, this.getVelocity());
                // friction!
                this.setVelocity(this.getVelocity().multiply((double)f));
            }

            this.method_29242(this, false);
        }
    }

    @Override
    public void updateVelocity(float speed, Vec3d movementInput) {
        if (this.getPrimaryPassenger() != null && this.getPrimaryPassenger() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) this.getPrimaryPassenger();
            Vec3d vec3d = movementInputToVelocityWithPitch(movementInput, speed, player.yaw, player.pitch, player);
            this.setVelocity(this.getVelocity().add(vec3d));
        }
    }

    private Vec3d movementInputToVelocityWithPitch(Vec3d movementInput, float speed, float yaw, float pitch, PlayerEntity playerEntity) {

        Vec3d playerVec = playerEntity.getVelocity();
        double d = movementInput.lengthSquared();
        if (d < 1.0E-7D) {
            return Vec3d.ZERO;
        } else {
            Vec3d mvinput_vec = movementInput.multiply((double)speed);
//            System.out.println("MULTIPLIED MVINPUT: " + movementInput);
            // Small diff on three dir
            // PI/180 = 0.017453292F
            float f = MathHelper.sin(yaw * 0.017453292F);
            float g = MathHelper.cos(yaw * 0.017453292F);
            float h = MathHelper.sin(pitch * 0.017453292F);
//            System.out.println("PITCH ANGLE: " + h);
            // Calculating a small diff
            Vec3d result_vec3d = new Vec3d(mvinput_vec.x * (double)g - mvinput_vec.z * (double)f, playerVec.y * h, mvinput_vec.z * (double)g + mvinput_vec.x * (double)f);
//            System.out.println("ENTITY VEC DIFF: " + result_vec3d);
            return result_vec3d;
        }


//        float f = (float)(vec3d.getX() - BroomEntity.this.getX());
//        float g = (float)(vec3d.getY() - BroomEntity.this.getY());
//        float h = (float)(vec3d.getZ() - BroomEntity.this.getZ());
//        double d = (double)MathHelper.sqrt(f * f + h * h);
//        double e = 1.0D - (double)MathHelper.abs(g * 0.7F) / d;
//        f = (float)((double)f * e);
//        h = (float)((double)h * e);
//        d = (double)MathHelper.sqrt(f * f + h * h);
//        double i = (double)MathHelper.sqrt(f * f + h * h + g * g);
//
//        float j = BroomEntity.this.yaw;
//        float k = (float)MathHelper.atan2((double)h, (double)f);
//        float l = MathHelper.wrapDegrees(BroomEntity.this.yaw + 90.0F);
//        float m = MathHelper.wrapDegrees(k * 57.295776F);
//        BroomEntity.this.yaw = MathHelper.stepUnwrappedAngleTowards(l, m, 4.0F) - 90.0F;
//        BroomEntity.this.bodyYaw = BroomEntity.this.yaw;
//        if (MathHelper.angleBetween(j, BroomEntity.this.yaw) < 3.0F) {
//            vec3d = MathHelper.stepTowards(vec3d, 1.8F, 0.005F * (1.8F / this.targetSpeed));
//        } else {
//            this.targetSpeed = MathHelper.stepTowards(this.targetSpeed, 0.2F, 0.025F);
//        }
//
//        float n = (float)(-(MathHelper.atan2((double)(-g), d) * 57.2957763671875D));
//        BroomEntity.this.pitch = n;
//        float o = this.yaw + 90.0F;
//        double p = (double)(BroomEntity.this.targetSpeed * MathHelper.cos(o * 0.017453292F)) * Math.abs((double)f / i);
//        double q = (double)(BroomEntity.this.targetSpeed * MathHelper.sin(o * 0.017453292F)) * Math.abs((double)h / i);
//        double r = (double)(BroomEntity.this.targetSpeed * MathHelper.sin(n * 0.017453292F)) * Math.abs((double)g / i);
//
//        BroomEntity.this.setVelocity(vec3d.add((new Vec3d(p, r, q)).subtract(vec3d).multiply(0.2D)));

    }

    // Pig speed
    @Override
    public float getSaddledSpeed() {
        return 1.0f;
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
        if(!player.getEntityWorld().isClient && hand == Hand.MAIN_HAND)
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
        passenger.fallDistance=0;
        if (this.getPrimaryPassenger() instanceof PlayerEntity) {
            if (passenger == this.getPrimaryPassenger()) {
                passenger.updatePosition(this.getX(),this.getY()+0.1,this.getZ());
            } else {
                Vector3f dir = this.getMovementDirection().getOpposite().getUnitVector();
                int vv = this.getPassengerList().indexOf(passenger);
                passenger.updatePosition(this.getPrimaryPassenger().getX()+dir.getX()*vv, this.getPrimaryPassenger().getY()+dir.getY()*vv, this.getPrimaryPassenger().getZ()+dir.getZ()*vv);
            }
        }
    }


}
