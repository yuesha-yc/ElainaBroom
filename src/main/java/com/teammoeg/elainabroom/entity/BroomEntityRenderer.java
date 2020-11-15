package com.teammoeg.elainabroom.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BroomEntityRenderer extends MobEntityRenderer<BroomEntity, BroomEntityModel> {
    private static final Identifier TEXTURE = new Identifier("elainabroom", "textures/broom.png");

    public BroomEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new BroomEntityModel(),0.7F);
    }

    @Override
    public Identifier getTexture(BroomEntity entity) {
        return TEXTURE;
    }
}
