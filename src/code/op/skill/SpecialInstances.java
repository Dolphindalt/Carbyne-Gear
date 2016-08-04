package code.op.skill;

public class SpecialInstances {

	private static final Special FORCEBLAST = new ForceBlast();
	private static final Special LIGHTNINGSTORM = new LightningStorm();
	private static final Special SHADOWSWEEP = new ShadowSweep();
	private static final Special SUMMONHEALING = new SummonHealing();
	private static final Special FIRESTORM = new FireStorm();
	
	public static Special getForceBlast() {
		return FORCEBLAST;
	}
	
	public static Special getLightningStorm() {
		return LIGHTNINGSTORM;
	}
	
	public static Special getShadowSweep() {
		return SHADOWSWEEP;
	}
	
	public static Special getSummonHealing() {
		return SUMMONHEALING;
	}

	public static Special getFirestorm() {
		return FIRESTORM;
	}
	
}
