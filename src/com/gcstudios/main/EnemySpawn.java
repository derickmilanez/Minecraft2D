package com.gcstudios.main;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.world.World;

public class EnemySpawn {
	
	public int interval = 60*5;
	public int curTime = 0;
	
	public void tick() {
		if(World.cycle == World.night) {
			curTime++;
			if(curTime>interval) {
				curTime = 0;
				Enemy e = new Enemy((Entity.rand.nextInt(900)+100)*16,45*16,16,16,2,Entity.ENEMY1_RIGHT[0]);
				Game.entities.add(e);
			}
		}
	}
}
