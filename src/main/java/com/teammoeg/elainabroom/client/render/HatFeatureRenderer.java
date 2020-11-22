package com.teammoeg.elainabroom.client.render;

import com.teammoeg.elainabroom.client.model.HatModel;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;

public class HatFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private static final Identifier TEXTURE = new Identifier("elainabroom", "textures/hat.png");

    public HatFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context)
    {
        super(context);
    }

    @Override
    protected Identifier getTexture(AbstractClientPlayerEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if(entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) //todo: add a hat for el
        {

            matrices.push();

            HatModel hatModel = new HatModel(0);
            ((ModelWithHead)this.getContextModel()).getHead().rotate(matrices);

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(hatModel.getLayer(this.getTexture(entity)));
            hatModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1F, 1F, 1F, 1F);

            matrices.pop();

        }

    }
}
