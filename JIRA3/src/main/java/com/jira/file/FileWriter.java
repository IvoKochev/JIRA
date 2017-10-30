package com.jira.file;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

public class FileWriter {

	public void avatarWrite(Part filePart, String fileName, int id) throws IOException {
		InputStream fileContent = filePart.getInputStream();
		Image image = ImageIO.read(fileContent);
		BufferedImage bi = this.createResizedCopy(image, 100, 100, false);
		File file = new File("/home/slavi/JiraImages/" + id);
		if (!file.exists()) {
			file.mkdir();
		}
		ImageIO.write(bi, "png", new File("/home/slavi/JiraImages/" + id + "/" + fileName));
	}

	private BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight,
			boolean preserveAlpha) {
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.setComposite(AlphaComposite.Clear);
		g.setComposite(AlphaComposite.Src);
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}
}
