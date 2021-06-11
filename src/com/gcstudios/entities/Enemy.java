package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
	public int vida = 3;
	
	private int framesAnimation = 0;
	private int maxFrames = 15;
	
	private int maxSprite = 2;
	private int curSprite = 0;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(World.isFree((int)x,(int)(y+1))) {
			y+=speed;
		}else {
		
		if(right) {
			if(World.isFree((int)(x+speed), (int)y)) {
			x+=speed;
			if(World.isFree((int)(x+16),(int)y+1)) {
				right = false;
				left = true;
			}
			}else {
				right = false;
				left = true;
			}
		}
		
		if(left) {
			if(World.isFree((int)(x-speed), (int)y)) {
				x-=speed;
			if(World.isFree((int)(x-16),(int)y+1)) {
				right = true;
				left = false;
			}
			}else {
				right = true;
				left = false;
			}
		}
		}
		
		if(vida == 0) {
			Game.entities.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		framesAnimation++;
		if(framesAnimation == maxFrames) {
			curSprite++;
			framesAnimation = 0;
			if(curSprite == maxSprite) {
				curSprite = 0;
			}
		}
		if(right)
			sprite = Entity.ENEMY1_RIGHT[curSprite];
		else if(left)
			sprite = Entity.ENEMY1_LEFT[curSprite];
		
		super.render(g);
	}

}
