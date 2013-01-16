package kdx7214.necessities;

import java.util.Collection;
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

public class CommandWarp extends CommandBaseNecessities {

	public CommandWarp() {
	}
		
    public String getCommandName()
    {
        return "warp";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <warpname>";
    }

    public void processCommand(ICommandSender sender, String[] var2)
    {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	EntityPlayerMP playerMP = (EntityPlayerMP) sender ;
    	
    	if (var2.length != 1) {
    		sender.sendChatToPlayer("Usage:  /" + getCommandName() + " <warpname>") ;
    		return ;
    	}

    	setBackLocation(player) ;
    	
    	NBTTagCompound warps = NecessitiesMain.instance.necessities_data.getCompoundTag("[Warps]") ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag("[Warps]", warps) ;

    	if (!warps.hasKey(var2[0])) {
    		sender.sendChatToPlayer("Unknown warp \"" + var2[0] + "\"") ;
    		return ;
    	}
    	
    	NBTTagCompound w = warps.getCompoundTag(var2[0]) ;
    	double posX = w.getDouble("PosX") ;
    	double posY = w.getDouble("PosY") ;
    	double posZ = w.getDouble("PosZ") ;
    	float yaw = w.getFloat("Yaw") ;
    	float pitch = w.getFloat("Pitch") ;
    	int dim = w.getInteger("Dim") ;
    	
    	if (player.dimension != dim)
    		server.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player), dim);

       	playerMP.playerNetServerHandler.setPlayerLocation(posX, posY, posZ, yaw, pitch) ;

    } // public voice processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.warp"))
			return true ;
		else
			return false ;
    } // public boolean canCommandSenderUseCommand(...)


} // public class CommandHome


