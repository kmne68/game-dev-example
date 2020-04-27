package com.kmne68.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	
	private BufferedImage sprite;
	
	
	public SpriteSheet(BufferedImage spriteSheet) {
		
		this.sprite = spriteSheet;
		
	}

	
	public BufferedImage grabImage(int column, int row, int width, int height) {
		
		BufferedImage image = sprite.getSubimage( ( row * 32 ) - 32, ( column * 32 - 32), width, height);
		
		return image;
		
	}
	
}
