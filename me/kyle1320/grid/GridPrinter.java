package me.kyle1320.grid;

import java.awt.Color;
import java.util.function.Function;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GridPrinter {
	public static <T> BufferedImage toImage(Grid<T> grid, Function<T, Color> display) throws IOException {
		BufferedImage out = new BufferedImage(grid.getWidth(), grid.getHeight(), BufferedImage.TYPE_INT_ARGB);

		grid.each((x, y, v) -> out.setRGB(x, y, display.apply(v).getRGB()));

		return out;
	}

	public static <T> Grid<T> fromImage(BufferedImage img, ReadMethod<T> read) throws IOException {
		return new Grid<T>(	img.getWidth(), img.getHeight(),
							(x, y) -> read.read(img.getRGB(x, y)));
	}

	public static <T> void toFile(Grid<T> grid, String filename, String format, Function<T, Color> display) throws IOException {
		BufferedImage out = toImage(grid, display);

		File fout = new File(filename + "." + format);
		fout.mkdirs();
		ImageIO.write(out, format, fout);
	}

	public static <T> Grid<T> fromFile(String filename, ReadMethod<T> read) throws IOException {
		File fin = new File(filename);
		BufferedImage in = ImageIO.read(fin);
		return fromImage(in, read);
	}

	public interface ReadMethod<T> {
		public T read(int r, int g, int b);

		public default T read(int rgb) {
			return read((rgb>>16)&255, (rgb>>8)&255, rgb&255);
		}
	}
}