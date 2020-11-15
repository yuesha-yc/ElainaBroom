package com.teammoeg.elainabroom.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;

public class HatModel extends EntityModel<AbstractClientPlayerEntity> implements ModelWithHead, ModelWithHat {
    private final ModelPart head;
    private final ModelPart headOverlay;
    private final ModelPart hat;

    public HatModel(float scale) {
        this(scale, 64, 64);
    }

    public HatModel(float scale, int u, int v) {
        this.head = (new ModelPart(this)).setTextureSize(u, v);
        this.head.setPivot(0.0F, 0.0F, 0.0F);
        this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale);
        this.headOverlay = (new ModelPart(this)).setTextureSize(u, v);
        this.headOverlay.setPivot(0.0F, 0.0F, 0.0F);
        this.headOverlay.setTextureOffset(32, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale + 0.5F);
        this.head.addChild(this.headOverlay);
        this.hat = (new ModelPart(this)).setTextureSize(u, v);
        this.hat.setPivot(0.0F, 0.0F, 0.0F);
        this.hat.setTextureOffset(30, 47).addCuboid(-8.0F, -8.0F, -6.0F, 16, 16, 1, scale);
        this.hat.pitch = -1.5707964F;
        this.headOverlay.addChild(this.hat);
    }

    @Override
    public void setHatVisible(boolean visible) {
        this.head.visible = visible;
        this.headOverlay.visible = visible;
        this.hat.visible = visible;
    }

    @Override
    public ModelPart getHead() {
        return head;
    }

    @Override
    public void setAngles(AbstractClientPlayerEntity entity, float v, float headRoll, float headYaw, float headPitch, float scale)
    {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha)
    {
        this.head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
