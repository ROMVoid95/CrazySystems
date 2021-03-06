package com.crazypanda.crazysystems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.crazypanda.crazysystems.init.Planets;
import com.crazypanda.crazysystems.init.SolarSystems;
import com.crazypanda.crazysystems.proxy.Proxy;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 */
@Mod(
	modid = CrazySystems.MODID,
	name = CrazySystems.NAME,
	version = CrazySystems.VERSION,
	dependencies = "required-after:forge@[14.23.4.2705,)",
	useMetadata = false,
	clientSideOnly = false,
	serverSideOnly = false,
	acceptedMinecraftVersions = "[1.12.2]",
	acceptableRemoteVersions = CrazySystems.VERSION,
	acceptableSaveVersions = "",
	certificateFingerprint = "@FINGERPRINT@",
	modLanguage = "java",
	modLanguageAdapter = "",
	canBeDeactivated = false,
	guiFactory = "",
	updateJSON = "https://github.com/MinecraftModDevelopmentMods/ExampleMod/master/update.json",
	customProperties = {})
public final class CrazySystems {

	/* The Mod's Instance. */
	//@Mod.Instance
	//private static ExampleMod instance = null

	/** ID of this Mod. */
	public static final String MODID = "crazysystems";

	/** Display name of this Mod. */
	public static final String NAME = "CrazySystems";

	/** Version of this Mod. */
//	public static final String VERSION = "0.0.10";
	public static final String VERSION = "@MOD_VERSION@";

	/**
	 *
	 */
	public static final Logger LOGGER = LogManager.getLogger(CrazySystems.MODID);

	/**
	 *
	 */
	private static final class InstanceHolder {

		/**
		 * The Instance.
		 */
		private static final CrazySystems INSTANCE = new CrazySystems();
	}

	/**
	 *
	 * @return The Mod's Instance.
	 */
	@Mod.InstanceFactory
	public static CrazySystems instance() {
		return InstanceHolder.INSTANCE;
	}

	/**
	 *
 	 */
	@SidedProxy(
				clientSide = "com.crazypanda.crazysystems.proxy.ClientProxy",
				serverSide = "com.crazypanda.crazysystems.proxy.ServerProxy")
	private static Proxy proxy = null;

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void onFingerprintViolation(final FMLFingerprintViolationEvent event) {
		// This complains if jar not signed, even if certificateFingerprint is blank
		LOGGER.warn("Invalid Fingerprint");
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void preInit(final FMLPreInitializationEvent event) {
		SolarSystems.init();
		Planets.init();
		proxy.preInit(event);
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void init(final FMLInitializationEvent event) {
		proxy.init(event);
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void receiveIMC(final IMCEvent event) {
		proxy.receiveIMC(event);
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void postInit(final FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static World getWorld() {
		return proxy.getWorld();
	}

	/*
	// Before 1.12
	@Mod.EventHandler
	public static void onRemap(final FMLMissingMappingsEvent event) {
		proxy.onRemap(event);
	}
	*/
}
