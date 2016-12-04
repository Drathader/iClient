package me.ihaq.iClient.utils;

public class TimerUtil {
	private long prevMS = 0;
	private long currentMs = 0;
	private long lastMs = -1;
	private long previousTime;

	public boolean hasDelay(double d) {
		if ((double) (this.getTime() - this.prevMS) >= 1000.0 / d) {
			return true;
		}
		return false;
	}

	public void reset() {
		this.prevMS = this.getTime();
	}

	public long getTime() {
		return System.nanoTime() / 1000000;
	}

	public boolean a(long milliseconds) {
		if (this.getCurrentMS() - this.prevMS >= milliseconds) {
			return true;
		}
		return false;
	}

	public void updateMs() {
		this.currentMs = System.currentTimeMillis();
	}

	public void setLastMs() {
		this.lastMs = System.currentTimeMillis();
	}

	public boolean hasPassed(long Ms) {
		if (this.currentMs > this.lastMs + Ms) {
			return true;
		}
		return false;
	}

	public long getCurrentMS() {
		return System.nanoTime() / 1000000;
	}

	public long getLastMS() {
		return this.lastMs;
	}

	public static long getCurrentTime() {
		return System.nanoTime() / 1000000;
	}

	public boolean hasTimePassedM(long MS) {
		if (this.currentMs >= this.lastMs + MS) {
			return true;
		}
		return false;
	}

	public boolean hasReached(long milliseconds) {
		if (this.getCurrentMS() - this.lastMs >= milliseconds) {
			return true;
		}
		return false;
	}

	public boolean check(float milliseconds) {
		if ((float) (TimerUtil.getCurrentTime() - this.previousTime) >= milliseconds) {
			return true;
		}
		return false;
	}

	public boolean hasPassed(double milli) {
		if ((double) (this.getTime() - this.previousTime) >= milli) {
			return true;
		}
		return false;
	}

	public boolean isDelayComplete(long delay) {
		if (System.currentTimeMillis() - this.lastMs >= delay) {
			return true;
		}
		return false;
	}

	public int convertToMS(int perSecond) {
		return 1000 / perSecond;
	}
}
