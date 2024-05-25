package WizardTD;

import java.io.*;
import java.util.*;

public class Monster{
    private String type;
    private float monster_x;
    private float monster_y;
    private double hp;
    private double speed;
    private double armour;
    private int entry_flag;
    private int map_out_flag;
    private float position;
    private double monster_count;
    private String last_move;
    private String next_move;
    private int duration;
    private int quantity;
    private double original_hp;
    private float start_x;
    private float start_y;
    private int mana_gained_on_kill;
    private float start_position;
    private int death_flag;
    private int gain_mana_flag;

    public Monster(String type, float monster_x,float monster_y, double hp, double speed, double armour, int entry_flag, int map_out_flag, float position, double monster_count, String last_move, String next_move, int duration, int quantity, int mana_gained_on_kill){
        this.type = type;
        this.monster_x = monster_x;
        this.monster_y = monster_y;
        this.hp = hp;
        this.speed = speed;
        this.armour = armour;
        this.entry_flag = entry_flag;
        this.map_out_flag = map_out_flag;
        this.position = position;
        this.monster_count = monster_count;
        this.last_move = last_move;
        this.next_move = next_move;
        this.duration = duration;
        this.quantity = quantity;
        this.original_hp = hp;
        this.start_x = monster_x;
        this.start_y = monster_y;
        this.mana_gained_on_kill = mana_gained_on_kill;
        this.start_position = position;
        this.death_flag = 1;
        this.gain_mana_flag = 1;
    }

    public void set_gain_mana_flag(int gain_mana_flag){
        this.gain_mana_flag = gain_mana_flag;
    }


    /**
     * indicate whether the mana of that monster is already gained 
     * @return gain_mana_flag
     */
    public int get_gain_mana_flag(){
        return (this.gain_mana_flag);
    }

    /**
     * indicate whether the monster is dead or not
     * @return death_flag
     */
    public int get_death_flag(){
        return (this.death_flag);
    }

    public void set_death_flag(int death_flag){
        this.death_flag = death_flag;
    }


    /**
     * get the initial hp of monster
     * @return original_hp
     */
    public double get_original_hp(){
        return (this.original_hp);
    }

    public double get_hp(){
        return (this.hp);
    }

    public double get_armour(){
        return (this.armour);
    }

    /**
     * get x coordinate of monster
     * @return monster_x
     */
    public float get_monster_x(){
        return (this.monster_x);
    }

    public void set_monster_x(float monster_x){
        this.monster_x = monster_x;
    }

    /**
     * get y coordinate of monster
     * @return monster_y
     */
    public float get_monster_y(){
        return (this.monster_y);
    }

    public void set_monster_y(float monster_y){
        this.monster_y = monster_y;
    }

    public double get_speed(){
        return (this.speed);
    }

    public void set_speed(double speed){
        this.speed = speed;
    }

    public int get_duration(){
        return (this.duration);
    }

    /**
     * position of monster in map
     * this is used to find the corresponding path that monster currently at from the level list
     * @return position
     */
    public float get_position(){
        return (this.position);
    }

    /**
     * last direction that monster move
     * @return last_move
     */
    public String get_last_move(){
        return (this.last_move);
    }

    /**
     * next direction that monster is going to move
     * @return next_move
     */
    public String get_next_move(){
        return (this.next_move);
    }

    /**
     * get the flag that check if monster appear outside the map for the first time
     * @return entry_flag
     */
    public int get_entry_flag(){
        return (this.entry_flag);
    }

    /**
     * check if monster is outside the map
     * @return map_out_flag
     */
    public int get_map_out_flag(){
        return (this.map_out_flag);
    }

    /**
     * get the count of number of frams since the monster appeared
     * @return monster_count
     */
    public double get_monster_count(){
        return (this.monster_count);
    }

    /**
     * called when monster reach the wizard house and banishing
     */
    public void respawn(){
        this.monster_x = this.start_x;
        this.monster_y = this.start_y;
        this.position = this.start_position;
        this.last_move = " ";
        this.next_move = " ";
        this.entry_flag = 1;
        this.map_out_flag = 1;
        this.monster_count = 0;
    }

    public void set_position(float position){
        this.position = position;
    }

    public void set_last_move(String last_move){
        this.last_move = last_move;
    }

    public void set_next_move(String next_move){
        this.next_move = next_move;
    }

    public void set_entry_flag(int entry_flag){
        this.entry_flag = entry_flag;
    }

    public void set_map_out_flag(int map_out_flag){
        this.map_out_flag = map_out_flag;
    }

    public void set_monster_count(double monster_count){
        this.monster_count = monster_count;
    }

    public int get_quantity(){
        return (this.quantity);
    }

    public float get_start_x(){
        return (this.start_x);
    }

    public float get_start_y(){
        return (this.start_y);
    }

    /**
     * called when fast forward button is clicked
     * monster will have 2x speed
     */
    public void accelerate(){
        this.speed = 2*this.speed;
        this.monster_count = this.monster_count/2;
    }

    /**
     * called when fast forward button is unselected
     * monster speed will back to normal speed
     */
    public void decelerate(){
        this.speed = this.speed/2;
        this.monster_count = 2*this.monster_count;
    }

    public String get_type(){
        return (this.type);
    }

    public int get_mana_gained_on_kill(){
        return (this.mana_gained_on_kill);
    }

    public void set_hp(double hp){
        this.hp = hp;
    }
}