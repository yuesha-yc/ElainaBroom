package com.teammoeg.elainabroom.client;

import com.teammoeg.elainabroom.Elainabroom;
import com.teammoeg.elainabroom.entity.BroomEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

/**
 * @author YueSha
 */
@Environment(EnvType.CLIENT)
public class ElainabroomClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Elainabroom.BROOM_ENTITY_TYPE, (entityRenderDispatcher, context) -> new BroomEntityRenderer(entityRenderDispatcher));
    }
}
