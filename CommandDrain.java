package kdx7214.necessities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.common.Loader;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class CommandDrain extends CommandBaseNecessities {

	public CommandDrain() {
	}
		
    public String getCommandName()
    {
        return "drain";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <water/lava/oil> <radius>" ;
    }

    public List getCommandAliases()
    {
        return null;
    }

    //
    // The /drain command will recognize three possible liquid names:  water, lava, Oil
    //
    public void processCommand(ICommandSender sender, String[] var2)
    {
    	Map<String, LiquidStack> liquids = LiquidDictionary.getLiquids() ;
    	LiquidStack oil = LiquidDictionary.getLiquid("Oil",  1) ;
    	double radius = 0.0 ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	EntityPlayerMP playerMP = (EntityPlayerMP)sender ;
    	Set<Integer> blocks = new HashSet<Integer>() ;

    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
   		NBTTagCompound we = playerdata.getCompoundTag("[Worldedit]") ;
   		playerdata.setCompoundTag("[Worldedit]",  we) ;
   		int limit ;

   		if (we.hasKey("Limit")) {
   			limit = we.getInteger("Limit") ;
   		} else {
   			limit = Integer.MAX_VALUE ;
   		}
    	
    	// air == block id #0
    	// water == block id #8-9 (9 = stationary)  Block.waterStill Block.waterMoving
    	// lava == block id #10-11 (11 = stationary)  Block.lavaStill Block.lavaMoving

    	if (var2.length != 2) {
    		sender.sendChatToPlayer(getCommandUsage(sender)) ;
    		return ;
    	}

    	if (var2[0].equalsIgnoreCase("water")) {
    		blocks.add(8) ;
    		blocks.add(9) ;
    	} else if (var2[0].equalsIgnoreCase("lava")) {
    		blocks.add(10) ;
    		blocks.add(11) ;
    	} else if (var2[0].equalsIgnoreCase("oil")) {
    		if (oil == null) {
    			sender.sendChatToPlayer("Oil is not registered in the LiquidDictionary.  Please install a mod that adds Oil.") ;
    		} else {
    			blocks.add(oil.itemID) ; 
    			blocks.add(oil.itemID - 1) ;
    		}
    	} else {
    		sender.sendChatToPlayer("Invalid liquid name.") ;
    		return ;
    	}
    	
    	radius = Double.parseDouble(var2[1]) ;
    	if (radius < 1.0 || radius > 100.0) {
    		sender.sendChatToPlayer("Radius must be between 1 and 100") ;
    		return ;
    	}

    	double minX = player.posX - radius ;
    	double maxX = player.posX + radius ;
    	double minY = player.posY - radius ;
    	double maxY = player.posY + radius ;
    	double minZ = player.posZ - radius ;
    	double maxZ = player.posZ + radius ;

    	int count = 0 ;
    	double dist ;
    	
    	for (double i = minX; i < maxX; ++i) {
    		for (double j = minY; j < maxY; ++j) {
    			for (double k = minZ; k < maxZ; ++k) {
    				dist = (player.posX - i) * (player.posX - i) + 
    					   (player.posY - j) * (player.posY - j) +
    					   (player.posZ - k) * (player.posZ - k) ;
    				dist = Math.sqrt(dist) ;
    				
    				if (dist - 0.5 < radius) {  // This means we are within the sphere
    					if (blocks.contains(playerMP.getServerForPlayer().getBlockId((int)i, (int)j, (int)k))) {
    						if (count < limit) {
    							playerMP.getServerForPlayer().setBlock((int)i, (int)j, (int)k, 0) ;
    						}
    						count++ ;
    					} // if (block needs to be changed)
    				} // if (within sphere)
    			} // for (...)
    		} // for (...)
    		
    	} // for (...)
    	
    	sender.sendChatToPlayer("" + count + " blocks changed.") ;
    	
    } // public void processCommand(...)	

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.drain", true, false) ;
    } // public boolean canCommandSenderUseCommand(...)
    
	
} // public class CommandDrain
