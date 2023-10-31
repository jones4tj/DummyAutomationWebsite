package DummyAutomationWebsite.components;

public enum ProductSort {
	AtoZ ("az"),
	ZtoA ("za"),
	LowtoHigh ("lohi"),
	HightoLow ("hilo");
	
	private final String value;
	
	ProductSort(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
