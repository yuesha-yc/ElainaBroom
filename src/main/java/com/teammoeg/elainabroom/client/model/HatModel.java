package com.teammoeg.elainabroom.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.util.math.MatrixStack;

public class HatModel extends EntityModel<AbstractClientPlayerEntity> implements ModelWithHat {

    private final ModelPart hat;

    public HatModel(float scale) {
        this(scale, 64, 64);
    }

    public HatModel(float scale, int u, int v) {
        this.hat = (new ModelPart(this)).setTextureSize(u, v);
        this.hat.setPivot(0.0F, 0.0F, 0.0F);
        this.hat.setTextureOffset(30, 47).addCuboid(-8.0F, -8.0F, -6.0F, 16, 16, 1, scale);
        this.hat.pitch = -1.5707964F;
    }

    @Override
    public void setHatVisible(boolean visible) {
        this.hat.visible = visible;
    }

    @Override
    public void setAngles(AbstractClientPlayerEntity entity, float v, float headRoll, float headYaw, float headPitch, float scale)
    {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha)
    {
        this.hat.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
