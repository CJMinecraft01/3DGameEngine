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

/**
 * The loader for all your textures
 * 
 * @author CJMinecraft
 *
 */
public class TextureLoader {

	/**
	 * The number of bytes of data per pixel. 4 because we use RGBA
	 */
	public static final int BYTES_PER_PIXEL = 4;

	/**
	 * A cache of textures to be removed at the closing of the game
	 */
	private static List<Integer> textures = new ArrayList<Integer>();

	/**
	 * Delete all the textures from the memory
	 */
	public static void cleanUp() {
		for (int texture : textures)
			glDeleteTextures(texture);
	}

	/**
	 * Load the given image
	 * 
	 * @param image
	 *            The image to load. Also works well with
	 *            {@link BufferedImage#getSubimage(int, int, int, int)}
	 * @return The texture id for use with rendering
	 */
	public static int loadTexture(BufferedImage image) {
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

	/**
	 * Load a texture. <strong>Ensure that your launch file contains the option
	 * <code>textures.location</code> as this is the place to start looking for
	 * textures.</strong>. All images must be in the .png format (<i>This is
	 * already added onto the file name for you</i>)
	 * 
	 * @param fileName
	 *            The location to the texture file. This will be in the folder
	 *            from your launch options
	 * @param u
	 *            The u location of the texture to start getting the image from
	 * @param v
	 *            The v location of the texture to start getting the image from
	 * @param width
	 *            The width of the texture (to stop getting it from)
	 * @param height
	 *            The width of the texture (to stop getting it from)
	 * @return The texture id for use with rendering
	 * @throws IOException
	 *             If the texture is not able to be read
	 */
	public static int loadTexture(String fileName, int u, int v, int width, int height) throws IOException {
		BufferedImage texture = ImageIO.read(new File(Engine.getOption("textures.location") + "/" + fileName + ".png"));
		return loadTexture(texture.getSubimage(u, v, width, height));
	}

	/**
	 * Load a texture. <strong>Ensure that your launch file contains the option
	 * <code>textures.location</code> as this is the place to start looking for
	 * textures.</strong>. All images must be in the .png format (<i>This is
	 * already added onto the file name for you</i>)
	 * 
	 * @param fileName
	 *            The location to the texture file. This will be in the folder
	 *            from your launch options
	 * @param u
	 *            The u location of the texture to start getting the image from
	 * @param v
	 *            The v location of the texture to start getting the image from
	 * @return The texture id for use with rendering
	 * @throws IOException
	 *             If the texture is not able to be read
	 */
	public static int loadTexture(String fileName, int u, int v) throws IOException {
		BufferedImage texture = ImageIO.read(new File(Engine.getOption("textures.location") + "/" + fileName + ".png"));
		return loadTexture(texture.getSubimage(u, v, texture.getWidth() - u, texture.getHeight() - v));
	}

	/**
	 * Load a texture. <strong>Ensure that your launch file contains the option
	 * <code>textures.location</code> as this is the place to start looking for
	 * textures.</strong>. All images must be in the .png format (<i>This is
	 * already added onto the file name for you</i>)
	 * 
	 * @param fileName
	 *            The location to the texture file. This will be in the folder
	 *            from your launch options
	 * @return The texture id for use with rendering
	 * @throws IOException
	 *             If the texture is not able to be read
	 */
	public static int loadTexture(String fileName) throws IOException {
		return loadTexture(ImageIO.read(new File(Engine.getOption("textures.location") + "/" + fileName + ".png")));
	}

}
