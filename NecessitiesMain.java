package kdx7214.necessities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraft.nbt.* ;
import net.minecraft.command.* ;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.Configuration;

@Mod(modid = "necessities", name = "Necessities", version = "0.6.7")
@NetworkMod(clientSideRequired = false, serverSideRequired = true)
public class NecessitiesMain {

	public static MinecraftServer server = null ;
	public NBTTagCompound necessities_data = null ;
	public String fname ;
	
	private boolean bEnableBack, bEnableDelHome, bEnableDelWarp, bEnableHome, bEnableListHome, bEnableMods ;
	private boolean bEnableMotd, bEnableNick, bEnableRealName, bEnableSeen, bEnableSetHome, bEnableSetHomeCount ;
	private boolean bEnableSetMotd, bEnableSetSpawn, bEnableSetWarp, bEnableSpawn, bEnableWarp, bEnableWarpList ;
	private boolean bEnableWhoAmI, bEnableListOps, bEnableMemInfo, bEnableDrain, bEnableButcher, bEnableWand ;
	private boolean bEnableRemove, bEnableGm, bEnableTPAHere, bEnableTPDenyAll, bEnableTPReject, bEnableTPAccept ;

	public boolean bNickRequiresOp, bUseNormalBrackets, bEnableWorldEdit, bEnableChatFilter ;

	public int updateInterval ;
	public int WorldEditWandItem ;

	public String nickValidCharacters ;
	
	@Instance("necessities")
	public static NecessitiesMain instance;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load() ;
		
		bEnableMods = config.get("Commands",  "EnableMods",  true).getBoolean(true) ;
		bEnableNick = config.get("Commands",  "EnableNick",  true).getBoolean(true) ;
		bEnableRealName = config.get("Commands", "EnableRealName", true).getBoolean(true) ;
		bEnableSeen = config.get("Commands", "EnableSeen", true).getBoolean(true) ;
		bEnableMotd = config.get("Commands", "EnableMotd", true).getBoolean(true) ;
		bEnableSetMotd = config.get("Commands", "EnableSetMotd", true).getBoolean(true) ;		
		bEnableWhoAmI = config.get("Commands", "EnableWhoAmI", true).getBoolean(true) ;
		bEnableListOps = config.get("Commands", "EnableListOps", true).getBoolean(true) ;
		bEnableMemInfo = config.get("Commands", "EnableMemInfo", true).getBoolean(true) ;
		bEnableDrain = config.get("Commands", "EnableDrain", true).getBoolean(true) ;
		bEnableButcher = config.get("Commands", "EnableButcher", true).getBoolean(true) ;
		bEnableWand = config.get("Commands", "EnableWand", true).getBoolean(true) ;
		bEnableRemove = config.get("Commands", "EnableRemove", true).getBoolean(true) ;
		bEnableGm = config.get("Commands", "EnableGm", true).getBoolean(true) ;
		bEnableTPAHere = config.get("Commands", "EnableTPAHere", true).getBoolean(true) ;
		bEnableTPDenyAll = config.get("Commands", "EnableTPDenyAll", true).getBoolean(true) ;
		bEnableTPReject = config.get("Commands", "EnableTPReject", true).getBoolean(true) ;
		bEnableTPAccept = config.get("Commands", "EnableTPAccept", true).getBoolean(true) ;
		
		bEnableHome = config.get("Commands", "EnableHome", true).getBoolean(true) ;
		bEnableListHome = config.get("Commands", "EnableListHome", true).getBoolean(true) ;
		bEnableDelHome = config.get("Commands", "EnableDelHome", true).getBoolean(true) ;
		bEnableSetHome = config.get("Commands", "EnableSetHome", true).getBoolean(true) ;
		bEnableSetHomeCount = config.get("Commands", "EnableSetHomeCount", true).getBoolean(true) ;
		bEnableBack = config.get("Commands", "EnableBack", true).getBoolean(true) ;
		
		bEnableSpawn = config.get("Commands", "EnableSpawn", true).getBoolean(true) ;
		bEnableSetSpawn = config.get("Commands", "EnableSetSpawn", true).getBoolean(true) ;
		
		bEnableWarp = config.get("Commands", "EnableWarp", true).getBoolean(true) ;
		bEnableSetWarp = config.get("Commands", "EnableSetWarp", true).getBoolean(true) ;
		bEnableDelWarp = config.get("Commands",  "EnableDelWarp",  true).getBoolean(true) ;
		bEnableWarpList = config.get("Commands", "EnableWarpList", true).getBoolean(true) ;
		
		bNickRequiresOp = config.get("Functionality", "NickRequiresOp", false).getBoolean(false) ;
		bUseNormalBrackets = config.get("Functionality", "UseNormalBrackets", false).getBoolean(false) ;
		updateInterval = config.get("Functionality", "AutoSaveInterval", 15).getInt(15) ;
		bEnableWorldEdit = config.get("Functionality", "EnableWorldEdit", true).getBoolean(true) ; 
		WorldEditWandItem = config.get("Funcionality", "WorldEditWandItem", 271).getInt(271) ;
		
		nickValidCharacters = config.get("Functionality", "NickValidCharacters", "_-").value ;
		bEnableChatFilter = config.get("Functionality", "EnableChatFilter", true).getBoolean(true) ;

		if (nickValidCharacters.indexOf('&') >= 0 || nickValidCharacters.indexOf('\"') >= 0) {
			LogHelper.severe("The ampersand (&) and quotation mark (\") characters cannot be used in nicknames.  Using default.") ;
			nickValidCharacters = "_-" ;
		}
		
		config.save() ;
		
	} // PreInit

	@Init
	public void init(FMLInitializationEvent event) {
		
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event) {
		server = ModLoader.getMinecraftServerInstance() ;
		ICommandManager commandManager = server.getCommandManager() ;
		
		//
		// Verify if on SMP or SSP and react accordingly
		//		
		fname = server.getFolderName() ;
		if (new File(fname).exists() == true) {
			LogHelper.info("Running on SMP") ;
		} else {
			LogHelper.info("Running on SSP") ;
			fname = "saves/" + server.getFolderName();
			// return ;
		}

		// Setup the Necessities folder
		fname = fname + "/Necessities" ;
		File f = new File(fname) ;
		if (f.exists() == false) {
			if (f.mkdir() == false) {
				LogHelper.severe("Failed to create Necessities folder.  Aborting.") ;
				return ;
			}
		}
		
		
		// Setup NBT file
		necessities_data = new NBTTagCompound() ;
		try {
			 necessities_data = CompressedStreamTools.readCompressed(new FileInputStream(fname + "/necessities.dat")) ;			
		}
		catch (IOException e) {
            FileOutputStream fos ;
            necessities_data.setInteger("[HomeCount]", 3) ;
            
			try {
				fos = new FileOutputStream(fname + "/necessities.dat") ;
				CompressedStreamTools.writeCompressed(necessities_data, fos) ;
				fos.close() ;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if (Loader.instance().isModLoaded("MCPermissions")) {
			LogHelper.info("MCPermissions detected and being used.") ;
		}
		
		// Register new server commands
		ServerCommandManager serverCommandManager = ((ServerCommandManager) commandManager) ;

		LogHelper.info("Enabling commands") ;
				
		if (bEnableSpawn)			serverCommandManager.registerCommand(new CommandSpawn()) ;
		if (bEnableSetSpawn)		serverCommandManager.registerCommand(new CommandSetSpawn()) ;
		if (bEnableSetHome)			serverCommandManager.registerCommand(new CommandSetHome()) ;
		if (bEnableDelHome)			serverCommandManager.registerCommand(new CommandDelHome()) ;
		if (bEnableListHome)		serverCommandManager.registerCommand(new CommandListHome()) ;
		if (bEnableSetHomeCount)	serverCommandManager.registerCommand(new CommandSetHomeCount()) ;
		if (bEnableHome)			serverCommandManager.registerCommand(new CommandHome()) ;
		if (bEnableBack)			serverCommandManager.registerCommand(new CommandBack()) ;
		if (bEnableMods)			serverCommandManager.registerCommand(new CommandMods()) ;
		if (bEnableSetMotd)			serverCommandManager.registerCommand(new CommandSetMotd()) ;
		if (bEnableMotd)			serverCommandManager.registerCommand(new CommandMotd()) ;
		serverCommandManager.registerCommand(new CommandReport()) ;
		if (bEnableNick)			serverCommandManager.registerCommand(new CommandNick()) ;
		if (bEnableSeen)			serverCommandManager.registerCommand(new CommandSeen()) ;
		if (bEnableRealName)		serverCommandManager.registerCommand(new CommandRealName()) ;
		if (bEnableSetWarp)			serverCommandManager.registerCommand(new CommandSetWarp()) ;
		if (bEnableWarp)			serverCommandManager.registerCommand(new CommandWarp()) ;
		if (bEnableWarpList)		serverCommandManager.registerCommand(new CommandWarpList()) ;
		if (bEnableDelWarp)			serverCommandManager.registerCommand(new CommandDelWarp()) ;
		if (bEnableWhoAmI)			serverCommandManager.registerCommand(new CommandWhoAmI()) ;
		if (bEnableListOps)			serverCommandManager.registerCommand(new CommandListOps()) ;
		if (bEnableMemInfo)			serverCommandManager.registerCommand(new CommandMemInfo()) ;
		if (bEnableGm)				serverCommandManager.registerCommand(new CommandGm()) ;
		if (bEnableTPAHere)			serverCommandManager.registerCommand(new CommandSummon()) ;
		if (bEnableTPDenyAll)		serverCommandManager.registerCommand(new CommandTPDenyAll()) ;
		if (bEnableTPReject)		serverCommandManager.registerCommand(new CommandTPReject()) ;
		if (bEnableTPAccept)		serverCommandManager.registerCommand(new CommandTPAccept()) ;
		
		if (bEnableWorldEdit) {
			if (bEnableDrain)		serverCommandManager.registerCommand(new CommandDrain()) ;
			if (bEnableButcher)		serverCommandManager.registerCommand(new CommandButcher()) ;
			if (bEnableWand)		serverCommandManager.registerCommand(new CommandWand()) ;
			if (bEnableRemove)		serverCommandManager.registerCommand(new CommandRemove()) ;
		}
			
		GameRegistry.registerPlayerTracker(new PlayerTracker(necessities_data)) ;

		if (updateInterval >= 1) {
			TickRegistry.registerTickHandler(new ServerTickHandler(necessities_data, updateInterval), Side.SERVER) ;
		}
		
		// Register for events
		MinecraftForge.EVENT_BUS.register(new LoginEventHandler(necessities_data));
		
	} // serverStarting()
	
	@ServerStopping
	public void serverStopping(FMLServerStoppingEvent event) {
	
		FileOutputStream fos ;
		try {
			fos = new FileOutputStream(fname + "/necessities.dat") ;
			CompressedStreamTools.writeCompressed(necessities_data, fos) ;
			fos.close() ;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	} // public void serverStopping(...)
	

	
}
