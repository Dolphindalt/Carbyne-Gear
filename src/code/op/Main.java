package code.op;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import SB.ScoreboardManager;

import com.palmergames.bukkit.towny.Towny;

import code.op.gear.CarbyneListener;
import code.op.gear.EffectsTask;
import code.op.gear.GearCommands;
import code.op.gear.GearHandler;
import code.op.misc.DamageListener;
import code.op.sb.ScoreboardCommands;
import code.op.sb.ScoreboardJoinListener;
import code.op.sb.ScoreboardUpdater;
import code.op.skill.crehop.CrehopListener;

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
	
	public static Plugin sbplugin;
	public static ScoreboardManager sbm;
	
	public static Plugin townyPlugin;
	public static Towny towny;
	public static boolean townyEnabled = false;
	
	private CPManager cpm;
	private GearHandler gh;
	
	public void onEnable() {
		instance = this;
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		if (pm.isPluginEnabled("Towny")) {
			townyPlugin = pm.getPlugin("Towny");
			towny = (Towny) townyPlugin;
			townyEnabled = true;
		}
		
		sbplugin = pm.getPlugin("ScoreboardAPI");
		sbm = (ScoreboardManager) sbplugin;
		
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
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, cpm, 1L, 20L);
		
		registerCommands();
		registerEvents(pm);
		registerTasks();
	}
	
	public void onDisable() {
		
	}
	
	//Registers
	private void registerTasks() {
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ScoreboardUpdater(), 1, 5);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new EffectsTask(), 1L, 20L);
	}
	
	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new CarbyneListener(this), this);
		pm.registerEvents(new CPListeners(this), this);
		if (townyEnabled) pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new ScoreboardJoinListener(), this);
		pm.registerEvents(new CrehopListener(cpm), this);
	}
	
	private void registerCommands() {
		this.getCommand("cg").setExecutor(new GearCommands());
		this.getCommand("sb").setExecutor(new ScoreboardCommands());
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
