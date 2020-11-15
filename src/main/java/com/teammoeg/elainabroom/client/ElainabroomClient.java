package com.teammoeg.elainabroom.client;

import com.teammoeg.elainabroom.Elainabroom;
import com.teammoeg.elainabroom.client.render.BroomEntityRenderer;
import com.teammoeg.elainabroom.client.render.HatFeatureRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

/**
 * @author YueSha
 */
@Environment(EnvType.CLIENT)
public class ElainabroomClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Elainabroom.BROOM_ENTITY_TYPE, (entityRenderDispatcher, context) -> new BroomEntityRenderer(entityRenderDispatcher));
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper) -> {
            if (entityRenderer instanceof PlayerEntityRenderer) {
                registrationHelper.register(new HatFeatureRenderer((PlayerEntityRenderer) entityRenderer));
            }
        });
    }
}
