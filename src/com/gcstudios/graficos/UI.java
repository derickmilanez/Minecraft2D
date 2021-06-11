package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class UI {
	
	public int seconds = 0;
	public int minutes = 0;
	public int frames = 0;
	
	public void tick() {
		frames++;
		if(frames == 60) {
			frames = 0;
			seconds++;
			if(seconds == 60) {
				seconds= 0;
				minutes++;
				if(minutes % 2 == 0) {
					World.cycle++;
					if(World.cycle > World.night) {
						World.cycle = World.morning;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		int curlife = (int)((Game.player.life/100) * 160);
		int curhunger = (int)((Game.player.hunger/100) * 160);
		g.drawImage(Game.spritesheet.getSprite(32, 16, 16, 16), 5, 5, 32, 32, null);
		g.setColor(Color.red);
		g.fillRect(48,5, 160, 30);
		g.setColor(Color.green);
		g.fillRect(48,5, curlife, 30);
		g.drawImage(Game.spritesheet.getSprite(48, 16, 16, 16), Game.WIDTH*Game.SCALE - 220, 5, 36, 36, null);
		g.setColor(Color.blue);
		g.fillRect(Game.WIDTH*Game.SCALE - 180,5, 160, 30);
		g.setColor(Color.yellow);
		g.fillRect(Game.WIDTH*Game.SCALE - 180,5, curhunger, 30);
		
		String formatTime = "";
		if(minutes < 10) {
			formatTime+="0"+minutes+":";
		}else {
			formatTime+=minutes+":";
		}
		if(seconds < 10) {
			formatTime+="0"+seconds;
		}else {
			formatTime+=seconds;
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,23));
		g.drawString("Time alive = " + formatTime, 5, Game.HEIGHT*Game.SCALE - 10);
	}
	
}
