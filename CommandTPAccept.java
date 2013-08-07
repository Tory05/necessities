package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandTPAccept extends CommandBaseNecessities {

	public CommandTPAccept() {
	}

	@Override
    public String getCommandName()
    {
        return "tpaccept";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {

		assert(sender instanceof EntityPlayer || sender instanceof EntityPlayerMP) ;
		
		EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	EntityPlayerMP playerMP = (EntityPlayerMP) sender ;
		MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound tpa = playerdata.getCompoundTag("[Tpa]") ;
    	playerdata.setCompoundTag("[Tpa]", tpa) ;
    	String who = tpa.getString("Who") ;
		EntityPlayerMP other = func_82359_c(sender, who) ;


    	if (tpa.hasKey("Command")) { // accepting a /tpa
    		other.addChatMessage(player.username + " has accepted your teleport request.") ;
    		player.addChatMessage("Teleport request from " + who + " accepted.") ;
    		
    		if (player.dimension != other.dimension) {
    			server.getConfigurationManager().transferPlayerToDimension(other, player.dimension) ;
    		}

    		setBackLocation((EntityPlayer)other) ;
    		other.playerNetServerHandler.setPlayerLocation(player.posX, player.posY,  player.posZ,  player.rotationYaw, player.rotationPitch) ;
    		
    	} else { // accepting a /tpahere
    		setBackLocation(player) ;
    	
    		other.addChatMessage(player.username + " has accepted your teleport request.") ;
    		player.addChatMessage("Teleport request from " + who + " accepted.") ;
    		double posX = tpa.getDouble("PosX") ;
    		double posY = tpa.getDouble("PosY") ;
    		double posZ = tpa.getDouble("PosZ") ;
    		float yaw = tpa.getFloat("Yaw") ;
    		float pitch = tpa.getFloat("Pitch") ;
    		int dim = tpa.getInteger("Dim") ;
    	
    		if (player.dimension != dim) {
    			server.getConfigurationManager().transferPlayerToDimension(playerMP, dim) ;
    		}
    	
    		playerMP.playerNetServerHandler.setPlayerLocation(posX, posY, posZ, yaw, pitch) ;
    	} // if (tpa.hasKey("Command"))

    	if (tpa.hasKey("Command")) tpa.removeTag("Command") ;
    	tpa.removeTag("Who") ;
       	tpa.removeTag("PosX") ;
       	tpa.removeTag("PosY") ;
       	tpa.removeTag("PosZ") ;
       	tpa.removeTag("Yaw") ;
       	tpa.removeTag("Pitch") ;
       	tpa.removeTag("Dim") ;
   	
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.tpaccept", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandTPAccept
