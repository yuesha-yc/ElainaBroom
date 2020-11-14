package com.teammoeg.elainabroom;

import com.teammoeg.elainabroom.entity.BroomEntity;
import com.teammoeg.elainabroom.item.BroomItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * @author YueSha
 */
public class Elainabroom implements ModInitializer {

    // Tab
    public static final ItemGroup TAB = FabricItemGroupBuilder.build(
            new Identifier("elainabroom", "tab"),
            () -> new ItemStack(Elainabroom.BROOM));

    // Broom entity
    public static final EntityType<BroomEntity> BROOM_ENTITY_TYPE =
            Registry.register(Registry.ENTITY_TYPE, new Identifier("elainabroom","broom")
                    , FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BroomEntity::new).dimensions(EntityDimensions.fixed(4,4)).trackable(100,4).build());

    // Broom spawner item
    public static final Item BROOM = new BroomItem(new Item.Settings().group(TAB));

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(BROOM_ENTITY_TYPE, MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D));
        Registry.register(Registry.ITEM, new Identifier("elainabroom","broom"), BROOM);

    }
}
