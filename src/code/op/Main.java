package code.op;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import code.op.gear.CarbyneListener;
import code.op.gear.GearCommands;
import code.op.gear.GearHandler;
import code.op.sb.CGScoreboard;
import code.op.sb.ScoreboardRunner;

/*
 *  Credit to Frodenkvist for the firstRun and copy methods.
 */

public class Main extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Minecraft");
	
	public static Main instance;
	
	public File gearconfigFile;
	public File storeFile;
	public File duelFile;
	
	public FileConfiguration gearData;
	public FileConfiguration storeData;
	
	private CPManager cpm;
	private GearHandler gh;
	
	public void onEnable() {
		instance = this;
		gearconfigFile = new File(getDataFolder(), "gearconfig.yml");
		storeFile = new File(getDataFolder(), "store.yml");
		duelFile = new File(getDataFolder(), "duel.yml");
		try {
			firstRun();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gearData = YamlConfiguration.loadConfiguration(gearconfigFile);
		storeData = YamlConfiguration.loadConfiguration(storeFile);
		
		GearHandler.load(gearData);
		GearHandler.loadStoreOptions(storeData);
		gh = new GearHandler();
		
		cpm = new CPManager();
		
		registerCommands();
		registerEvents();
		registerTasks();
	}
	
	public void onDisable() {
		
	}
	
	//Registers
	private void registerTasks() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ScoreboardRunner(new CGScoreboard(this, cpm)), 20, 20);
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new CarbyneListener(this), this);
		pm.registerEvents(new CPListeners(this), this);
	}
	
	private void registerCommands() {
		this.getCommand("cg").setExecutor(new GearCommands());
	}
	
	//File methods
	private void firstRun() throws Exception {
	    if(!gearconfigFile.exists()) {
	        gearconfigFile.getParentFile().mkdirs();
	        copy(getResource("gearconfig.yml"), gearconfigFile);
	    }
	    if(!storeFile.exists())
	    {
	        storeFile.getParentFile().mkdirs();
	        copy(getResource("store.yml"), storeFile);
	    }
	    if(!duelFile.exists())
	    {
	        duelFile.getParentFile().mkdirs();
	        copy(getResource("duel.yml"), duelFile);
	    }
	}
	
	private void copy(InputStream in, File file)
	{
	    try
	    {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0)
	        {
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	
	// Getters and Setters
	public static Main getInstance() {
		return Main.instance;
	}

	public CPManager getCpm() {
		return cpm;
	}
	
	public GearHandler getGearHanlder() {
		return gh;
	}
	
}
