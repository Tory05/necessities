package kdx7214.necessities ;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.IPlayerTracker ;

public class PlayerTracker implements IPlayerTracker {

	NBTTagCompound data ;
	
	public PlayerTracker(NBTTagCompound nd) {
		data = nd ;
	}
	
	@Override
	public void onPlayerLogin(EntityPlayer player) {
		String motd = data.getString("[MOTD]") ;
		MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
		String fname = server.getFolderName() ;

		if (new File(fname).exists() == false) 
			fname = "saves/" + fname ;

		fname = fname + "/players/" ;
		fname = fname + player.username ;
		fname = fname + ".dat" ;
		
		// Should only get here if a brand new user
		if (new File(fname).exists() == false && data.hasKey("[Spawn]")) {
			 NBTTagCompound spawn = data.getCompoundTag("[Spawn]") ;
			 double x = spawn.getDouble("PosX") ;
			 double y = spawn.getDouble("PosY") ;
			 double z = spawn.getDouble("PosZ") ;
			 float yaw = spawn.getFloat("Yaw") ;
			 float pitch = spawn.getFloat("Pitch") ;
			 int dim = spawn.getInteger("Dim") ;

			 if (((EntityPlayer)player).dimension != dim)
		    		server.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)player, dim);

			// player.setPositionAndUpdate(x,  y,  z) ;
			 EntityPlayerMP playerMP = (EntityPlayerMP) player ;
		     playerMP.playerNetServerHandler.setPlayerLocation(x, y, z, yaw, pitch) ;
		}
		
		if (motd.length() > 0)
			player.sendChatToPlayer(motd) ;

	} // public void onPlayerLogin(...)

	@Override
	public void onPlayerLogout(EntityPlayer player) {

    	NBTTagCompound playerdata = data.getCompoundTag(player.username) ;
    	data.setCompoundTag(player.username, playerdata) ;

    	long now = System.currentTimeMillis() ;
    	playerdata.setLong("[Seen]",  now) ;
    	
    	if (playerdata.hasKey("[Worldedit]")) {
    		playerdata.removeTag("[Worldedit]") ;
    	}
	} // public void onPlayerLogout(...)

	
	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		//System.out.println("[Necessities] Player " + player.username + " changed dimensions") ;
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		//System.out.println("[Necessities] Player " + player.username + " respawned") ;
	}

 	
	
}
