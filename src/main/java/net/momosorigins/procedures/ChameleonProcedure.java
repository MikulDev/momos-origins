package net.momosorigins.procedures;

import net.momosorigins.MomosOriginsMod;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class ChameleonProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency entity for procedure Chameleon!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.isSneaking())) {
			entity.getPersistentData().putDouble("timer", ((entity.getPersistentData().getDouble("timer")) + 1));
		} else {
			entity.getPersistentData().putDouble("timer", 0);
		}
		if (((entity.getPersistentData().getDouble("timer")) >= 40)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, (int) 2, (int) 0, (false), (false)));
		}
	}
}
