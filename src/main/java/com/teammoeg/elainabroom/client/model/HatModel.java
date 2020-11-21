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
    private final ModelPart a6_r1;
    private final ModelPart a5_r1;
    private final ModelPart a4_r1;
    private final ModelPart a3_r1;
    private final ModelPart a2_r1;
    private final ModelPart a1_r1;

    public HatModel(float scale) {
        this(scale, 128, 128);
    }

    public HatModel(float scale, int u, int v) {
        textureWidth = u;
        textureHeight = v;

        hattop = new ModelPart(this);
        hattop.setPivot(0.0F, 24.0F, 0.0F);
        hattop.setTextureOffset(44, 45).addCuboid(-5.0F, -39.0F, -2.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);
        hattop.setTextureOffset(29, 0).addCuboid(-5.0F, -33.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, false);
        hattop.setTextureOffset(26, 41).addCuboid(-4.0F, -37.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

        hatring = new ModelPart(this);
        hatring.setPivot(0.0F, 24.0F, 0.0F);


        a6_r1 = new ModelPart(this);
        a6_r1.setPivot(1.0F, -10.0F, -8.0F);
        hatring.addChild(a6_r1);
        setRotationAngle(a6_r1, 0.0F, 0.0F, 0.0436F);
        a6_r1.setTextureOffset(0, 24).addCuboid(-1.0F, -21.0F, -3.0F, 9.0F, 1.0F, 11.0F, 0.0F, false);

        a5_r1 = new ModelPart(this);
        a5_r1.setPivot(2.0F, -10.0F, 0.0F);
        hatring.addChild(a5_r1);
        setRotationAngle(a5_r1, 0.0F, 0.0F, -0.0436F);
        a5_r1.setTextureOffset(0, 0).addCuboid(-1.0F, -21.0F, 0.0F, 9.0F, 1.0F, 11.0F, 0.0F, false);

        a4_r1 = new ModelPart(this);
        a4_r1.setPivot(3.0F, -10.0F, 7.0F);
        hatring.addChild(a4_r1);
        setRotationAngle(a4_r1, 0.0F, -1.5708F, 0.0F);
        a4_r1.setTextureOffset(29, 12).addCuboid(-3.0F, -22.0F, 0.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

        a3_r1 = new ModelPart(this);
        a3_r1.setPivot(3.0F, -10.0F, -5.0F);
        hatring.addChild(a3_r1);
        setRotationAngle(a3_r1, 0.0F, -1.5708F, 0.0F);
        a3_r1.setTextureOffset(0, 36).addCuboid(-7.0F, -22.0F, 0.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

        a2_r1 = new ModelPart(this);
        a2_r1.setPivot(-3.0F, -10.0F, -8.0F);
        hatring.addChild(a2_r1);
        setRotationAngle(a2_r1, 0.0F, 0.0F, 0.0436F);
        a2_r1.setTextureOffset(0, 12).addCuboid(-8.0F, -21.0F, -3.0F, 9.0F, 1.0F, 11.0F, 0.0F, false);

        a1_r1 = new ModelPart(this);
        a1_r1.setPivot(-2.0F, -10.0F, 0.0F);
        hatring.addChild(a1_r1);
        setRotationAngle(a1_r1, 0.0F, 0.0F, -0.0436F);
        a1_r1.setTextureOffset(29, 29).addCuboid(-8.0F, -21.0F, 0.0F, 9.0F, 1.0F, 11.0F, 0.0F, false);
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
