package WizardTD;

import java.io.*;
import java.util.*;

public class Fireball{
    private int tower_ls_num;
    private int wave_ls_num;
    private int monster_ls_num;
    private int fireball_speed;
    private boolean shooting_complete_flag;
    private float fireball_x;
    private float fireball_y;

    public Fireball(int tower_ls_num, int wave_ls_num, int monster_ls_num, float fireball_x, float fireball_y){
        this.shooting_complete_flag = false;
        this.fireball_speed = 5;
        this.tower_ls_num = tower_ls_num;
        this.wave_ls_num = wave_ls_num;
        this.monster_ls_num = monster_ls_num;
        this.fireball_x = fireball_x;
        this.fireball_y = fireball_y;
    }
    
    
    public void set_fireball_x(float fireball_x){
        this.fireball_x = fireball_x;
    }

    public void set_fireball_y(float fireball_y){
        this.fireball_y = fireball_y;
    }

    public void set_shooting_complete_flag(boolean shooting_complete_flag){
        this.shooting_complete_flag = shooting_complete_flag;
    }

    /**
     * get integet that identify the wave that the monster shot by this fireball belongs to
     * @return wave_ls_num
     */
    public int get_wave_ls_num(){
        return (this.wave_ls_num);
    }

    /**
     * get integer that identify the specific monster shot by this fireball
     * @return monster_ls_num
     */
    public int get_monster_ls_num(){
        return (this.monster_ls_num);
    }

    /**
     * get x coordinate of fireball
     * @return fireball_x
     */
    public float get_fireball_x(){
        return (this.fireball_x);
    }

    /**
     * get y coordinate of fireball
     * @return fireball_y
     */
    public float get_fireball_y(){
        return (this.fireball_y);
    }

    /**
     * identify whether the fireball reach the monster
     * @return shooting_complete_flag
     */
    public boolean get_shooting_complete_flag(){
        return (this.shooting_complete_flag);
    }

    public int get_fireball_speed(){
        return (this.fireball_speed);
    }

    /**
     * get an integer which is used to identify the tower that shot that fireball
     * @return tower_ls_num
     */
    public int get_tower_ls_num(){
        return (this.tower_ls_num);
    }

}