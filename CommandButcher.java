package kdx7214.necessities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import java.io.*;
import java.net.*;
import com.google.common.collect.ImmutableMap;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

public class CommandButcher extends CommandBaseNecessities {

	public CommandButcher() {
	}
		
    public String getCommandName()
    {
        return "butcher";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <radius> [mobtype]" ;
    }

    public void processCommand(ICommandSender sender, String[] var2)
    {

    	assert(sender instanceof EntityPlayer || sender instanceof EntityPlayerMP) ;
    	
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ;
    	
    	if (var2.length < 1 || var2.length > 2) {
    		player.addChatMessage(getCommandUsage(sender)) ;
    		return ;
    	}
    	
    	double r = Double.parseDouble(var2[0]) ;
    	r *= r ;
    	List mobs = player.worldObj.loadedEntityList ;
    	Iterator it = mobs.iterator() ;
    	int count = 0 ;

    	while (it.hasNext()) {
    		Object t = it.next() ;
    		Entity u = (Entity)t ;
    		double x = player.posX - u.posX ;
    		double y = player.posY - u.posY ;
    		double z = player.posZ - u.posZ ;
    		double dist = x*x + y*y + z*z ;
    		
    		if (dist < r) {
    			if (var2.length == 1 && t instanceof IMob) {
    				u.setDead() ;
    				count++ ;
    			} else if (var2.length == 2) {
    				if (t instanceof EntityPlayer || t instanceof EntityPlayerMP || t instanceof EntityItem)
    					continue ;
    				if (u.getEntityName().contains(var2[1])) {
    					u.setDead() ;
    					count++ ;
    				} // if (t.getEntityName().contains(var2[1]))

    			} // if (...)
    			
    		} // if (dist < r)
    		
    	} // while (it.hasNext())
    	player.addChatMessage("" + count + " mobs destroyed.") ;
    	
    } // public void processCommand(...)
		
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		return hasPermission(sender, "necessities.butcher", true, false) ;
    } // public boolean canCommandSenderUseCommand(...)


} // public class CommandButcher

