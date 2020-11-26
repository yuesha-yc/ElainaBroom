package com.teammoeg.elainabroom.client.render;

import com.teammoeg.elainabroom.client.model.MarisaBroomEntityModel;
import com.teammoeg.elainabroom.entity.MarisaBroomEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class MarisaBroomEntityRenderer extends LivingEntityRenderer<MarisaBroomEntity, MarisaBroomEntityModel> {
    private static final Identifier TEXTURE = new Identifier("elainabroom", "textures/broom.png");

    public MarisaBroomEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new MarisaBroomEntityModel(), 0.7f);
    }

    @Override
    public Identifier getTexture(MarisaBroomEntity entity) {
        return TEXTURE;
    }
}
