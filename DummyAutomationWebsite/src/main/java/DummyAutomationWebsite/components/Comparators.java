package DummyAutomationWebsite.components;

import java.util.Comparator;

public class Comparators {
	public static final Comparator<String> AtoZ = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};
	public static final Comparator<String> ZtoA = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o2.compareTo(o1);
		}
	};
	public static final Comparator<String> LowtoHigh = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			double o1Price = Double.parseDouble(o1.substring(1));
			double o2Price = Double.parseDouble(o2.substring(1));
			return Double.compare(o1Price, o2Price);
		}
	};
	public static final Comparator<String> HightoLow = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			double o1Price = Double.parseDouble(o1.substring(1));
			double o2Price = Double.parseDouble(o2.substring(1));
			return Double.compare(o2Price, o1Price);
		}
	};
}
