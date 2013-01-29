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

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
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

public class CommandReport extends CommandBaseNecessities {

	public CommandReport() {
	}
		
    public String getCommandName()
    {
        return "report";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

    public List getCommandAliases()
    {
        return null;
    }

    public void processCommand(ICommandSender sender, String[] var2)
    {

    	for (int i = 0; i < 4096; ++i) {
    		if (Item.itemsList[i] != null)
    			System.out.println("Item[" + i + "] == " + Item.itemsList[i].getItemName()) ;
    		
    		//if (Block.blocksList[i] != null)
    		//	System.out.println("Block[" + i + "] == " + Block.blocksList[i].getBlockName()) ;
    	}
    	
    	
    	
    	
    	
    	
    	/*
    	URL url;
    	HttpURLConnection conn;
    	BufferedReader rd;
    	String line;
    	String result = "";
    	try {
    			url = new URL("http://status.mojang.com/check");
    			conn = (HttpURLConnection) url.openConnection();
    			conn.setRequestMethod("GET");
    			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    			while ((line = rd.readLine()) != null) {
    				result += line;
    			}
    			rd.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	sender.sendChatToPlayer(result) ;
    	*/

    	/*
    	try {
    		PrintStream out = new PrintStream(new FileOutputStream("blocks.txt")) ;
    	   	for (int i = 0; i < 4096; ++i) {
        		if (Block.blocksList[i] != null) {
        			out.println("Block[" + i + "] == " + Block.blocksList[i]) ;
        			//out.println("Block[" + i + "] == " + Block.blocksList[i].getClass().getSimpleName()) ;
        		}
        	}
    	   	out.close() ;

    	   	out = new PrintStream(new FileOutputStream("items.txt")) ;
    	   	for (int i = 0; i < 32000; ++i) {
    	   		if (Item.itemsList[i] != null) {
    	   			out.println("Item[" + i + "] == " + Item.itemsList[i]) ;
    	   		}
    	   	}
    	   	out.close();
    	   	
    	}
    	catch (Exception e) {
    		// give up
    	}
    	*/
    	
    } // public void processCommand(...)
		
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.report", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandReport

