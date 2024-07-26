package br.com.lojavitual.DTO;

import java.io.Serializable;

public class BillingDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean free;
	private boolean database;

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isDatabase() {
		return database;
	}

	public void setDatabase(boolean database) {
		this.database = database;
	}

	@Override
	public String toString() {
		return "BillingDTO [free=" + free + ", database=" + database + "]";
	}
	
	
}
