package net.momosorigins.procedures;

import net.momosorigins.potion.TreantPotion;
import net.momosorigins.MomosOriginsMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BoneMealItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class TreantBonemealProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency entity for procedure TreantBonemeal!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency x for procedure TreantBonemeal!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency y for procedure TreantBonemeal!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency z for procedure TreantBonemeal!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MomosOriginsMod.LOGGER.warn("Failed to load dependency world for procedure TreantBonemeal!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double rx = 0;
		double ry = 0;
		double rz = 0;
		if (entity.ticksExisted % 15 == 0) {
			if ((new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == TreantPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity))) {
				rx = (double) ((Math.random() * 10) - 5);
				ry = (double) ((Math.random() * 6) - 3);
				rz = (double) ((Math.random() * 10) - 5);
				if ((((world.getBlockState(new BlockPos((int) (x + rx), (int) ((y + ry) - 1), (int) (z + ry)))).getBlock() == Blocks.FARMLAND
						.getDefaultState().getBlock())
						|| (BlockTags.getCollection().getTagByID(new ResourceLocation(("minecraft:saplings").toLowerCase(java.util.Locale.ENGLISH)))
								.contains((world.getBlockState(new BlockPos((int) (x + rx), (int) (y + ry), (int) (z + ry)))).getBlock())))) {
					if (world instanceof World) {
						if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), (World) world,
								new BlockPos((int) (x + rx), (int) (y + ry), (int) (z + ry)))
								|| BoneMealItem.growSeagrass(new ItemStack(Items.BONE_MEAL), (World) world,
										new BlockPos((int) (x + rx), (int) (y + ry), (int) (z + ry)), (Direction) null)) {
							if (!world.isRemote())
								((World) world).playEvent(2005, new BlockPos((int) (x + rx), (int) (y + ry), (int) (z + ry)), 0);
						}
					}
				}
			}
		}
	}
}
