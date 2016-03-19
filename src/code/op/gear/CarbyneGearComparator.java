package code.op.gear;

import java.util.Comparator;

public class CarbyneGearComparator implements Comparator<CarbyneGear> {

	@Override
	public int compare(CarbyneGear obj1, CarbyneGear obj2)
	{
		return obj1.getName().compareTo(obj2.getName());
	}
	
}
