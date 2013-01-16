package kdx7214.necessities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.EnumSet;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	NBTTagCompound data ;
	long updateTime ;
	int updateInterval ;
	
	public ServerTickHandler(NBTTagCompound nd, int ui) {
		data = nd ;
		updateInterval = ui * 60 * 1000 ;  // Convert update interval to minutes
		updateTime = System.currentTimeMillis() + updateInterval ;
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
	} // public void tickStart(...)

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.SERVER))) {
			if (System.currentTimeMillis() > updateTime) {
				updateTime += updateInterval ;
				LogHelper.info("Saving Necessities data") ;
				try {
					FileOutputStream fos = new FileOutputStream(getNecessities()) ;
					CompressedStreamTools.writeCompressed(data, fos) ;
					fos.close() ;
				} catch (Exception e1) {
					e1.printStackTrace();
				} // catch
			} // if (...)
        } // if (...)
	} // public void tickEnd(...)

	@Override
	public EnumSet<TickType> ticks() {
		//return EnumSet.of(TickType.PLAYER, TickType.SERVER);
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public String getNecessities() {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	String fname ;
    	
		fname = server.getFolderName() ;
		if (new File(fname).exists() == true) {
		} else {
			fname = "saves/" + server.getFolderName();
		}
		fname = fname + "/Necessities/necessities.dat" ;
		return fname ;
    } // public String getNecessities()
	
	
	
} // public class ServerTickHandler
