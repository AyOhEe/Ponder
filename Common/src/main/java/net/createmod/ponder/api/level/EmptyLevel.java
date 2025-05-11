package net.createmod.ponder.api.level;

import java.util.List;

import com.mojang.serialization.Lifecycle;

import net.minecraft.core.*;

import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import org.jetbrains.annotations.Nullable;

import net.createmod.catnip.levelWrappers.DummyLevelEntityGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.ticks.BlackholeTickAccess;
import net.minecraft.world.ticks.LevelTickAccess;

public class EmptyLevel extends Level {
	public EmptyLevel() {
		super(
				null,
				null,
				createDamageTypeAccessRegistry(),
				VanillaRegistries.createLookup().asGetterLookup().lookupOrThrow(Registries.DIMENSION_TYPE).getOrThrow(BuiltinDimensionTypes.OVERWORLD),
				null,
				false,
				false,
				0,
				0
		);
	}

	private static RegistryAccess createDamageTypeAccessRegistry() {
		RegistrySetBuilder registryBuilder = new RegistrySetBuilder()
			.add(Registries.DAMAGE_TYPE, DamageTypes::bootstrap);
		HolderLookup.RegistryLookup<DamageType> damageTypeRegistryLookup = registryBuilder.build(RegistryAccess.EMPTY).lookupOrThrow(Registries.DAMAGE_TYPE);

		Registry<DamageType> damageTypesRegistry = new MappedRegistry<>(Registries.DAMAGE_TYPE, Lifecycle.stable());
		damageTypeRegistryLookup.listElementIds().forEach(
			(key) -> Registry.register(damageTypesRegistry, key, damageTypeRegistryLookup.get(key).orElseThrow().value())
		);

		return new RegistryAccess.ImmutableRegistryAccess(List.of(damageTypesRegistry));
	}

	@Override
	public void sendBlockUpdated(BlockPos blockPos, BlockState blockState, BlockState blockState1, int i) {}

	@Override
	public void playSeededSound(@Nullable Player player, double v, double v1, double v2, Holder<SoundEvent> soundEvent, SoundSource soundSource, float v3, float v4, long l) {}

	@Override
	public void playSeededSound(@Nullable Player player, Entity entity, Holder<SoundEvent> soundEvent, SoundSource soundSource, float v, float v1, long l) {}

	@Override
	public String gatherChunkSourceStats() {
		return "null";
	}

	@Nullable
	@Override
	public Entity getEntity(int i) {
		return null;
	}

	@Nullable
	@Override
	public MapItemSavedData getMapData(String s) {
		return null;
	}

	@Override
	public void setMapData(String s, MapItemSavedData mapItemSavedData) {}

	@Override
	public int getFreeMapId() {
		return 0;
	}

	@Override
	public void destroyBlockProgress(int i, BlockPos blockPos, int i1) {}

	@Override
	public Scoreboard getScoreboard() {
		return null;
	}

	@Override
	public RecipeManager getRecipeManager() {
		return null;
	}

	@Override
	protected LevelEntityGetter<Entity> getEntities() {
		return new DummyLevelEntityGetter<>();
	}

	@Override
	public LevelTickAccess<Block> getBlockTicks() {
		return BlackholeTickAccess.emptyLevelList();
	}

	@Override
	public LevelTickAccess<Fluid> getFluidTicks() {
		return BlackholeTickAccess.emptyLevelList();
	}

	@Override
	public ChunkSource getChunkSource() {
		return null;
	}

	@Override
	public void levelEvent(@Nullable Player player, int i, BlockPos blockPos, int i1) {}

	@Override
	public void gameEvent(GameEvent gameEvent, Vec3 vec3, GameEvent.Context context) {}

	@Override
	public FeatureFlagSet enabledFeatures() {
		return FeatureFlagSet.of();
	}

	@Override
	public float getShade(Direction direction, boolean b) {
		return 0;
	}

	@Override
	public List<? extends Player> players() {
		return List.of();
	}

	@Override
	public Holder<Biome> getUncachedNoiseBiome(int i, int i1, int i2) {
		return null;
	}
}
