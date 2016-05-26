package code.op;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class CarbynePlayer {

	private Player p;
	private String name;
	private int specialCount;
	
	private ScoreboardManager man;
	private Scoreboard board;
	private Objective obj;
	
	public CarbynePlayer(Player p) {
		this.p = p;
		this.name = p.getName();
		this.specialCount = 0;
		this.man = Bukkit.getScoreboardManager();
		this.board = man.getNewScoreboard();
		this.obj = board.registerNewObjective(name, "dummy");
		this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Health").setScore((int) p.getHealth());
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Charges").setScore(0);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Helmet").setScore(0);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Chestplate").setScore(0);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Leggings").setScore(0);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Boots").setScore(0);
	}

	public void updateArmorDurability(double a, double a2, double a3, double a4) {
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Helmet").setScore((int) a4);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Chestplate").setScore((int) a3);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Leggings").setScore((int) a2);
		this.board.getObjective(DisplaySlot.SIDEBAR).getScore("Boots").setScore((int) a);
	}
	
	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}
	
	public String getName() {
		return name;
	}

	public int getSpecialCount() {
		return specialCount;
	}

	public void setSpecialCount(int specialCount) {
		this.specialCount = specialCount;
	}

	public Scoreboard getBoard() {
		return board;
	}

	public Objective getObj() {
		return obj;
	}
	
}
