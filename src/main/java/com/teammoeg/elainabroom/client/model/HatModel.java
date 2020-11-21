package com.teammoeg.elainabroom.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.util.math.MatrixStack;

public class HatModel extends EntityModel<AbstractClientPlayerEntity> implements ModelWithHat {

    private final ModelPart hattop;
    private final ModelPart hatring;
    private final ModelPart a4_r1;
    private final ModelPart a3_r1;

    public HatModel(float scale) {
        this(scale, 128, 128);
    }

    public HatModel(float scale, int u, int v) {
        textureWidth = u;
        textureHeight = v;

        textureWidth = 128;
        textureHeight = 128;

        hattop = new ModelPart(this);
        hattop.setPivot(-1.3333F, -12.3333F, 0.3333F);
        hattop.setTextureOffset(44, 45).addCuboid(-3.6667F, -4.3333F, -2.3333F, 6.0F, 3.0F, 6.0F, 0.0F, false);
        hattop.setTextureOffset(29, 0).addCuboid(-3.6667F, 1.6667F, -4.3333F, 8.0F, 3.0F, 8.0F, 0.0F, false);
        hattop.setTextureOffset(26, 41).addCuboid(-2.6667F, -2.3333F, -3.3333F, 6.0F, 4.0F, 6.0F, 0.0F, false);

        hatring = new ModelPart(this);
        hatring.setPivot(0.3333F, -8.3333F, 0.6667F);
        hatring.setTextureOffset(29, 29).addCuboid(-11.1806F, -0.1621F, -1.6667F, 9.0F, 1.0F, 11.0F, 0.0F, false);
        hatring.setTextureOffset(0, 12).addCuboid(-10.4794F, -0.4674F, -12.6667F, 9.0F, 1.0F, 11.0F, 0.0F, false);
        hatring.setTextureOffset(0, 0).addCuboid(-0.1872F, -0.4674F, -1.6667F, 9.0F, 1.0F, 11.0F, 0.0F, false);
        hatring.setTextureOffset(0, 24).addCuboid(0.5139F, -0.1621F, -12.6667F, 9.0F, 1.0F, 11.0F, 0.0F, false);

        a4_r1 = new ModelPart(this);
        a4_r1.setPivot(-1.3333F, -0.3333F, 6.3333F);
        hatring.addChild(a4_r1);
        setRotationAngle(a4_r1, 0.0F, -1.5708F, 0.0F);
        a4_r1.setTextureOffset(29, 12).addCuboid(-4.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

        a3_r1 = new ModelPart(this);
        a3_r1.setPivot(-1.3333F, -0.3333F, -9.6667F);
        hatring.addChild(a3_r1);
        setRotationAngle(a3_r1, 0.0F, -1.5708F, 0.0F);
        a3_r1.setTextureOffset(0, 36).addCuboid(-4.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setHatVisible(boolean visible) {
        this.hattop.visible = visible;
        this.hatring.visible = visible;
    }

    @Override
    public void setAngles(AbstractClientPlayerEntity entity, float v, float headRoll, float headYaw, float headPitch, float scale)
    {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha)
    {
        hattop.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        hatring.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.pivotX = x;
        ModelPart.pivotY = y;
        ModelPart.pivotZ = z;
    }
}
