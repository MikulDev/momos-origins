package net.momosorigins.procedures;

import net.momosorigins.MomosOriginsMod;

import net.minecraft.world.IWorld;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class RootedStopMovementProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency entity for procedure RootedStopMovement!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency x for procedure RootedStopMovement!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency y for procedure RootedStopMovement!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency z for procedure RootedStopMovement!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency world for procedure RootedStopMovement!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity.isSneaking())) {
			entity.getPersistentData().putDouble("timer", ((entity.getPersistentData().getDouble("timer")) + 1));
		} else {
			entity.getPersistentData().putDouble("timer", 0);
		}
		if (((entity.getPersistentData().getDouble("timer")) >= 40)) {
			world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, (x + ((Math.random() * 2) - 1)), y, (z + ((Math.random() * 2) - 1)), 0, 1, 0);
			if (world.getWorldInfo().getDayTime() % 20 == 0) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SATURATION, (int) 1, (int) 0));
			}
		}
	}
}
