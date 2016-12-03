package me.ihaq.iClient.utils;

import java.awt.Color;

public class Colors {
	public static Color getRainbow(long offset, float fade) {
        float hue = (System.nanoTime() + offset) / 5.0E9F % 1.0F;
        long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 1.0F, 1.0F)).intValue()),
                16);
        Color c = new Color((int) color);
        return new Color(c.getRed() / 255.0F * fade, c.getGreen() / 255.0F * fade, c.getBlue() / 255.0F * fade,
                c.getAlpha() / 255.0F);

        }

}
