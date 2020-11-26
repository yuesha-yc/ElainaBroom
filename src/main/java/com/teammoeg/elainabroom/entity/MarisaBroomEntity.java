package com.teammoeg.elainabroom.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MarisaBroomEntity extends AbstractEntityFromItem {
    private boolean keyForward = false;
    private boolean keyBack = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;

    public MarisaBroomEntity(EntityType<? extends AbstractEntityFromItem> entityType, World world) {
        super(entityType, world);
        setNoGravity(true);
    }

    @Environment(EnvType.CLIENT)
    private static boolean keyForward() {
        return MinecraftClient.getInstance().options.keyForward.isPressed();
    }

    @Environment(EnvType.CLIENT)
    private static boolean keyBack() {
        return MinecraftClient.getInstance().options.keyBack.isPressed();
    }

    @Environment(EnvType.CLIENT)
    private static boolean keyLeft() {
        return MinecraftClient.getInstance().options.keyLeft.isPressed();
    }

    @Environment(EnvType.CLIENT)
    private static boolean keyRight() {
        return MinecraftClient.getInstance().options.keyRight.isPressed();
    }

    @Override
    public double getMountedHeightOffset() {
        return 0.1D;
    }

    /**
     * 与玩家操控扫帚有关系
     */
    @Override
    public void travel(Vec3d vec3d) {
        Entity entity = getPrimaryPassenger();
        if (entity instanceof PlayerEntity && this.hasPlayerRider()) {
            PlayerEntity player = (PlayerEntity) entity;

            // 记得将 fall distance 设置为 0，否则会摔死
            player.fallDistance = 0;
            this.fallDistance = 0;

            // 与旋转有关系的一堆东西，用来控制扫帚朝向
            this.yaw = player.yaw;
            this.pitch = player.pitch;
            this.prevYaw = this.yaw;
            this.serverYaw = this.yaw; // todo: not sure what is this.renderYawOffset
            this.headYaw = this.yaw;
            this.setRotation(this.yaw, this.pitch);

            if (world.isClient) {
                keyForward = keyForward();
                keyBack = keyBack();
                keyLeft = keyLeft();
                keyRight = keyRight();
            }

            // 按键控制扫帚各个方向速度
            float strafe = keyLeft ? 0.5f : (keyRight ? -0.5f : 0);
            float vertical = keyForward ? -(player.pitch - 10) / 45 : 0;
            float forward = keyForward ? 3 : (keyBack ? -0.5f : 0);

            this.updateVelocity(0.02f, new Vec3d(strafe, vertical, forward)); //todo: 我不知道这对不对
            this.move(MovementType.SELF, this.getVelocity());
        } else if (entity == null && !this.hasPlayerRider() && !this.onGround) {
            // 玩家没有坐在扫帚上，那就让它掉下来
            super.travel(new Vec3d(0f, -3.0f, 0f));
        } else {
            super.travel(vec3d);
        }
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if (player.isHolding(Items.NAME_TAG)) {
            return ActionResult.FAIL;
        }
        if(!player.getEntityWorld().isClient && hand == Hand.MAIN_HAND && !this.hasPlayerRider())
        {
            player.startRiding(this,true);
            return ActionResult.SUCCESS;
        }
        return super.interactAt(player, hitPos, hand);
    }

    @Override
    public void updatePassengerPosition(Entity passenger) {
        passenger.fallDistance=0;
        this.fallDistance=0;
//        super.updatePassengerPosition(passenger);
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
