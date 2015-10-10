package maxBandwidthPath;

public enum Status {
	UNSEEN(1),FRINGE(2),INTREE(3);
	private int value;
	private Status(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
}
