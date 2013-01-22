package kdx7214.necessities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import cpw.mods.fml.common.Loader;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChunkCoordinates;

public class CommandSetSpawn extends CommandBaseNecessities {

	public CommandSetSpawn() {
	}

    public String getCommandName()
    {
        return "setspawn";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
       	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	NBTTagCompound spawn = NecessitiesMain.instance.necessities_data.getCompoundTag("[Spawn]") ;
    	
    	spawn.setDouble("PosX", player.posX) ;
    	spawn.setDouble("PosY", player.posY) ;
    	spawn.setDouble("PosZ", player.posZ) ;
    	spawn.setFloat("Yaw",  player.rotationYaw) ;
    	spawn.setFloat("Pitch", player.rotationPitch) ;
    	spawn.setInteger("Dim", player.dimension) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag("[Spawn]", spawn) ;

    	player.worldObj.provider.setSpawnPoint((int)player.posX,  (int)player.posY,  (int)player.posZ) ;

    	try {
			FileOutputStream fos = new FileOutputStream(getNecessities()) ;
			CompressedStreamTools.writeCompressed(NecessitiesMain.instance.necessities_data, fos) ;
			fos.close() ;
		} catch (Exception e1) {
			e1.printStackTrace();
		}

    	sender.sendChatToPlayer("New spawn location has been set.") ;
    	
    } // public void processCommand(...)
  	
		
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.setspawn", true, false) ;
    } // public boolean canCommandSenderUseCommand(...)
   
    
}
