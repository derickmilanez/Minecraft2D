package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.world.Camera;
import com.gcstudios.world.FloorTile;
import com.gcstudios.world.Tile;
import com.gcstudios.world.WallTile;
import com.gcstudios.world.World;

public class Inventory {
	
	public int boxSize = 40;
	public String[] items = {"grass", "earth", "snow", "sand","", "",""};
	public int initialPosition = ((Game.WIDTH * Game.SCALE) / 2) - ((items.length*boxSize) / 2);
	public int selected = 0;
	public boolean isPressed = false;
	public boolean isPlaced = false;
	public int mX, mY;
	
	public void tick() {
		if(isPressed) {
			isPressed = false;
			int mx = (int)mX/3 + Camera.x;
			int my = (int)mY/3 + Camera.y;
			int tilex = mx/16;
			int tiley = my/16;
			
			if(mX >= initialPosition && mX < initialPosition + (boxSize*items.length) && mY >= 5 && mY < boxSize) {
				selected = (int)(mX-initialPosition)/boxSize;
			}
			
			if(World.tiles[tilex+tiley*World.WIDTH].hasBlock && World.tiles[tilex+tiley*World.WIDTH].badrock == false) {
				World.tiles[tilex+tiley*World.WIDTH].hitPoints -= Game.player.attack;
			}
			
			
			if(World.tiles[tilex+tiley*World.WIDTH].hitPoints <= 0) {
				World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16,tiley*16,Tile.TILE_SKY);
			}
			
		}
		
		if(isPlaced) {
			isPlaced = false;
			mX = (int)mX/3 + Camera.x;
			mY = (int)mY/3 + Camera.y;
			int tilex = mX/16;
			int tiley = mY/16;
			if(World.tiles[tilex+tiley*World.WIDTH].hasBlock == false) {
				if(items[selected]=="grass") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_GRASS);
					World.tiles[tilex+tiley*World.WIDTH].hasBlock = true;
					World.tiles[tilex+tiley*World.WIDTH].hitPoints = 3;
				}else if(items[selected]=="earth") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_EARTH);
					World.tiles[tilex+tiley*World.WIDTH].hasBlock = true;
					World.tiles[tilex+tiley*World.WIDTH].hitPoints = 3;
				}else if(items[selected]=="sand") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_SAND);
					World.tiles[tilex+tiley*World.WIDTH].hasBlock = true;
					World.tiles[tilex+tiley*World.WIDTH].hitPoints = 2;
				}else if(items[selected]=="snow") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_SNOW);
					World.tiles[tilex+tiley*World.WIDTH].hasBlock = true;
					World.tiles[tilex+tiley*World.WIDTH].hitPoints = 3;
				}
				
				if(!World.isFree(Game.player.getX(), Game.player.getY())) {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16,tiley*16,Tile.TILE_SKY);
				}
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof Enemy) {
						if(!World.isFree(e.getX(), e.getY())) {
							World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16,tiley*16,Tile.TILE_SKY);
						}
					}
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < items.length; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(initialPosition + (i * boxSize) + 1, 5 , boxSize, boxSize);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(initialPosition + (i * boxSize) + 1, 5, boxSize, boxSize);
			
			if(items[i] == "grass") {
				g.drawImage(Tile.TILE_GRASS, initialPosition + (i * boxSize) + 3, 8, boxSize-5, boxSize-5, null);
			}else if(items[i] == "earth") {
				g.drawImage(Tile.TILE_EARTH, initialPosition + (i * boxSize) + 3, 8, boxSize-5, boxSize-5, null);
			}else if(items[i] == "sand") {
				g.drawImage(Tile.TILE_SAND, initialPosition + (i * boxSize) + 3, 8, boxSize-5, boxSize-5, null);
			}else if(items[i] == "snow") {
				g.drawImage(Tile.TILE_SNOW, initialPosition + (i * boxSize) + 3, 8, boxSize-5, boxSize-5, null);
			}
			
			if(selected == i) {
				g.setColor(Color.red);
				g.drawRect(initialPosition + (i * boxSize), 5, boxSize, boxSize);
			}
		}
		
	}

}
