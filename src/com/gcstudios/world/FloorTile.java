package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FloorTile extends Tile{

	public FloorTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	
	public void render(Graphics g) {
		if(World.cycle == World.morning) {
			g.drawImage(Tile.TILE_SKY, x - Camera.x, y - Camera.y, null);
		}else if(World.cycle == World.afternoon) {
			g.drawImage(Tile.TILE_AFTERNOON, x - Camera.x, y - Camera.y, null);
		}else {
			g.drawImage(Tile.TILE_NIGHT, x - Camera.x, y - Camera.y, null);
		}
	}

}
