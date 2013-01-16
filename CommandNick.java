package kdx7214.necessities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.Loader;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandNick extends CommandBaseNecessities {

	public boolean bOP ;

	public CommandNick() {
	}
		
    public String getCommandName()
    {
        return "nick" ;
    }

    public String getCommandUsage(ICommandSender sender)
    {
   		return "/" + getCommandName() + " <player> [nickname]" ;
   	} // public String getCommandUsage(...)
    

    public void processCommand(ICommandSender sender, String[] var2)
    {
		if (var2.length < 1 || var2.length > 2) {
			sender.sendChatToPlayer(getCommandUsage(sender)) ;
   			return ;
   		}

		// Check for special case where necessities.nick.self and user put someone else's name in
		if (!sender.getCommandSenderName().equalsIgnoreCase(var2[0])) {
			if (Loader.instance().isModLoaded("MCPermissions") && 
				!NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.nick.others")) {
				sender.sendChatToPlayer("You do not have permission to change nicknames for anyone other than yourself.") ;
				return ;
			}
		}

		// Need to check that player in var2[0] is online and send a notification to them when done
		
   		NBTTagCompound playerdata ;
   		playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(var2[0]) ;
   		NecessitiesMain.instance.necessities_data.setCompoundTag(var2[0], playerdata) ;

   		if (var2.length == 1) { // clear nickname
   			playerdata.removeTag("Nick") ;
   			sender.sendChatToPlayer("Nickname for player " + var2[0] + " has been cleared.") ;
   			return ;
   		} else {
   			if (validNick(var2[1], NecessitiesMain.instance.nickValidCharacters)) {
   				playerdata.setString("Nick",  var2[1]) ;
   				sender.sendChatToPlayer("Nickname for player " + var2[0] + " has been set to " + var2[1]) ;
   				return ;
   			} else {
   				sender.sendChatToPlayer("Invalid Nickname") ;
   			}
   		}

    
    } // public void processCommand(...)

    
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		bOP = false ;
		if (!isPlayer(sender)) {
			sender.sendChatToPlayer("Not a valid console command.") ;
			return false ;
		}

		if (Loader.instance().isModLoaded("MCPermissions")) {
			if (NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.nick.self") ||
				NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.nick.others")) {
				bOP = true ;
			} else {
				bOP = false ;
			}
		} else if (NecessitiesMain.instance.bNickRequiresOp && isOP(sender)) {
			bOP = true ;
		} else {
			bOP = false ;
		}

		return bOP ;
		
    } // public boolean canCommandSenderUseCommand(...)
    
    
    public boolean validNick(String n, String valid) {
    	
    	for (int i = 0; i < n.length(); ++i) {
    		char c ;
    		c = n.charAt(i) ;
    		if (valid.indexOf(c) >= 0 || Character.isAlphabetic(c) || Character.isDigit(c))
    			continue ;
    		else if (c == '&') {
    			if (i + 1 >= n.length())
    				return false ;
    			i++ ;
    			c = n.charAt(i) ;
    			if (Character.isDigit(c) || c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'r')
    				continue ;
    			return false ;
    		} else
    			return false ;
    	}
    	
    	return true ;
    }

    
    
} // public class CommandNick

