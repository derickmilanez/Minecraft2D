package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Tile {
	
	public static BufferedImage TILE_SKY = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_EARTH = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_SNOW = Game.spritesheet.getSprite(0,32,16,16);
	public static BufferedImage TILE_STONE = Game.spritesheet.getSprite(0,48,16,16);
	public static BufferedImage TILE_NIGHT = Game.spritesheet.getSprite(0,64,16,16);
	public static BufferedImage TILE_LEAF = Game.spritesheet.getSprite(0,80,16,16);
	public static BufferedImage TILE_GRASS = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_SAND = Game.spritesheet.getSprite(16,16,16,16);
	public static BufferedImage TILE_BADROCK = Game.spritesheet.getSprite(16,32,16,16);
	public static BufferedImage TILE_AFTERNOON = Game.spritesheet.getSprite(16,48,16,16);
	public static BufferedImage TILE_WOOD = Game.spritesheet.getSprite(16,64,16,16);
	public boolean hasBlock;
	public boolean badrock = false;

	private BufferedImage sprite;
	protected int x,y;
	public int hitPoints = 0;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
