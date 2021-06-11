package com.gcstudios.entities;


import java.awt.Graphics;

import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;


public class Player extends Entity{

	
	public boolean right,left;
	public int attack = 1;
	public int dir = 1;
	private double gravity = 0.4;
	private double vspd = 0;
	public double life = 100;
	public double hunger = 100;
	
	public boolean jump = false;
	public boolean attacked = false;
	public boolean isAttacking = false;

	public boolean standing  = false;
	
	private int framesAnimation = 0;
	private int maxFrames = 15;
	
	private int framesAttack = 0;
	private int maxAttack = 5;
	
	private int maxSprite = 2;
	private int curSprite = 0;
	
	public BufferedImage ATTACK_LEFT;
	public BufferedImage ATTACK_RIGHT;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		ATTACK_LEFT = Game.spritesheet.getSprite(32, 32, 16, 16);
		ATTACK_RIGHT = Game.spritesheet.getSprite(48, 32, 16, 16);
	}
	
	public void tick(){
		depth = 2;
		vspd+=gravity;
		this.speed = 1;
		if(!World.isFree((int)x,(int)(y+1)) && jump)
		{
			vspd = -4;
			jump = false;
		}
		
		if(!World.isFree((int)x,(int)(y+vspd))) {
			
			int signVsp = 0;
			if(vspd >= 0)
			{
				signVsp = 1;
			}else  {
				signVsp = -1;
			}
			while(World.isFree((int)x,(int)(y+signVsp))) {
				y = y+signVsp;
			}
			vspd = 0;
		}
		
		y = y + vspd;
		if(right && World.isFree((int)(x+speed), (int)y)) {
			x+=speed;
			dir = 1;
			standing = false;
		}
		else if(left && World.isFree((int)(x-speed), (int)y)) {
			x-=speed;
			dir = -1;
			standing = false;
		}else {
			standing = true;
		}
		
		if(attacked) {
			if(!isAttacking) {
				attacked = false;
				isAttacking = true;
			}
		}
		
		if(isAttacking) {
			framesAttack++;
			if(framesAttack ==  maxAttack) {
				framesAttack = 0;
				isAttacking = false;
			}
		}
		
		collisionEnemy();
		
		Camera.x = Camera.clamp((int)x - Game.WIDTH / 2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT / 2, 0, World.HEIGHT * 16 - Game.HEIGHT);
		
		
	}
	
	public void render(Graphics g){
		framesAnimation++;
		if(framesAnimation == maxFrames) {
			curSprite++;
			framesAnimation = 0;
			if(curSprite == maxSprite) {
				curSprite = 0;
			}
		}
		if(dir == 1) {
			if(standing) {
				sprite = Entity.PLAYER_SPRITE_RIGHT[0];
			}else {
				sprite = Entity.PLAYER_SPRITE_RIGHT[curSprite];
			}
			
			if(isAttacking) {
				g.drawImage(ATTACK_RIGHT, this.getX()+8 - Camera.x, this.getY() - Camera.y, null);
			}
		}else if(dir == -1) {
			if(standing) {
				sprite = Entity.PLAYER_SPRITE_LEFT[1];
			}else {
				sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
			}
			
			if(isAttacking) {
				g.drawImage(ATTACK_LEFT, this.getX()-8 - Camera.x, this.getY() - Camera.y, null);
			}
		}
		super.render(g);
	}
	
	public void collisionEnemy() {
		for(int i =0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.rand.nextInt(100) < 30) {
					if(Entity.isColidding(this, e)) {
						life-=1;
						if(isAttacking) {
							((Enemy) e).vida--;
						}
					}
				}
			}
		}
	}
}
