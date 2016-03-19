package code.op.sb;

import org.bukkit.entity.Player;
import code.op.CPManager;
import code.op.CarbynePlayer;
import code.op.Main;

public class CGScoreboard {
	
	private CPManager cpm;
	
	public CGScoreboard(Main plugin, CPManager cpm) {
		this.cpm = cpm;
	}
	
	public void tick() {
		for (CarbynePlayer p : cpm.getPlayers()) {
			Player pl = p.getP();
			pl.setScoreboard(p.getBoard());
			p.getObj().getScore("Charges").setScore(p.getSpecialCount());
			p.getObj().getScore("Health").setScore((int) pl.getHealth());
		}
	}
}
