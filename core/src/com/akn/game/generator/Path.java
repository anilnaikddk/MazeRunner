package com.akn.game.generator;

import com.akn.game.entities.Cell;

import java.util.ArrayList;
import java.util.Optional;


public class Path extends ArrayList<Cell> {

	public Path(){

	}

	public Path(Cell cell){
		add(cell);
	}

	public Path(Path path){
		addAll(path);
	}

	public Optional<Cell> last() {
		return Optional.ofNullable(get(size() - 1));
	}

	//	public int length;
//	private ArrayList<Cell> cellList;
//
//	public Path() {
//		cellList = new ArrayList<>();
//		updateLength();
//	}

//	public Path(Path path) {
//		cellList = new ArrayList<>(path.cellList);
//		updateLength();
//	}
//
//	public Path add(Cell cell) {
//		cellList.add(cell);
//		updateLength();
//		return this;
//	}
//
//	public Optional<Cell> getLast() {
////		cellList.sort(Com);
////		Collections.sort(cellList);
//		return Optional.ofNullable(cellList.get(length - 1));
//	}
//
//	private void updateLength() {
//		length = cellList.size();
//	}
//
//	public Path clear(){
//		cellList.clear();
//		updateLength();
//		return this;
//	}
//
//	@Override
//	public String toString() {
//		List<String> strList = new ArrayList<>();
//		String str = "";
//		for (Cell c : cellList) {
//			str = str + c.xi + "," + c.yi + " -> ";
////			strList.add(c.toString());
//		}
//		return str + " [***]";
//	}
}
