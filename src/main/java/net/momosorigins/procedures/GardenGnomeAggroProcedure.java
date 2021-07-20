package net.momosorigins.procedures;

import net.momosorigins.MomosOriginsMod;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;

import java.util.Map;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import java.util.Iterator;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;

public class GardenGnomeAggroProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency entity for procedure GardenGnomeAggro!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency x for procedure GardenGnomeAggro!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency y for procedure GardenGnomeAggro!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency z for procedure GardenGnomeAggro!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency world for procedure GardenGnomeAggro!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		AxisAlignedBB box = new AxisAlignedBB(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10);
		List entities = world.getEntitiesInAABBexcluding(entity, box, null);
		if (!entities.isEmpty())
		{
			Iterator iter = entities.iterator();
			Entity ent;
			while (iter.hasNext())
			{
				ent = (Entity) iter.next();
				if (ent instanceof MobEntity)
				{
					((MobEntity) ent).setAttackTarget((LivingEntity) entity);
				}
			}
		}
	}
}
