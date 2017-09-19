package cjminecraft.engine.loaders;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL;

import cjminecraft.engine.Engine;

public class TextureLoader {

	public static final int BYTES_PER_PIXEL = 4;

	private static List<Integer> textures = new ArrayList<Integer>();

	private static int loadTexture(BufferedImage image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int pixel = pixels[y * image.getHeight() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component.
															// Only for RGBA
			}
		}
		buffer.flip();

		int textureId = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureId);
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_LOD_BIAS, 0f);
		if (GL.getCapabilities().GL_EXT_texture_filter_anisotropic) {
			float amount = Math.min(4F, glGetFloat(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));
			glTexParameterf(GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, amount);
		} else {
			System.out.println("Anisotropic is not supported!");
		}
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
				buffer);
		textures.add(textureId);
		return textureId;
	}

	public static int loadTexture(String fileName, int x, int y, int width, int height) throws IOException {
		BufferedImage texture = ImageIO.read(new File(Engine.getOption("textures.location") + "/" + fileName + ".png"));
		return loadTexture(texture.getSubimage(x, y, width, height));
	}

	public static int loadTexture(String fileName, int x, int y) throws IOException {
		BufferedImage texture = ImageIO.read(new File(Engine.getOption("textures.location") + "/" + fileName + ".png"));
		return loadTexture(texture.getSubimage(x, y, texture.getWidth() - x, texture.getHeight() - y));
	}
	
	public static int loadTexture(String fileName) throws IOException {
		return loadTexture(ImageIO.read(new File(Engine.getOption("textures.location") +"/" + fileName + ".png")));
	}

}
