package model;

import java.util.ArrayList;
import java.util.List;
import observer.Observable;
import observer.Observer;

public class Model implements Observable
{
	private List<R201> compressorList;
	private List<Observer> observerList = new ArrayList<Observer>();
	private R201 compressor;
	
	public Model() {
		compressorList = new ArrayList<R201>();
		compressorList.add(new R201A());
		compressorList.add(new R201B());
		compressorList.add(new R201C());
		
		compressor = compressorList.get(0);
	}
	
	public void setCompressor(R201 compressor) {
		this.compressor = compressor;
		this.notifyObservers();
	}
	
	public R201 getCompressor() {
		return this.compressor;
	}
	
	public void setCharge(Integer charge) {
		this.compressor.setCharge(charge);
		this.notifyObservers();
	}
	
	public List<R201> getCompressorList() {
		return compressorList;
	}
	
	public void removeCompressor(R201 compressor) {
		compressorList.remove(compressor);
		this.notifyObservers();
	}

	@Override
	public void addObserver(Observer o) {
		observerList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observerList.remove(o);	
	}

	@Override
	public void notifyObservers() {
		for(Observer o : observerList) {
			o.update(compressor);
		}
	}
}
