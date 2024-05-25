package WizardTD;

import java.io.*;
import java.util.*;

public class Tower{
    private int tower_cost;
    private int initial_tower_range;
    private float initial_tower_firing_speed;
    private int initial_tower_damage;
    private int speed_level;
    private int range_level;
    private int damage_level;
    private int tower_level;
    private float tower_x;
    private float tower_y;
    private double current_tower_damage;
    private float tower_count;
    private float frame_between_firing;

    public Tower(int tower_cost, int initial_tower_range, float initial_tower_firing_speed, int initial_tower_damage, int range_level, int speed_level, int damage_level, int tower_level, float tower_x, float tower_y,double current_tower_damage){
        this.tower_cost = tower_cost;
        this.initial_tower_range = initial_tower_range;
        this.initial_tower_firing_speed = initial_tower_firing_speed;
        this.initial_tower_damage = initial_tower_damage;
        this.current_tower_damage = current_tower_damage;
        this.range_level = range_level;
        this.speed_level = speed_level;
        this.damage_level = damage_level;
        this.tower_level = tower_level;
        this.tower_x = tower_x;
        this.tower_y = tower_y;
        this.tower_count = 0;
        this.frame_between_firing = (float) 60/initial_tower_firing_speed;
    }


    /**
     * number of frames between each firing 
     * @return frame_between_firing
     */
    public float get_frame_between_firing(){
        return (this.frame_between_firing);
    }

    public void set_tower_count(float tower_count){
        this.tower_count = tower_count;
    }

    /**
     * add tower_count by 1 if tower count+1 is lower than fram_between_firing
     */
    public void add_tower_count(){
        if ((this.tower_count+1) >= this.frame_between_firing){
            return;
        }else{
            this.tower_count += 1;
        }
    }

    /**
     * get the count of frames since the tower is built
     * @return tower_count
     */
    public float get_tower_count(){
        return (this.tower_count);
    }

    public void set_current_tower_damage(double current_tower_damage){
        this.current_tower_damage = current_tower_damage;
    }

    public double get_current_tower_damage(){
        return (this.current_tower_damage);
    }

    /**
     * get x coordinate of tower
     * @return tower_x
     */
    public float get_tower_x(){
        return (this.tower_x);
    }

    /**
     * get y coordinate of tower
     * @return tower_y
     */
    public float get_tower_y(){
        return (this.tower_y);
    }

    public int get_range_level(){
        return (this.range_level);
    }

    public int get_speed_level(){
        return (this.speed_level);
    }

    public int get_damage_level(){
        return (this.damage_level);
    }

    public int get_tower_level(){
        return (this.tower_level);
    }    

    public void set_range_level(int range_level){
        this.range_level = range_level;
    }

    public void set_speed_level(int speed_level){
        this.speed_level = speed_level;
    }

    public void set_damage_level(int damage_level){
        this.damage_level = damage_level;
    }

    public void set_tower_level(int tower_level){
        this.tower_level = tower_level;
    }

    public int get_initial_tower_range(){
        return (this.initial_tower_range);
    }

    public float get_initial_tower_firing_speed(){
        return (this.initial_tower_firing_speed);
    }

    public int get_initial_tower_damage(){
        return (this.initial_tower_damage);
    }

    public void set_initial_tower_range(int initial_tower_range){
        this.initial_tower_range = initial_tower_range;
    }

    public void set_initial_tower_firing_speed(float initial_tower_firing_speed){
        this.initial_tower_firing_speed = initial_tower_firing_speed;
    }
}