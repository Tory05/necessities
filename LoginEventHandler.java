package kdx7214.necessities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ChunkEvent;

public class LoginEventHandler {

	NBTTagCompound data ;
	
	public LoginEventHandler(NBTTagCompound nd) {
		data = nd ;
	}

	
	@ForgeSubscribe
	public void onChunkEventLoad(ChunkEvent.Load event) {
		int count = data.getInteger("[Chunks]") + 1 ;
		data.setInteger("[Chunks]", count) ;
	} // public void onChunkEventLoad(...)
	

	@ForgeSubscribe
	public void onChunkEventUnload(ChunkEvent.Unload event) {
		int count = data.getInteger("[Chunks]") ;
		if (count > 1) {
			count-- ;
			data.setInteger("[Chunks]", count) ;
		}
	}
	
	
	@ForgeSubscribe
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if(event.entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)event.entity ;

	    	// Now deal with the NBT stuff
	    	NBTTagCompound playerdata = data.getCompoundTag(player.username) ;
	    	data.setCompoundTag(player.username, playerdata) ;
	    	NBTTagCompound back = playerdata.getCompoundTag("[Back]") ;
			back.setDouble("PosX", player.posX) ;
			back.setDouble("PosY", player.posY) ;
			back.setDouble("PosZ", player.posZ) ;
			back.setFloat("Yaw", player.rotationYaw) ;
			back.setFloat("Pitch", player.rotationPitch) ;
			back.setInteger("Dim", player.dimension);
			playerdata.setCompoundTag("[Back]",  back) ;
		} // if (...)
	} // public void onEntityJoinWorld(...)
	
	
	@ForgeSubscribe
	public void onServerChatEvent(ServerChatEvent event) {
		String line ;
		
		if (!NecessitiesMain.instance.bEnableChatFilter)
			return ;

		if (data.hasKey(event.username)) {
			NBTTagCompound playerdata = data.getCompoundTag(event.username) ;
			if (playerdata.hasKey("Nick")) {
				String nick = playerdata.getString("Nick") ;
				if (nick.isEmpty())
					nick = event.username ;
				line = "<" + colorize(nick) + "\u00a7r> " + colorize(event.message) ;
				return ;
			}
		}
		if (NecessitiesMain.instance.bUseNormalBrackets) {
			line = "<" + event.username + ">  " + colorize(event.message) ;
		} else {
			line = ">" + event.username + "<  " + colorize(event.message) ;
		}
		
		event.component = ChatMessageComponent.func_111077_e(line) ;
		
} // public void onServerChatEvent(...)
	
	
	
	@ForgeSubscribe
	public void onPlayerInteractEvent(PlayerInteractEvent event) {

		if (!NecessitiesMain.instance.bEnableWorldEdit || event.entityPlayer.getCurrentEquippedItem() == null)
			return ;
		
		if (event.entityPlayer.getCurrentEquippedItem().itemID != NecessitiesMain.instance.WorldEditWandItem)
			return ;
		
    	EntityPlayer player = event.entityPlayer ; 
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;

    	// if not found, default to false
    	if (!playerdata.hasKey("[ToggleEditWand]"))
    		return ;

    	if (!playerdata.getBoolean("[ToggleEditWand]"))
    		return ;

   		NBTTagCompound we = playerdata.getCompoundTag("[Worldedit]") ;
   		playerdata.setCompoundTag("[Worldedit]",  we) ;
    	
		if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
			we.setDouble("Pos1.X", player.posX) ;
			we.setDouble("Pos1.Y", player.posY) ;
			we.setDouble("Pos1.Z", player.posZ) ; 
			player.addChatMessage("\u00a7dPosition 1 set to (" + player.posX + ", " + player.posY + ", " + player.posZ + ")") ;
		} else if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
			we.setDouble("Pos2.X", player.posX) ;
			we.setDouble("Pos2.Y", player.posY) ;
			we.setDouble("Pos2.Z", player.posZ) ; 
			player.addChatMessage("\u00a7dPosition 2 set to (" + player.posX + ", " + player.posY + ", " + player.posZ + ")") ;
		} else {
			return ;
		}
		
		event.setCanceled(true) ;
		
	} // public void onPlayerInteractEvent(...)

	
	
	public String colorize(String n) {
		String r = "" ;
		
		for (int i = 0; i < n.length(); ++i) {
			Character c = n.charAt(i) ;
			if (c != '&') {
				r += c ;
				continue ;
			} // if (c != '&')
			
			if (i >= n.length() - 1) {
				return r ;
			}
			
			i++ ;
			c = n.charAt(i) ;
			switch (c) {
			case '0':
				r += "\u00a70" ;
				break ;
			case '1':
				r += "\u00a71" ;
				break ;
			case '2':
				r += "\u00a72" ;
				break ;
			case '3':
				r += "\u00a73" ;
				break ;
			case '4':
				r += "\u00a74" ;
				break ;
			case '5': 
				r += "\u00a75" ;
				break ;
			case '6':
				r += "\u00a76" ;
				break ;
			case '7':
				r += "\u00a77" ;
				break ;
			case '8':
				r += "\u00a78" ;
				break ;
			case '9':
				r += "\u00a79" ;
				break ;
			case 'a':
				r += "\u00a7a" ;
				break ;
			case 'b':
				r += "\u00a7b" ;
				break ;
			case 'c':
				r += "\u00a7c" ;
				break ;
			case 'd':
				r += "\u00a7d" ;
				break ;
			case 'e':
				r += "\u00a7e" ;
				break ;
			case 'f':
				r += "\u00a7f" ;
				break ;
			case 'l':
				r += "\u00a7l" ;
				break ;
			case 'm':
				r += "\u00a7m" ;
				break ;
			case 'n':
				r += "\u00a7n" ;
				break ;
			case 'o':
				r += "\u00a7o" ;
				break ;
			case 'r':
				r += "\u00a7r" ;
				break ;
			default:
				r += c ;
				break ;
			} // switch(c)
		} // for (...)
		
		return r ;
	} // public String colorize(...)
	
	
	
	
} // public class LoginEventHandler
