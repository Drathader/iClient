package me.ihaq.iClient.utils;

import java.awt.Font;
import java.util.Comparator;

public class ComparatorStrings implements Comparator<String> {
	private FontUtils fu_mods = new FontUtils("Audiowide", Font.PLAIN, 18);
	
	@Override 
	public int compare(String o1, String o2) {
	        if (fu_mods.getWidth(o1) <= fu_mods.getWidth(o2)) {
	          return 1;
	        } else if (fu_mods.getWidth(o1) >= fu_mods.getWidth(o2)) {
	          return -1;
	        } else {
	          return 0;
	        }
	}
}

