package net.momosorigins.procedures;

import net.momosorigins.MomosOriginsMod;

import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.Entity;

import java.util.UUID;
import java.util.Map;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.vector.Vector3d;

public class RootedStopMovementProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) 
	{
		UUID ROOTED_ID = UUID.fromString("cd5b5ae9-b2f3-4143-92e5-59413fd850ff");

		LivingEntity entity = (LivingEntity) dependencies.get("entity");
		entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0F);
		entity.setMotion(0, entity.getMotion().y, 0);
	}
}
