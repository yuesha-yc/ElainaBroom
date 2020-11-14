package com.teammoeg.elainabroom.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class BroomEntityModel extends EntityModel<BroomEntity> {
    
    private final ModelPart bb_main;
    private final ModelPart straw6_r1;
    private final ModelPart straw5_r1;
    private final ModelPart straw5_r2;
    private final ModelPart straw4_r1;
    private final ModelPart straw4_r2;
    private final ModelPart straw3_r1;
    private final ModelPart straw2_r1;
    private final ModelPart straw1_r1;

	public BroomEntityModel() {
            textureWidth = 16;
            textureHeight = 16;

            bb_main = new ModelPart(this);
            bb_main.setPivot(0.0F, 24.0F, 0.0F);
            bb_main.setTextureOffset(0, 0).addCuboid(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 32.0F, 0.0F, false);

            straw6_r1 = new ModelPart(this);
            straw6_r1.setPivot(-1.0F, 0.0F, 24.0F);
            bb_main.addChild(straw6_r1);
            setAngles(straw6_r1, 0.0F, 0.7854F, -0.7854F);
            straw6_r1.setTextureOffset(0, 0).addCuboid(-11.0F, 0.0F, 0.0F, 12.0F, 1.0F, 1.0F, 0.0F, true);

            straw5_r1 = new ModelPart(this);
            straw5_r1.setPivot(-1.0F, 0.0F, 24.0F);
            bb_main.addChild(straw5_r1);
            setAngles(straw5_r1, 0.0F, 0.7854F, 0.7854F);
            straw5_r1.setTextureOffset(0, 0).addCuboid(-11.0F, -1.0F, 0.0F, 12.0F, 1.0F, 1.0F, 0.0F, true);

            straw5_r2 = new ModelPart(this);
            straw5_r2.setPivot(1.0F, 0.0F, 24.0F);
            bb_main.addChild(straw5_r2);
            setAngles(straw5_r2, 0.0F, -0.7854F, 0.7854F);
            straw5_r2.setTextureOffset(0, 0).addCuboid(-1.0F, 0.0F, 0.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);

            straw4_r1 = new ModelPart(this);
            straw4_r1.setPivot(1.0F, 0.0F, 24.0F);
            bb_main.addChild(straw4_r1);
            setAngles(straw4_r1, 0.0F, -0.7854F, -0.7854F);
            straw4_r1.setTextureOffset(0, 0).addCuboid(-1.0F, -1.0F, 0.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);

            straw4_r2 = new ModelPart(this);
            straw4_r2.setPivot(0.0F, 1.0F, 24.0F);
            bb_main.addChild(straw4_r2);
            setAngles(straw4_r2, 0.7854F, 0.0F, 0.0F);
            straw4_r2.setTextureOffset(0, 0).addCuboid(-1.0F, 0.0F, 0.0F, 2.0F, 11.0F, 1.0F, 0.0F, false);

            straw3_r1 = new ModelPart(this);
            straw3_r1.setPivot(0.0F, -1.0F, 24.0F);
            bb_main.addChild(straw3_r1);
            setAngles(straw3_r1, -0.7854F, 0.0F, 0.0F);
            straw3_r1.setTextureOffset(0, 0).addCuboid(-1.0F, -11.0F, 0.0F, 2.0F, 12.0F, 1.0F, 0.0F, false);

            straw2_r1 = new ModelPart(this);
            straw2_r1.setPivot(0.0F, 0.0F, 24.0F);
            bb_main.addChild(straw2_r1);
            setAngles(straw2_r1, 0.0F, 0.7854F, 0.0F);
            straw2_r1.setTextureOffset(0, 0).addCuboid(0.0F, -1.0F, 0.0F, 1.0F, 2.0F, 12.0F, 0.0F, true);

            straw1_r1 = new ModelPart(this);
            straw1_r1.setPivot(0.0F, 0.0F, 24.0F);
            bb_main.addChild(straw1_r1);
            setAngles(straw1_r1, 0.0F, -0.7854F, 0.0F);
            straw1_r1.setTextureOffset(0, 0).addCuboid(-1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 12.0F, 0.0F, false);
    }

    @Override
    public void setAngles(BroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bb_main.render(matrixStack, vertices, packedLight, packedOverlay);
    }

    public void setAngles(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.pivotX = x;
        ModelPart.pivotY = y;
        ModelPart.pivotZ = z;
    }
}