package me.ihaq.iClient.utils;

import java.awt.Font;
import java.util.Comparator;

import net.minecraft.client.Minecraft;

public class ComparatorStrings implements Comparator<String> {
	//private final FontUtils fu_mods = new FontUtils("Audiowide", Font.PLAIN,18);

	@Override
	public int compare(String o1, String o2) {
		if (o1.length() <= o2.length()) {
			return 1;
		} else if (o1.length() >= o2.length()) {
			return -1;
		} else {
			return 0;
		}
	}
}
