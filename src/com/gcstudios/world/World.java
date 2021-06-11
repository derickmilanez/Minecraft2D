package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	public static int morning =  0;
	public static int afternoon =  1;
	public static int night =  2;
	public static int cycle = 2;//Entity.rand.nextInt(2);
	
	
	public World(){
		WIDTH = 1000;
		HEIGHT = 200;
		tiles = new Tile[WIDTH*HEIGHT];
		for(int xx = 0; xx < WIDTH; xx++) {
			int initialHeight = Entity.rand.nextInt(100 - 96) + 50;
			for(int yy = 0; yy < HEIGHT; yy++) {
				if(yy == HEIGHT - 1 || xx == WIDTH - 1 || yy == 0 || xx == 0) {
					tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_BADROCK);
					tiles[xx+yy*WIDTH].hasBlock = true;
					tiles[xx+yy*WIDTH].hitPoints = 100;
					tiles[xx+yy*WIDTH].badrock = true;
				}else {
					if(yy >= initialHeight) {
						tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_GRASS);
						tiles[xx+yy*WIDTH].hasBlock = true;
						tiles[xx+yy*WIDTH].hitPoints = 3;
						if(yy > initialHeight) {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_EARTH);
							tiles[xx+yy*WIDTH].hasBlock = true;
							tiles[xx+yy*WIDTH].hitPoints = 3;
						}
						if(yy >= initialHeight + 10) {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_STONE);
							tiles[xx+yy*WIDTH].hasBlock = true;
							tiles[xx+yy*WIDTH].hitPoints = 20;
						}
						
						//EARTH BIOME GENERATION
						if(xx > 200) {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_EARTH);
							tiles[xx+yy*WIDTH].hasBlock = true;
							tiles[xx+yy*WIDTH].hitPoints = 3;
							if(yy > initialHeight) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_EARTH);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 3;
							}
							if(yy >= initialHeight + 10) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_STONE);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 20;
							}
						}
						
						//SNOW BIOME GENERATION
						if(xx > 400) {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_SNOW);
							tiles[xx+yy*WIDTH].hasBlock = true;
							tiles[xx+yy*WIDTH].hitPoints = 3;
							if(yy > initialHeight) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_EARTH);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 3;
							}
							if(yy >= initialHeight + 10) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_STONE);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 20;
							}
						}
						
						//DESERT BIOME GENERATION
						if(xx > 600) {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_SAND);
							tiles[xx+yy*WIDTH].hasBlock = true;
							tiles[xx+yy*WIDTH].hitPoints = 2;
							if(yy > initialHeight) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_SAND);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 2;
							}
							if(yy >= initialHeight + 10) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_STONE);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 20;
							}
						}
						
						//STONE BIOME GENERATION
						if(xx > 800) {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_STONE);
							tiles[xx+yy*WIDTH].hasBlock = true;
							tiles[xx+yy*WIDTH].hitPoints = 20;
							if(yy > initialHeight) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_STONE);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 20;
							}
							if(yy >= initialHeight + 10) {
								tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_STONE);
								tiles[xx+yy*WIDTH].hasBlock = true;
								tiles[xx+yy*WIDTH].hitPoints = 20;
							}
						}
					}else {
						tiles[xx+yy*WIDTH] = new FloorTile(xx*16,yy*16,Tile.TILE_SKY);
					}
				}
			}
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(){
		//TODO: Aplicar método para reiniciar o jogo corretamente.
		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
