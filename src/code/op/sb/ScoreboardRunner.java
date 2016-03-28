package code.op.sb;

public class ScoreboardRunner implements Runnable {

	private CGScoreboard scoreboard;
	
	public ScoreboardRunner(CGScoreboard sbman) {
		this.scoreboard = sbman;
	}
	
	@Override
	public void run() {
		scoreboard.tick();
	}
	
}
