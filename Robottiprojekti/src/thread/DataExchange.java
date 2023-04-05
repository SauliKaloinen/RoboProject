package thread;

public class DataExchange {

	private boolean obstacleDetected = false;
	private int CMD = 1; // follow line
	private boolean dodge = false;

	public DataExchange() {

	}

	public void setObstacleDetected(boolean status) {
		obstacleDetected = status;
	}

	public boolean getObstacleDetected() {
		return obstacleDetected;
	}

	public void setCMD(int command) {
		CMD = command;
	}

	public int getCMD() {
		return CMD;
	}

	public boolean getDodge() {
		return this.dodge;
	}

	// public boolean setDodge(boolean dodge) {
	public void setDodge(boolean dodge) {
		this.dodge = dodge;
	}


	
}
