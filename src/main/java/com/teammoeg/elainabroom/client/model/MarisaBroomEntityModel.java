package com.teammoeg.elainabroom.client.model;

import com.teammoeg.elainabroom.entity.MarisaBroomEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class MarisaBroomEntityModel extends EntityModel<MarisaBroomEntity> {

    private final ModelPart rod;
    private final ModelPart head;

    public MarisaBroomEntityModel() {
        textureWidth = 128;
        textureHeight = 128;

        rod = new ModelPart(this);
        rod.setPivot(0.0F, 24.0F, 0.0F);
        rod.setTextureOffset(0, 28).addCuboid(-3.0F, -2.0F, -10.0F, 3.0F, 3.0F, 25.0F, 0.0F, false);
        rod.setTextureOffset(0, 0).addCuboid(-2.0F, -3.0F, -33.0F, 3.0F, 3.0F, 25.0F, 0.0F, false);
        rod.setTextureOffset(0, 56).addCuboid(-2.0F, -4.0F, 11.0F, 3.0F, 3.0F, 23.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(0.0F, 24.0F, 0.0F);
        head.setTextureOffset(31, 12).addCuboid(-3.0F, -4.0F, -36.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(76, 39).addCuboid(-5.0F, -6.0F, -41.0F, 9.0F, 9.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(0, 28).addCuboid(-5.0F, -6.0F, -58.0F, 9.0F, 9.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(0, 15).addCuboid(-5.0F, -5.0F, -60.0F, 9.0F, 7.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(41, 13).addCuboid(-6.0F, -7.0F, -56.0F, 11.0F, 11.0F, 15.0F, 0.0F, false);
        head.setTextureOffset(39, 69).addCuboid(-7.0F, -6.0F, -55.0F, 13.0F, 9.0F, 13.0F, 0.0F, false);
        head.setTextureOffset(41, 41).addCuboid(-3.0F, -8.0F, -54.0F, 10.0F, 13.0F, 15.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addCuboid(0.0F, -7.0F, -39.0F, 5.0F, 11.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(31, 0).addCuboid(-2.0F, -5.0F, -35.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(51, 0).addCuboid(-4.0F, -6.0F, -34.0F, 8.0F, 9.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(31, 39).addCuboid(-4.0F, -7.0F, -41.0F, 4.0F, 3.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(0, 39).addCuboid(-4.0F, 1.0F, -41.0F, 4.0F, 3.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void setAngles(MarisaBroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, vertices, packedLight, packedOverlay);
        rod.render(matrixStack, vertices, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.pivotX = x;
        ModelPart.pivotY = y;
        ModelPart.pivotZ = z;
    }
}
