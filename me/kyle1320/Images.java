package me.kyle1320;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Images {
	public static BufferedImage get(Class from, String fname) {
		try {
			return ImageIO.read(from.getResourceAsStream(fname));
		} catch (IOException e) {
			return null;
		}
	}

	public static BufferedImage[] getAll(Class from, String... fnames) {
		BufferedImage[] images = new BufferedImage[fnames.length];

		for (int i=0; i < images.length; i++) {
			images[i] = get(from, fnames[i]);
		}

		return images;
	}
}