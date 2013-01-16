package kdx7214.necessities;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChunkCoordinates;

public class CommandSpawn extends CommandBaseNecessities {
		
	@Override
    public String getCommandName()
    {
        return "spawn";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	if (!NecessitiesMain.instance.necessities_data.hasKey("[Spawn]")) {
    		sender.sendChatToPlayer("No Spawn point has been set.") ;
    		return ;
    	}
    	
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	EntityPlayerMP playerMP = (EntityPlayerMP) sender ;
    	sender.sendChatToPlayer("Returning to spawn point.") ;

    	setBackLocation(player) ;

    	NBTTagCompound spawn = NecessitiesMain.instance.necessities_data.getCompoundTag("[Spawn]") ;
    	double posX = spawn.getDouble("PosX") ;
    	double posY = spawn.getDouble("PosY") ;
    	double posZ = spawn.getDouble("PosZ") ;
    	float yaw = spawn.getFloat("Yaw") ;
    	float pitch = spawn.getFloat("Pitch") ;
    	int dim = spawn.getInteger("Dim") ;
    	
    	if (player.dimension != dim)
    		server.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player), dim);

       	playerMP.playerNetServerHandler.setPlayerLocation(posX, posY, posZ, yaw, pitch) ;

    } // public void processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.spawn"))
			return true ;
		else
			return false ;
    } // public boolean canCommandSenderUseCommand(...)

	
	
	
} // public class CommandSpawn

