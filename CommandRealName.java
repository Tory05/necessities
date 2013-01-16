package kdx7214.necessities;

import java.util.List;

import cpw.mods.fml.common.Loader;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandRealName extends CommandBaseNecessities {

	public CommandRealName() {
	}
	
	@Override
	public String getCommandName() {
		return "realname" ;
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/" + getCommandName() + " <nickname>";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(var1) ; 

    	if (var2.length != 1) {
    		var1.sendChatToPlayer("Usage:  /" + getCommandName() + " <nickname>") ;
    		return ;
    	}
    	
//    	NBTTagCompound playerdata = data.getCompoundTag(var2[0]) ;

    	EntityPlayerMP p ;
    	for (int i = 0; i < MinecraftServer.getServer().getConfigurationManager().playerEntityList.size(); ++i)
        {
            p = (EntityPlayerMP)MinecraftServer.getServer().getConfigurationManager().playerEntityList.get(i);
            NBTTagCompound pd = NecessitiesMain.instance.necessities_data.getCompoundTag(p.username) ;
            String nick = pd.getString("Nick") ;
            
            if (var2[0].equalsIgnoreCase(stripcodes(nick)))
            {
                var1.sendChatToPlayer("Realname for  \"" + var2[0] + "\" is \"" + p.username + "\"") ;
                return ;
            }
        }    	
    	
    	var1.sendChatToPlayer("No user with the nickname \"" + var2[0] + "\" was found.") ;
    	
	} // public void processCommand(...)

	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.realname"))
			return true ;
		else
			return false ;
    } // public boolean canCommandSenderUseCommand(...)

	
	public String stripcodes(String in) {
		String out = new String() ;
		
		for (int i = 0; i < in.length(); ++i) {
			if (in.charAt(i) != '&')
				out += in.charAt(i) ;
			else {
				++i ;
				continue ;			
			} // if (...)
			
		} // for (...)

		return out ;
				
	} // public String stripcodes(...)
	

}
