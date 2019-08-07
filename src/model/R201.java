package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class R201
{
	protected Map<Integer, Integer> debitList;
	protected Integer charge;
	protected Image image;
	
	public R201() {
		this.debitList = new TreeMap<Integer, Integer>();
		charge = Integer.valueOf(0);
	}
	
	public void setCharge(Integer charge) {
		this.charge = charge;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public Integer getCharge() {
		return charge;
	}
	
	public Set<Integer> getCharges() {
		return debitList.keySet();
	}
	
	public int getDebit() {
		return debitList.get(this.charge);
	}
	
	public Map<Integer, Integer> getList() {
		return debitList;
	}
}
