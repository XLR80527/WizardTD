package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.*;

public class App extends PApplet {

    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE+TOPBAR;

    public static final int FPS = 60;

    public String configPath;

    public Random random = new Random();

    protected PImage grass;
    protected PImage path0;
    protected PImage rotate_90_path0;
    protected PImage path1;
    protected PImage rotate_90_path1;
    protected PImage rotate_180_path1;
    protected PImage rotate_270_path1;
    protected PImage path2;
    protected PImage rotate_90_path2;
    protected PImage rotate_180_path2;
    protected PImage rotate_270_path2;
    protected PImage path3;
    protected PImage shrub;
    protected PImage wizard_house;
    protected PImage rotate_90_wizard_house;
    protected PImage rotate_180_wizard_house;
    protected PImage rotate_270_wizard_house;
    protected PImage back1;
    protected PImage back2;
    protected PImage back3;
    protected PImage gremlin;
    protected PImage gremlin1;
    protected PImage gremlin2;
    protected PImage gremlin3;
    protected PImage gremlin4;
    protected PImage gremlin5;
    protected PImage tower0;
    protected PImage tower1;
    protected PImage tower2;
    protected PImage right_side_bar1;
    protected PImage right_side_bar2;
    protected PImage right_side_bar3;
    protected PImage beetle;
    protected PImage worm;
    protected PImage fireball;

    public JSONObject json;
    public String level;
	
    public String[] ls = new String[420];

    public float pre_wave_pause;
    public int q = 0;
    public int Q = 0;
    public int wave_count = 1;
    public int config_size;

    public int sleep_count = 0;
    public int sleep_count1 = 0;

    public int entryNum;
    public HashMap<Integer, float[]> monster_hash = new HashMap<>();
    public float[] store_random_ls;
    public int wizard_house_x;
    public int wizard_house_y;
    public rightSideBar right_bar;

    public boolean pause_flag = false;
    public boolean fast_flag = false;

    public ArrayList<Tower> tower_ls = new ArrayList<Tower>();
    public ArrayList<Wave> wave_ls = new ArrayList<Wave>();
    public ArrayList<Fireball> fireball_ls = new ArrayList<Fireball>();

    public int initial_mana;
    public int initial_mana_cap;
    public int initial_mana_gained_per_second;
    public int mana_pool_spell_cost;
    public int mana_pool_spell_cost_increase_per_use;
    public double mana_pool_spell_cap_multiplier;
    public double mana_pool_spell_mana_gained_multiplier;

    public int gain_mana_count;

	// Feel free to add any additional methods or attributes you want. Please put classes in different files.

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
	@Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
     */
	@Override
    public void setup() {
        frameRate(FPS);

        this.grass = this.loadImage("src/main/resources/WizardTD/grass.png");
        this.path0 = this.loadImage("src/main/resources/WizardTD/path0.png");
        this.rotate_90_path0 = rotateImageByDegrees(this.path0,90.0);
        this.path1 = this.loadImage("src/main/resources/WizardTD/path1.png");
        this.rotate_90_path1 = rotateImageByDegrees(this.path1,90.0);
        this.rotate_180_path1 = rotateImageByDegrees(this.path1,180.0);
        this.rotate_270_path1 = rotateImageByDegrees(this.path1,270.0);
        this.path2 = this.loadImage("src/main/resources/WizardTD/path2.png");
        this.rotate_90_path2 = rotateImageByDegrees(this.path2,90.0);
        this.rotate_180_path2 = rotateImageByDegrees(this.path2,180.0);
        this.rotate_270_path2 = rotateImageByDegrees(this.path2,270.0);
        this.path3 = this.loadImage("src/main/resources/WizardTD/path3.png");
        this.shrub = this.loadImage("src/main/resources/WizardTD/shrub.png");
        this.wizard_house = this.loadImage("src/main/resources/WizardTD/wizard_house.png");
        this.rotate_90_wizard_house = rotateImageByDegrees(this.wizard_house,90.0);
        this.rotate_180_wizard_house = rotateImageByDegrees(this.wizard_house,180.0);
        this.rotate_270_wizard_house = rotateImageByDegrees(this.wizard_house,270.0);
        this.json = this.loadJSONObject(configPath);
        this.back1 = this.loadImage("src/main/resources/WizardTD/back1.png");
        this.gremlin = this.loadImage("src/main/resources/WizardTD/gremlin.png");
        this.gremlin1 = this.loadImage("src/main/resources/WizardTD/gremlin1.png");
        this.gremlin2 = this.loadImage("src/main/resources/WizardTD/gremlin2.png");
        this.gremlin3 = this.loadImage("src/main/resources/WizardTD/gremlin3.png");
        this.gremlin4 = this.loadImage("src/main/resources/WizardTD/gremlin4.png");
        this.gremlin5 = this.loadImage("src/main/resources/WizardTD/gremlin5.png");
        this.back3 = this.loadImage("src/main/resources/WizardTD/back3.png");
        this.back2 = this.loadImage("src/main/resources/WizardTD/back2.png");
        this.tower0 = this.loadImage("src/main/resources/WizardTD/tower0.png");
        this.tower1 = this.loadImage("src/main/resources/WizardTD/tower1.png");
        this.tower2 = this.loadImage("src/main/resources/WizardTD/tower2.png");
        this.right_side_bar1 = this.loadImage("src/main/resources/WizardTD/right_side_bar1.png");
        this.right_side_bar2 = this.loadImage("src/main/resources/WizardTD/right_side_bar2.png");
        this.right_side_bar3 = this.loadImage("src/main/resources/WizardTD/right_side_bar3.png");
        this.beetle = this.loadImage("src/main/resources/WizardTD/beetle.png");
        this.worm = this.loadImage("src/main/resources/WizardTD/worm.png");
        this.fireball = this.loadImage("src/main/resources/WizardTD/fireball.png");
        this.right_bar = new rightSideBar();
        this.level = json.getString("layout");

        extract_level();
        setWaveValue();
        set_mana();
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
	@Override
    public void keyPressed(){
        if (key == 't'){
            this.right_bar.set_build_tower_flag();
        }
        if (key == '1'){
            this.right_bar.set_upgrade_range_flag();
        }
        if (key == '2'){
            this.right_bar.set_upgrade_speed_flag();
        }
        if (key == '3'){
            this.right_bar.set_upgrade_damage_flag();
        }
        if (key == 'p'){
            pause();
            this.right_bar.set_pause_flag();
        }
        if (key == 'f'){
            fast_forward_flag();
            fast_forward();
            this.right_bar.set_accelerate_flag();
        }
        if (key == 'm'){
            mana_pool_spell();
            this.right_bar.set_mana_flag();
        }
        if (key == 'r'){
            if (lost_condition() == true){
                reset();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        float mouse_x = (float) e.getX();
        float mouse_y = (float) e.getY();
        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 45 && mouse_y <= 87){
            fast_forward_flag();
            fast_forward();
            this.right_bar.set_accelerate_flag();
        }
        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 95 && mouse_y <= 137){
            pause();
            this.right_bar.set_pause_flag();
        }
        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 145 && mouse_y <= 187){
            this.right_bar.set_build_tower_flag();
        }
        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 195 && mouse_y <= 237){
            this.right_bar.set_upgrade_range_flag();
        }
        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 245 && mouse_y <= 287){
            this.right_bar.set_upgrade_speed_flag();
        }
        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 295 && mouse_y <= 337){
            this.right_bar.set_upgrade_damage_flag();
        }
        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 345 && mouse_y <= 387){
            mana_pool_spell();
            this.right_bar.set_mana_flag();
        }

        if (is_tower(mouse_x, mouse_y) == true){
            upgrade_tower(mouse_x, mouse_y);
        }else if (is_tower(mouse_x, mouse_y) == false){
            store_tower_value(mouse_x, mouse_y);
        }
        
    }

    /**
     * Draw all elements in the game by current frame.
     */
	@Override
    public void draw(){
        gain_mana_per_second();

        if (fast_flag == true){
            sleep_count1 = sleep_count%30;
        }else{
            sleep_count1 = sleep_count%60;
        }

        int original_q = q;

        if (pre_wave_pause <= 0 && (q+1) < config_size){
            q += 1;
            wave_count += 1;
            setWaveValue();
            sleep_count = 0;
        }

        map();

        if (original_q != q){
            setMonster();
            Q += 1;
        }

        if ((q+1 == config_size) && (Q != config_size)){
            setMonster();
            Q += 1;
        }

        if (q >= 1){
            monster_spawn();
            display_monster_hp();
            gain_dead_monster_mana();
            monster_death();
        }

        build_tower();
        display_range();
        hide_monster();
        wave();
        display_mana_bar(initial_mana);
        display_total_upgrade_cost();
        detect_monster();
        shoot_fireball();
        lost_condition();
        win_condition();

        if ((pre_wave_pause) < 0 && (q+1) == config_size){
            image(this.back1,0,0);
        }else{
            if (fast_flag == true){
                if (sleep_count1 == (int) (pre_wave_pause*30) && wave_count == 1){
                    if (pause_flag == true){
                        pre_wave_pause += pre_wave_pause;
                        sleep_count += sleep_count;
                    }
                    pre_wave_pause -= pre_wave_pause;
                    sleep_count -= sleep_count;
                }else if (sleep_count1 == 29){
                    pre_wave_pause -= 1.0;
                    if (pause_flag == true){
                        pre_wave_pause += 1.0;
                    }
                }
            }else{
                if (sleep_count1 == (int) (pre_wave_pause*60) && wave_count == 1){
                    if (pause_flag == true){
                        pre_wave_pause += pre_wave_pause;
                        sleep_count += sleep_count;
                    }
                    pre_wave_pause -= pre_wave_pause;
                    sleep_count -= sleep_count;
                }else if (sleep_count1 == 59){
                    pre_wave_pause -= 1.0;
                    if (pause_flag == true){
                        pre_wave_pause += 1.0;
                    }
                }
            }
        }

        right_side_bar();
        lost_animation();
        win_animation();
    }
    /**
     * Covering the monster when monster come from outside of the map.
     */
    public void hide_monster(){
        image(this.back3,0,0);
        image(this.back2,640,0);
    }
    
    public void gain_mana_per_second(){
        double frame_per_mana_gained = 60/initial_mana_gained_per_second;
        double gain_mana_count1 = gain_mana_count%frame_per_mana_gained;
        if (fast_flag == true){
            frame_per_mana_gained = frame_per_mana_gained/2;
            gain_mana_count1 = gain_mana_count%frame_per_mana_gained;
        }
        if (gain_mana_count1 + 1 >= frame_per_mana_gained){
            initial_mana += 1;
        }
        if (pause_flag == false){
            gain_mana_count += 1;
        }
    }

    public void gain_dead_monster_mana(){
        int i = 0;
        while (i < wave_ls.size()){
            Wave w = wave_ls.get(i);
            ArrayList<Monster> monster_ls = w.get_monster_ls();
            int l = 0;
            while (l < monster_ls.size()){
                Monster m = monster_ls.get(l);
                double hp = m.get_hp();
                int gain_mana_flag = m.get_gain_mana_flag();
                int mana_gained_on_kill = m.get_mana_gained_on_kill();
                if (hp <= 0 && gain_mana_flag == 1){
                    gain_mana_flag = 0;
                    m.set_gain_mana_flag(gain_mana_flag);
                    initial_mana += mana_gained_on_kill;
                }
                l += 1;
            }
            i += 1; 
        }    
    }

    /**
     * display the death animation of different type of monster.
     */
    public void monster_death(){
        int i = 0;
        while (i < wave_ls.size()){
            Wave w = wave_ls.get(i);
            ArrayList<Monster> monster_ls = w.get_monster_ls();
            int l = 0;
            while (l < monster_ls.size()){
                Monster m = monster_ls.get(l);
                double hp = m.get_hp();
                float monster_x = m.get_monster_x();
                float monster_y = m.get_monster_y();
                int death_flag = m.get_death_flag();
                String type = m.get_type();
                if (hp <= 0){
                    if (death_flag <= 4){
                        if (type.equals("gremlin")){
                            image(this.gremlin1,monster_x,monster_y);
                        }else if (type.equals("beetle")){
                            image(this.beetle,monster_x,monster_y);
                        }else if (type.equals("worm")){
                            image(this.worm,monster_x,monster_y);
                        }
                        death_flag += 1;
                        m.set_death_flag(death_flag);
                    }else if (death_flag >= 5 && death_flag <= 8){
                        if (type.equals("gremlin")){
                            image(this.gremlin2,monster_x,monster_y);
                        }else if (type.equals("beetle")){
                            image(rotateImageByDegrees(this.beetle,30.0),monster_x,monster_y);
                        }else if (type.equals("worm")){
                            image(rotateImageByDegrees(this.worm, 30.0),monster_x,monster_y);
                        }
                        death_flag += 1;
                        m.set_death_flag(death_flag);
                    }else if (death_flag >= 9 && death_flag <= 12){
                        if (type.equals("gremlin")){
                            image(this.gremlin3,monster_x,monster_y);
                        }else if (type.equals("beetle")){
                            image(rotateImageByDegrees(this.beetle,60.0),monster_x,monster_y);
                        }else if (type.equals("worm")){
                            image(rotateImageByDegrees(this.worm, 60.0),monster_x,monster_y);
                        }
                        death_flag += 1;
                        m.set_death_flag(death_flag);
                    }else if (death_flag >= 13 && death_flag <= 16){
                        if (type.equals("gremlin")){
                            image(this.gremlin4,monster_x,monster_y);
                        }else if (type.equals("beetle")){
                            image(rotateImageByDegrees(this.beetle,90.0),monster_x,monster_y);
                        }else if (type.equals("worm")){
                            image(rotateImageByDegrees(this.worm, 90.0),monster_x,monster_y);
                        }
                        death_flag += 1;
                        m.set_death_flag(death_flag);
                    }else if (death_flag >= 17 && death_flag <= 20){
                        if (type.equals("gremlin")){
                            image(this.gremlin5,monster_x,monster_y);
                        }
                        death_flag += 1;
                        m.set_death_flag(death_flag);
                    }
                }
                l += 1;
            }
            i += 1;
        }
    }

    /**
     * set all initial values related to mana.
     */
    public void set_mana(){
        initial_mana = json.getInt("initial_mana");
        initial_mana_cap = json.getInt("initial_mana_cap");
        initial_mana_gained_per_second = json.getInt("initial_mana_gained_per_second");
        mana_pool_spell_cost = json.getInt("mana_pool_spell_initial_cost");
        mana_pool_spell_cost_increase_per_use = json.getInt("mana_pool_spell_cost_increase_per_use");
        mana_pool_spell_cap_multiplier = json.getFloat("mana_pool_spell_cap_multiplier");
        mana_pool_spell_mana_gained_multiplier = json.getFloat("mana_pool_spell_mana_gained_multiplier");
    }

    /**
     * if player lost the game, return true, else flase.
     * @return boolean
     */
    public boolean lost_condition(){
        int l = 0;
        while (l < wave_ls.size()){
            Wave w = wave_ls.get(l);
            ArrayList<Monster> monster_ls = w.get_monster_ls();
            int q = 0;
            while (q < monster_ls.size()){
                Monster m = monster_ls.get(q);
                double hp = m.get_hp();
                if (initial_mana < 0 && hp > 0){
                    pause_flag = true;
                    return true;
                }
                q += 1;
            }
            l += 1;
        }      
        return false;  
    }

    public void lost_animation(){
        if (lost_condition() == true){
            textSize(50);
            fill(255,0,0);
            text("YOU LOST",230,230);
            textSize(30);
            text("Press 'r' to restart",220,260);
            image(this.back1,320,0);
            display_mana_bar(0);
        }
    }

    public void win_animation(){
        if (win_condition() == true){
            textSize(50);
            fill(255,0,0);
            text("YOU WIN",230,230);
        }
    }

    /**
     * if player win the game, return true, else false.
     * @return boolean
     */
    public boolean win_condition(){
        int l = 0;
        while (l < wave_ls.size()){
            Wave w = wave_ls.get(l);
            ArrayList<Monster> monster_ls = w.get_monster_ls();
            int q = 0;
            while (q < monster_ls.size()){
                Monster m = monster_ls.get(q);
                double hp = m.get_hp();
                if (hp > 0){
                    return false;
                }
                q += 1;
            }
            l += 1;
        }

        if ((wave_ls.size()) < 1){
            return false;
        }

        pause_flag = true;
        return true;  
    }

    public void mana_pool_spell(){
        if (initial_mana >= mana_pool_spell_cost){
            initial_mana -= mana_pool_spell_cost;
            mana_pool_spell_cost += mana_pool_spell_cost_increase_per_use;
            initial_mana_cap = (int)(initial_mana_cap*mana_pool_spell_cap_multiplier);
            initial_mana_gained_per_second = (int)(initial_mana_gained_per_second*mana_pool_spell_mana_gained_multiplier);
        }
    }

    /**
     * Called if player press 'r' after lose the game.
     */
    public void reset(){
        q = 0;
        Q = 0;
        wave_count = 1;
        sleep_count = 0;
        sleep_count1 = 0;
        pause_flag = false;
        fast_flag = false;
        setWaveValue();
        set_mana();
        tower_ls = new ArrayList<Tower>();
        wave_ls = new ArrayList<Wave>();
        fireball_ls = new ArrayList<Fireball>();
        gain_mana_count = 0;
        right_bar.set_all_flag_false();

    }

    /**
     * update the position of each fireball based on their targeting monster. 
     */
    public void shoot_fireball(){
        int i = 0;
        while (i < fireball_ls.size()){
            Fireball f = fireball_ls.get(i);
            float fireball_x = f.get_fireball_x();
            float fireball_y = f.get_fireball_y();
            boolean shooting_complete_flag = f.get_shooting_complete_flag();
            int fireball_speed = f.get_fireball_speed();
            int tower_ls_num = f.get_tower_ls_num();
            int wave_ls_num = f.get_wave_ls_num();
            int monster_ls_num = f.get_monster_ls_num();

            Tower t = tower_ls.get(tower_ls_num);
            double current_tower_damage = t.get_current_tower_damage();

            Wave w = wave_ls.get(wave_ls_num);
            ArrayList<Monster> monster_ls = w.get_monster_ls();

            Monster m = monster_ls.get(monster_ls_num);
            if (fast_flag == true){
                fireball_speed = 2*fireball_speed;
            }
            float monster_x = m.get_monster_x()+10;
            float monster_y = m.get_monster_y()+10;
            double armour = m.get_armour();
            double hp = m.get_hp();
            float distance_x = monster_x - fireball_x;
            float distance_y = monster_y - fireball_y;
            float distance = sqrt((float)Math.pow(distance_x, 2) + (float)Math.pow(distance_y, 2));
            if (shooting_complete_flag == false && distance >= fireball_speed && pause_flag == false){
                if (monster_x > fireball_x){
                    fireball_x += sqrt((fireball_speed*fireball_speed)/2);
                }else{
                    fireball_x -= sqrt((fireball_speed*fireball_speed)/2);
                }

                if (monster_y > fireball_y){
                    fireball_y += sqrt((fireball_speed*fireball_speed)/2);
                }else{
                    fireball_y -= sqrt((fireball_speed*fireball_speed)/2);
                }
            }else if (shooting_complete_flag == false && distance < fireball_speed && pause_flag == false){
                if (monster_x > fireball_x){
                    fireball_x += sqrt((distance*distance)/2);
                }else{
                    fireball_x -= sqrt((distance*distance)/2);
                }

                if (monster_y > fireball_y){
                    fireball_y += sqrt((distance*distance)/2);
                }else{
                    fireball_y -= sqrt((distance*distance)/2);
                }
                hp -= (armour*current_tower_damage);
                shooting_complete_flag = true;
            }
            if (shooting_complete_flag == false){
                image(this.fireball, fireball_x, fireball_y);
            }

            f.set_fireball_x(fireball_x);
            f.set_fireball_y(fireball_y);
            f.set_shooting_complete_flag(shooting_complete_flag);
            m.set_hp(hp);
            i += 1;
        }
        
    }

    /**
     * detect if there's a monster in the firing range of each tower and generate fireball based on the firing speed of each tower.
     */
    public void detect_monster(){
        int i = 0;
        while (i < tower_ls.size()){
            Tower t = tower_ls.get(i);
            float tower_x = t.get_tower_x()+16;
            float tower_y = t.get_tower_y()+16;
            int initial_tower_range = t.get_initial_tower_range();
            float initial_tower_firing_speed = t.get_initial_tower_firing_speed();
            float frame_between_firing = t.get_frame_between_firing();
            if (fast_flag == true){
                frame_between_firing = (float) frame_between_firing/2;
            }
            float tower_count = t.get_tower_count();
            int l = 0;
            while (l < wave_ls.size()){
                Wave w = wave_ls.get(l);
                ArrayList<Monster> monster_ls = w.get_monster_ls();
                int q = 0;
                while (q < monster_ls.size()){
                    Monster m = monster_ls.get(q);
                    double hp = m.get_hp();
                    float monster_x = m.get_monster_x()+10;
                    float monster_y = m.get_monster_y()+10;
                    float distance_x = monster_x - tower_x;
                    float distance_y = monster_y - tower_y;
                    float distance = sqrt((float)Math.pow(distance_x, 2) + (float)Math.pow(distance_y, 2));
                    if (distance <= initial_tower_range && tower_count+1 >= frame_between_firing && hp > 0){
                        fireball_ls.add(new Fireball(i, l, q, tower_x, tower_y));
                        tower_count = 0;
                        t.set_tower_count(tower_count);
                    }
                    q += 1;
                }
                l += 1;
            }
            if (pause_flag == true){
                i += 1;
                continue;
            }else{
                t.add_tower_count();
                i += 1;
            }
        }
    }

    /**
     * display hp bar of monster after being shot by tower.
     */
    public void display_monster_hp(){
        int a = 0;
        while (a < wave_ls.size()){
            Wave w = wave_ls.get(a);
            ArrayList<Monster> monster_ls = w.get_monster_ls();
            int i = 0;
            while (i < monster_ls.size()){
                Monster m = monster_ls.get(i);
                float monster_x = m.get_monster_x();
                float monster_y = m.get_monster_y();
                double original_hp = m.get_original_hp();
                double hp = m.get_hp();
                if (original_hp != hp && hp > 0){
                    noStroke();
                    fill(255,0,0);
                    rect(monster_x-2,monster_y-4,25,2);
                    fill(0,255,0);
                    float proportion = ((float)hp/(float)original_hp)*25;
                    rect(monster_x-2,monster_y-4,proportion,2);
                }
                i += 1;
            }
            a += 1;
        }
    }

    public void display_mana_bar(int initial_mana){
        if (initial_mana > initial_mana_cap){
            initial_mana = initial_mana_cap;
        }
        fill(0);
        textSize(22);
        text("MANA:",320,28);
        stroke(0);
        strokeWeight(2);
        fill(255);
        rect(400,10,320,22);
        fill(173,216,230);
        float proportion = ((float)initial_mana/(float)initial_mana_cap)*320;
        rect(400,10,proportion,22);
        fill(0);
        text(initial_mana+"/"+initial_mana_cap,500,29);

    }

    /**
     * display upgrade cost of a specific tower based on selected upgrade flags.
     */
    public void display_total_upgrade_cost(){
        float mouse_x = mouseX;
        float mouse_y = mouseY;
        mouse_x -= mouse_x%32;
        mouse_y -= (mouse_y-40)%32;
        int i = 0;
        while (i < tower_ls.size()){
            Tower t = tower_ls.get(i);
            float tower_x = t.get_tower_x();
            float tower_y = t.get_tower_y();
            int range_level = t.get_range_level();
            int speed_level = t.get_speed_level();
            int damage_level = t.get_damage_level();
            if (mouse_x == tower_x && mouse_y == tower_y){
                int upgrade_num = 0;
                int range_cost = 0;
                int speed_cost = 0;
                int damage_cost = 0;
                int total = 0;
                if (right_bar.get_upgrade_range_flag() == true){
                    upgrade_num += 1;
                    range_cost = (range_level+2)*10;
                    total += range_cost;

                }
                if (right_bar.get_upgrade_speed_flag() == true){
                    upgrade_num += 1;
                    speed_cost = (speed_level+2)*10;
                    total += speed_cost;
                }
                if (right_bar.get_upgrade_damage_flag() == true){
                    upgrade_num += 1;
                    damage_cost = (damage_level+2)*10;
                    total += damage_cost;
                }

                if (upgrade_num != 0){
                    stroke(0);
                    fill(255);
                    rect(650,564,90,20);
                    rect(650,584,90,20*upgrade_num);
                    rect(650,584+(20*upgrade_num),90,20);

                    int start = 578;
                    textSize(13);
                    fill(0);
                    text("Upgrade cost",653,start);
                    if (range_cost != 0){
                        start += 20;
                        text("range:   "+range_cost,653,start);
                    }
                    if (speed_cost != 0){
                        start += 20;
                        text("speed:   "+speed_cost,653,start);
                    }
                    if (damage_cost != 0){
                        start += 20;
                        text("damage:"+damage_cost,653,start);
                    }
                    text("Total:    "+total,653,start+20);
                }
            }
            i += 1;
        }
    }

    /**
     * When player hovering mouse on a tower, display firing range of that tower in circle.
     */
    public void display_range(){
        int i = 0;
        float mouse_x = mouseX;
        float mouse_y = mouseY;
        mouse_x -= mouse_x%32;
        mouse_y -= (mouse_y-40)%32;
        while (i < tower_ls.size()){
            Tower t = tower_ls.get(i);
            float tower_x = t.get_tower_x();
            float tower_y = t.get_tower_y();
            int tower_range = t.get_initial_tower_range();
            if (mouse_x == tower_x && mouse_y == tower_y){
                stroke(255,255,0);
                noFill();
                ellipse(mouse_x+16,mouse_y+16,2*tower_range,2*tower_range);
                break;
            }
            i += 1;
        }

    }

    /**
     * check whether the position of mouse click exist a tower.
     * @param mouse_x, x coordinate of mouse click.
     * @param mouse_y, y coordinate of mouse click.
     * @return boolean
     */
    public boolean is_tower(float mouse_x, float mouse_y){
        int i = 0;
        mouse_x -= mouse_x%32;
        mouse_y -= (mouse_y-40)%32;
        while (i < tower_ls.size()){
            Tower t = tower_ls.get(i);
            float tower_x = t.get_tower_x();
            float tower_y = t.get_tower_y();
            if (mouse_x == tower_x && mouse_y == tower_y){
                return true;
            }
            i += 1;
        }
        return false;
    }


    public void upgrade_tower(float mouse_x, float mouse_y){
        int i = 0;
        mouse_x -= mouse_x%32;
        mouse_y -= (mouse_y-40)%32;
        while (i < tower_ls.size()){
            Tower t = tower_ls.get(i);
            float tower_x = t.get_tower_x();
            float tower_y = t.get_tower_y();
            if (mouse_x == tower_x && mouse_y == tower_y){
                int speed_level = t.get_speed_level();
                int range_level = t.get_range_level();
                int damage_level = t.get_damage_level();
                int tower_level = t.get_tower_level();
                int initial_tower_range = t.get_initial_tower_range();
                float initial_tower_firing_speed = t.get_initial_tower_firing_speed();
                int initial_tower_damage = t.get_initial_tower_damage();
                double current_tower_damage = t.get_current_tower_damage();
                int range_cost = 0;
                int speed_cost = 0;
                int damage_cost = 0;
                if (right_bar.get_upgrade_range_flag() == true && initial_mana >= ((range_level+2)*10)){
                    range_level += 1;
                    range_cost = (range_level+1)*10;
                    initial_mana -= range_cost;
                    initial_tower_range += 32;
                }
                if (right_bar.get_upgrade_speed_flag() == true && initial_mana >= ((speed_level+2)*10)){
                    speed_level += 1;
                    speed_cost = (speed_level+1)*10;
                    initial_mana -= speed_cost;
                    initial_tower_firing_speed += 0.5;
                }
                if (right_bar.get_upgrade_damage_flag() == true && initial_mana >= ((damage_level+2)*10)){
                    damage_level += 1;
                    damage_cost = (damage_level+1)*10;
                    initial_mana -= damage_cost;
                    current_tower_damage += (double) initial_tower_damage/2;
                }
                if (range_level >= 1 && speed_level >= 1 && damage_level >= 1){
                    tower_level = 2;
                }
                if (range_level >= 2 && speed_level >= 2 && damage_level >= 2){
                    tower_level = 3;
                }
                t.set_range_level(range_level);
                t.set_speed_level(speed_level);
                t.set_damage_level(damage_level);
                t.set_tower_level(tower_level);
                t.set_initial_tower_range(initial_tower_range);
                t.set_initial_tower_firing_speed(initial_tower_firing_speed);
                t.set_current_tower_damage(current_tower_damage);
                return;
            }
            i += 1;
        }
    }

    /**
     * draw all tower in GUI.
     */
    public void build_tower(){
        int i = 0;
        while (i < tower_ls.size()){
            Tower t = tower_ls.get(i);
            float tower_x = t.get_tower_x();
            float tower_y = t.get_tower_y();
            int tower_level = t.get_tower_level();
            int range_level = t.get_range_level();
            int speed_level = t.get_speed_level();
            int damage_level = t.get_damage_level();
            int purple = color(128,0,128);
            int tower_flag = tower_level-1;
            int damage_level1 = damage_level - tower_flag;
            int range_level1 = range_level - tower_flag;
            int speed_level1 = speed_level - tower_flag;
            textSize(8);
            fill(purple);

            if (tower_level == 3){
                image(this.tower2, tower_x, tower_y);
            }else if (tower_level == 2){
                image(this.tower1, tower_x, tower_y);
            }else if (tower_level == 1){
                image(this.tower0, tower_x, tower_y);
            }

            if (range_level == speed_level && speed_level == damage_level && range_level == damage_level && range_level <= 2){
                i += 1;
                continue;
            }

            int l = 0;
            while (l < range_level1){
                text("O",tower_x+(5*l),tower_y+7);
                l += 1;
            }
            
            if (speed_level1 > 0){
                stroke(50,100,255,250);
                strokeWeight(speed_level+1);
                noFill();
                rect(tower_x+5,tower_y+5,21,21);
            }

            int n = 0;
            while (n < damage_level1){
                text("X",tower_x+(5*n),tower_y+31);
                n += 1;
            }

            i += 1;
        }
    }

    /**
     * store the value of each existing tower.
     * @param mouse_x, x coordinate of mouse click.
     * @param mouse_y, y coordinate of mouse click.
     */
    public void store_tower_value(float mouse_x, float mouse_y){
        if (is_grass(mouse_x, mouse_y) == true && right_bar.get_build_tower_flag() == true){
            mouse_x -= mouse_x%32;
            mouse_y -= (mouse_y-40)%32;
            int position = (int)(mouse_x/32) + ((int)(mouse_y/32)-1)*21;
            if (ls[position].equals("X")){
                mouse_y += 32;
            }
            int speed_level = 0;
            int range_level = 0;
            int damage_level = 0;
            int tower_level = 1;
            int tower_cost = json.getInt("tower_cost");
            int initial_tower_range = json.getInt("initial_tower_range");
            float initial_tower_firing_speed = json.getFloat("initial_tower_firing_speed");
            int initial_tower_damage = json.getInt("initial_tower_damage");
            double current_tower_damage = initial_tower_damage;
            if (initial_mana >= tower_cost){
                initial_mana -= tower_cost;
                if (right_bar.get_upgrade_speed_flag() == true && initial_mana >= 20){
                    speed_level = 1;
                    initial_mana -= 20;
                    initial_tower_firing_speed += 0.5;
                }
                if (right_bar.get_upgrade_range_flag() == true && initial_mana >= 20){
                    range_level = 1;
                    initial_mana -= 20;
                    initial_tower_range += 32;
                }
                if (right_bar.get_upgrade_damage_flag() == true && initial_mana >= 20){
                    damage_level = 1;
                    initial_mana -= 20;
                    current_tower_damage += (double) initial_tower_damage/2;
                }
                if (speed_level == 1 && range_level == 1 && damage_level == 1){
                    tower_level = 2;
                }
                tower_ls.add(new Tower(tower_cost, initial_tower_range, initial_tower_firing_speed, initial_tower_damage, range_level, speed_level, damage_level, tower_level, mouse_x, mouse_y, current_tower_damage));
            }
        }
    }

    /**
     * check if the mouse click position is grass
     * @param mouse_x, x coordinate of mouse click.
     * @param mouse_y, y coordinate of mouse click.
     * @return boolean
     */
    public boolean is_grass(float mouse_x, float mouse_y){
        if (mouse_x > 640 || mouse_y < 40){
            return false;
        }
        int x = (int) mouse_x/32;
        int y = (int) mouse_y/32;
        int position = (y-1)*21 + x;
        if (ls[position].equals(" ")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * accelerate the game to 2x speed.
     */
    public void fast_forward(){
        int i = 0;
        while (i < wave_ls.size()){
            Wave w = wave_ls.get(i);
            ArrayList<Monster> monster_ls = w.get_monster_ls();
            int l = 0;
            while (l < monster_ls.size()){
                Monster m = monster_ls.get(l);
                if (fast_flag == true){
                    m.accelerate();
                }else if (fast_flag == false){
                    m.decelerate();
                }
                l += 1;
            }
            i += 1;
        }
    }

    /**
     * flag to control the fast forward mode.
     */
    public void fast_forward_flag(){
        if (fast_flag == true){
            fast_flag = false;
        }else{
            fast_flag = true;
        }
    }

    /**
     * flag to control whether to pause the game or not.
     */
    public void pause(){
        if (pause_flag == true){
            pause_flag = false;
        }else{
            pause_flag = true;
        }
    }

    public void setMonster(){
        store_random();
        store_monster_value();
    }

    /**
     * Store value of each monster spawned.
     */
    public void store_monster_value(){
        JSONArray ls = json.getJSONArray("waves");
        config_size = ls.size();
        JSONObject ls1 = ls.getJSONObject(Q);
        int duration = ls1.getInt("duration");
        JSONArray ls2 = ls1.getJSONArray("monsters");
        ArrayList<Monster> monster_ls = new ArrayList<Monster>();
        int q = 0;
        while (q < ls2.size()){
            JSONObject ls3 = ls2.getJSONObject(q);
            int quantity = ls3.getInt("quantity");
            double hp = ls3.getFloat("hp");
            int mana_gained_on_kill = ls3.getInt("mana_gained_on_kill");
            String monster_type = ls3.getString("type");
            double speed = ls3.getFloat("speed");
            double armour = ls3.getFloat("armour");
            int l = 0;
            while (l < quantity){
                float divider = ((float)1/entryNum);
                int i = 0;
                while (i < entryNum){
                    if ((store_random_ls[l] >= i*divider) && (store_random_ls[l] < ((i+1)*divider))){
                        float[] ls4 = new float[3];
                        ls4 = monster_hash.get(i);
                        float monster_x = ls4[0];
                        float monster_y = ls4[1];
                        float position = ls4[2];
                        int entry_flag = 1;
                        int map_out_flag = 1;
                        int monster_count = 0;
                        String last_move = " ";
                        String next_move = " ";
                        monster_ls.add(new Monster(monster_type, monster_x, monster_y, hp, speed, armour, entry_flag, map_out_flag, position, monster_count, last_move, next_move, duration, quantity, mana_gained_on_kill));
                        break;
                    }
                    i += 1;
                }
                l += 1;
            }
            q += 1;
        }
        wave_ls.add(new Wave(monster_ls));
    }

    /**
     * draw monster in GUI every frame
     */
    public void monster_spawn(){
        int a = 0;
        while (a < wave_ls.size()){
            Wave w = wave_ls.get(a);
            ArrayList<Monster> monster_ls = w.get_monster_ls();
            int I = 0;
            while (I < monster_ls.size()){
                Monster m = monster_ls.get(I);
                double hp = m.get_hp();
                String type = m.get_type();
                double speed = m.get_speed();
                double pass_pixel_time = 32/speed;
                int duration = m.get_duration();
                int quantity = m.get_quantity();
                float distance_pixel = ((float)duration/quantity)*60;
                float multiple_x = m.get_monster_x();
                float multiple_y = m.get_monster_y();
                float l = m.get_position(); 
                int L = (int) l;
                String last_move = m.get_last_move(); 
                String next_move = m.get_next_move();
                int entry_flag = m.get_entry_flag();
                int map_out_flag = m.get_map_out_flag();
                double monster_count = m.get_monster_count(); 
                double monster_count1 = monster_count%pass_pixel_time;
                String up = " ";
                String down = " ";
                String left = " ";
                String right = " ";
                if ((L-1) >= 0){
                    left = ls[L-1];
                } 
                if ((L+1) < 420 && L >= 0){
                    right = ls[L+1];
                }
                if ((L-21) >= 0){
                    up = ls[L-21];
                } 
                if ((L+21) < 420 && L >= 0){
                    down = ls[L+21];
                }

                if (hp <= 0){
                    I += 1;
                    continue;
                }

                float distance_x = multiple_x - wizard_house_x;
                float distance_y = multiple_y - wizard_house_y;
                float distance = sqrt((float)Math.pow(distance_x, 2) + (float)Math.pow(distance_y, 2));
                if (distance >= -30 && distance <= 30){
                    m.respawn();
                    multiple_x = m.get_monster_x();
                    multiple_y = m.get_monster_y();
                    l = m.get_position(); 
                    L = (int) l;
                    last_move = m.get_last_move(); 
                    next_move = m.get_next_move();
                    entry_flag = m.get_entry_flag();
                    map_out_flag = m.get_map_out_flag();
                    monster_count = m.get_monster_count(); 
                    monster_count1 = monster_count%pass_pixel_time;
                    initial_mana -= hp;
                }

                if (monster_count1 >= pass_pixel_time-1){
                    if (last_move.equals(" ")){
                        if (up.equals("X")){
                            next_move = "up";
                        }else if (down.equals("X")){
                            next_move = "down";
                        }else if (left.equals("X")){
                            next_move = "left";
                        }else if (right.equals("X")){
                            next_move = "right";
                        }
                    }else if (last_move.equals("up")){
                        if (up.equals("X") && left.equals("X") && right.equals("X")){
                            if (ls[L-4].equals("X")){
                                next_move = "left";
                            }else if (ls[L+7].equals("X")){
                                next_move = "right";
                            }else if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (up.equals("X") && left.equals("X")){
                            if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }
                        }else if (up.equals("X") && right.equals("X")){
                            if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (left.equals("X") && right.equals("X")){
                            if (ls[L-2].equals("X")){
                                next_move = "left";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (up.equals("X")){
                            next_move = "up";
                        }else if (left.equals("X")){
                            next_move = "left";
                        }else if (right.equals("X")){
                            next_move = "right";
                        }
                    }else if (last_move.equals("down")){
                        if (down.equals("X") && left.equals("X") && right.equals("X")){
                            if (ls[L-4].equals("X")){
                                next_move = "left";
                            }else if (ls[L+42].equals("X")){
                                next_move = "down";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (down.equals("X") && left.equals("X")){
                            if (ls[L+42].equals("X")){
                                next_move = "down";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }
                        }else if (down.equals("X") && right.equals("X")){
                            if (ls[L+42].equals("X")){
                                next_move = "down";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (left.equals("X") && right.equals("X")){
                            if (ls[L+7].equals("X")){
                                next_move = "right";
                            }else if (ls[L-6].equals("X")){
                                next_move = "left";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (down.equals("X")){
                            next_move = "down";
                        }else if (left.equals("X")){
                            next_move = "left";
                        }else if (right.equals("X")){
                            next_move = "right";
                        }
                    }else if (last_move.equals("left")){
                        if (up.equals("X") && down.equals("X") && left.equals("X")){
                            if (ls[L-4].equals("X")){
                                next_move = "left";
                            }else if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L+42].equals("X")){
                                next_move = "down";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }
                        }else if (up.equals("X") && down.equals("X")){
                            if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L+42].equals("X")){
                                next_move = "down";
                            }
                        }else if (up.equals("X") && left.equals("X")){
                            if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }
                        }else if (left.equals("X") && down.equals("X")){
                            if (ls[L+42].equals("X")){
                                next_move = "down";
                            }else if (ls[L-2].equals("X")){
                                next_move = "left";
                            }
                        }else if (up.equals("X")){
                            next_move = "up";
                        }else if (left.equals("X")){
                            next_move = "left";
                        }else if (down.equals("X")){
                            next_move = "down";
                        }
                    }else if (last_move.equals("right")){
                        if (up.equals("X") && down.equals("X") && right.equals("X")){
                            if (ls[L-84].equals("X")){
                                next_move = "down";
                            }else if (ls[L+84].equals("X")){
                                next_move = "up";
                            }else if (ls[L+4].equals("X")){
                                next_move = "right";
                            }else if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L+42].equals("X")){
                                next_move = "down";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (up.equals("X") && down.equals("X")){
                            if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L+42].equals("X")){
                                next_move = "down";
                            }
                        }else if (up.equals("X") && right.equals("X")){
                            if (ls[L+6].equals("X")){
                                next_move = "right";
                            }else if (ls[L-105].equals("X")){
                                next_move = "up";
                            }else if (ls[L-42].equals("X")){
                                next_move = "up";
                            }else if (ls[L+2].equals("X")){
                                next_move = "right";
                            }
                        }else if (right.equals("X") && down.equals("X")){
                            if (ls[L+42].equals("X") && ls[L+2].equals("X")){
                                if (ls[L+63].equals("X")){
                                    next_move = "down";
                                }else{
                                    next_move = "right";
                                }
                            }else{
                                if (ls[L+42].equals("X")){
                                    next_move = "down";
                                }else if (ls[L+2].equals("X")){
                                    next_move = "right";
                                }
                            }
                        }else if (up.equals("X")){
                            next_move = "up";
                        }else if (down.equals("X")){
                            next_move = "down";
                        }else if (right.equals("X")){
                            next_move = "right";
                        }
                    }
                }
            
                if (next_move.equals("up")){
                    if (entry_flag == 1){
                        entry_flag = 0;
                        multiple_y += distance_pixel*(I+1)*speed;
                        multiple_x += 31;
                        multiple_y -= distance_pixel;
                        multiple_y += 33;
                    }

                    if (type.equals("gremlin")){
                        image(this.gremlin, multiple_x, multiple_y);
                    }else if (type.equals("beetle")){
                        image(this.beetle, multiple_x, multiple_y);
                    }else if (type.equals("worm")){
                        image(this.worm, multiple_x, multiple_y);
                    }

                    if (pause_flag == true){
                        multiple_y = multiple_y;
                    }else{
                        multiple_y -= speed;
                        last_move = "up";
                    }
                }else if (next_move.equals("down")){
                    if (entry_flag == 1){
                        entry_flag = 0;
                        multiple_y -= distance_pixel*(I+1)*speed;
                        multiple_y += distance_pixel;
                        multiple_y -= 35;
                        multiple_x += 31;
                    }

                    if (type.equals("gremlin")){
                        image(this.gremlin, multiple_x, multiple_y);
                    }else if (type.equals("beetle")){
                        image(this.beetle, multiple_x, multiple_y);
                    }else if (type.equals("worm")){
                        image(this.worm, multiple_x, multiple_y);
                    }
                    
                    if (pause_flag == true){
                        multiple_y = multiple_y;
                    }else{
                        multiple_y += speed;
                        last_move = "down";
                    }
                }else if (next_move.equals("left")){
                    if (entry_flag == 1){
                        entry_flag = 0;
                        multiple_x += distance_pixel*(I+1)*speed;
                        multiple_x -= distance_pixel;
                        multiple_x += 60;
                    }

                    if (type.equals("gremlin")){
                        image(this.gremlin, multiple_x, multiple_y);
                    }else if (type.equals("beetle")){
                        image(this.beetle, multiple_x, multiple_y);
                    }else if (type.equals("worm")){
                        image(this.worm, multiple_x, multiple_y);
                    }

                    if (pause_flag == true){
                        multiple_x = multiple_x;
                    }else{
                        multiple_x -= speed;
                        last_move = "left";
                    }
                }else if (next_move.equals("right")){
                    if (entry_flag == 1){
                        entry_flag = 0;
                        multiple_x -= distance_pixel*(I+1)*speed;
                        multiple_x += distance_pixel;
                    }

                    if (type.equals("gremlin")){
                        image(this.gremlin, multiple_x, multiple_y);
                    }else if (type.equals("beetle")){
                        image(this.beetle, multiple_x, multiple_y);
                    }else if (type.equals("worm")){
                        image(this.worm, multiple_x, multiple_y);
                    }

                    if (pause_flag == true){
                        multiple_x = multiple_x;
                    }else{
                        multiple_x += speed;
                        last_move = "right";
                    }
                }

                int n = 0;
                while (n < 10){
                    monster_count1 = monster_count%pass_pixel_time;
                    if (map_out_flag == 1){
                        if (multiple_x >= m.get_start_x()+2 && multiple_y >= m.get_start_y() && last_move.equals("right")){
                            map_out_flag = 0;
                            monster_count = 0;
                            break;
                        }else if (last_move.equals("down") && multiple_x >= m.get_start_x() && multiple_y >= m.get_start_y()-32){
                            map_out_flag = 0;
                            monster_count = 0;
                            break;
                        }else if (last_move.equals("up") && multiple_x >= m.get_start_x() && multiple_y <= m.get_start_y()+32){
                            map_out_flag = 0;
                            monster_count = 0;
                            break;
                        }else if (last_move.equals("left") && multiple_x <= m.get_start_x()+64 && multiple_y >= m.get_start_y()){
                            map_out_flag = 0;
                            monster_count = 0;
                            break;
                        }
                    }
                    if (monster_count1+0.1 >= pass_pixel_time && map_out_flag != 1){
                        if (last_move.equals("up")){
                            L -= 21;
                            if (pause_flag == true){
                                L += 21;
                            }
                        }else if (last_move.equals("down")){
                            L += 21;
                            if (pause_flag == true){
                                L -= 21;
                            }
                        }else if (last_move.equals("left")){
                            L -= 1;
                            if (pause_flag == true){
                                L += 1;
                            }
                        }else if (last_move.equals("right")){
                            L += 1;
                            if (pause_flag == true){
                                L -= 21;
                            }
                        }
                    }
                    n += 1;
                    monster_count += 0.1;
                    if (pause_flag == true){
                        monster_count -= 0.1;
                    }
                }

                m.set_monster_x(multiple_x);
                m.set_monster_y(multiple_y);
                m.set_position((float) L);
                m.set_last_move(last_move);
                m.set_next_move(next_move);
                m.set_entry_flag(entry_flag);
                m.set_map_out_flag(map_out_flag);
                m.set_monster_count(monster_count);
                I += 1;
            }
        a += 1;
        }
    }
        
    /**
     * store the random value which used to decide the spawn point for each monster
     */
    public void store_random(){
        JSONArray ls = json.getJSONArray("waves");
        JSONObject ls1 = ls.getJSONObject(Q);
        JSONArray ls2 = ls1.getJSONArray("monsters");
        JSONObject ls3 = ls2.getJSONObject(0);
        store_random_ls = new float[ls3.getInt("quantity")];
        int i = 0;
        while (i < (ls3.getInt("quantity"))){
            store_random_ls[i] = random.nextFloat();
            i += 1;
        }
    }

    /**
     * extracting the level file into a String array.
     */
    public void extract_level(){
        int length1 = 0;
        int length2 = 0;
        int length3 = 0;
        int length4 = 0;
        int length5 = 0;
        int length6 = 0;
        int line_count = 0;
        int count = 0;

        try{
            String[] ls1 = new String[420];
            File f1 = new File(level);
            Scanner scan1 = new Scanner(f1);
            while (scan1.hasNextLine()){
                String next1 = scan1.nextLine();
                int length = next1.length();
                if (next1.contains("\r") == true){
                    length -= 1;
                }
                if (length > 0 && length != 20){
                    length2 = 20 - length;
                    if (count == 0){
                        length3 = length2;
                        count += 1;
                    }else if (count == 1){
                        length4 = length2;
                        count += 1;
                    }else if (count == 2){
                        length5 = length2;
                        count += 1;
                    }else if (count == 3){
                        length6 = length2;
                        count += 1;
                    }

                    while (length2 != 0){
                        ls1[length1+length+length2+line_count-1] = " ";
                        length2 -= 1;
                    }
                }
                length1 += 20;
                line_count += 1;
            }

            ls = ls1;
            File f = new File(level);
            Scanner scan = new Scanner(f);
            scan.useDelimiter("");

            int i = 0;
            int c = 0;
            while (scan.hasNext() && i < 420){
                String next = scan.next();
                if (next.equals("\r")){
                    continue;
                }
                if (ls[i] == null){
                    ls[i] = next;
                    i += 1;
                }else if (ls[i].equals(" ") && c == 0){
                    i += length3;
                    ls[i] = next;
                    i += 1;
                    c += 1;
                }else if (ls[i].equals(" ") && c == 1){
                    i += length4;
                    ls[i] = next;
                    i += 1;
                    c += 1;
                }else if (ls[i].equals(" ") && c == 2){
                    i += length5;
                    ls[i] = next;
                    i += 1;
                    c += 1;
                }else if (ls[i].equals(" ") && c == 3){
                    i += length6;
                    ls[i] = next;
                    i += 1;
                    c += 1;
                }else{
                    i += 1;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * draw the map based on the list extracted from the level file.
     */
    public void map(){
        int start_x = 0;
        int start_y = 40;
        int house_x = 0;
        int house_y = 0;
        int multiple_x = 0;
        int multiple_y = 0;
        int entry_number = 0;
        int l = 0;
        int current = 0;

        while (ls[l] != null && l < 419){
            String now = ls[l];
            if ((now).equals("\n")){
                multiple_x = 0;
                multiple_y += 1;
            }else if ((now).equals("S")){
                image(this.shrub,start_x+multiple_x*32,start_y+multiple_y*32);
                multiple_x += 1;
            }else if ((now).equals("W")){
                image(this.grass,start_x+multiple_x*32,start_y+multiple_y*32);
                house_x = start_x-1+multiple_x*32;
                house_y = start_y+multiple_y*32-10;
                multiple_x += 1;
                current = l;
            }else if ((now).equals(" ")){
                image(this.grass,start_x+multiple_x*32,start_y+multiple_y*32);
                multiple_x += 1;
            }else if ((now).equals("X")){
                int adjacent_x = 0;
                String up = " ";
                String down = " ";
                String left = " ";
                String right = " ";
                if ((l - 21) >= 0){
                    up = ls[l-21];
                    if (up.equals("X")){
                        adjacent_x += 1;
                    }
                }
                if ((l + 21) < 420){
                    down = ls[l + 21];
                    if (down.equals("X")){
                        adjacent_x += 1;
                    }
                }
                if ((l - 1) >= 0){
                    left = ls[l - 1];
                    if (left.equals("X")){
                        adjacent_x += 1;
                    }
                }
                if ((l+1) < 420){
                    right = ls[l+1];
                    if (right.equals("X")){
                        adjacent_x += 1;
                    }
                }
                if ((multiple_x == 0 || multiple_y == 0 || multiple_x == 19 || multiple_y == 19) && adjacent_x == 1){
                    float[] ls2 = new float[3];
                    ls2[0] = (float) (multiple_x*32+start_x-25);
                    ls2[1] = (float) (multiple_y*32+start_y+5);
                    ls2[2] = (float) l;
                    monster_hash.put(entry_number, ls2);
                    entry_number += 1;
                }
                if (up.equals("X") && down.equals("X") && left.equals("X") && right.equals("X")){
                    image(this.path3,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (down.equals("X") && left.equals("X") && right.equals("X")){
                    image(this.path2,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (up.equals("X") && down.equals("X") && left.equals("X")){
                    image(this.rotate_90_path2,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (up.equals("X") && down.equals("X") && right.equals("X")){
                    image(this.rotate_270_path2,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (up.equals("X") && right.equals("X") && left.equals("X")){
                    image(this.rotate_180_path2,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (down.equals("X") && (left.equals("X") || left.equals("W"))){
                    image(this.path1,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (right.equals("X") && left.equals("X")){
                    image(this.path0,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (up.equals("X") && down.equals("X")){
                    image(this.rotate_90_path0,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (down.equals("X") && (right.equals("X") || right.equals("W"))){
                    image(this.rotate_270_path1,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (up.equals("X") && (left.equals("X") || left.equals("W"))){
                    image(this.rotate_90_path1,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (up.equals("X") && (right.equals("X") || right.equals("W"))){
                    image(this.rotate_180_path1,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (left.equals("X") || right.equals("X")){
                    image(this.path0,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }else if (up.equals("X") || down.equals("X")){
                    image(this.rotate_90_path0,start_x+multiple_x*32,start_y+multiple_y*32);
                    multiple_x += 1;
                }
            }else{
                multiple_x += 1;
            }
            l += 1;
        }

        String up1 = " ";
        String down1 = " ";
        String left1 = " ";
        String right1 = " ";
        if ((current - 21) >= 0){
            up1 = ls[current-21];
        }
        if ((current + 21) < 419){
            down1 = ls[current + 21];
        }
        if ((current - 1) >= 0){
            left1 = ls[current-1];
        }
        if ((current + 1) < 420){
            right1 = ls[current+1];
        }

        if (up1.equals("X")){
            image(this.rotate_270_wizard_house,house_x-5,house_y+6);
            wizard_house_x = house_x-5;
            wizard_house_y = house_y+6;
        }else if (down1.equals("X")){
            image(this.rotate_90_wizard_house,house_x-5,house_y+16);
            wizard_house_x = house_x-5;
            wizard_house_y = house_y+16;
        }else if (left1.equals("X")){
            image(this.rotate_180_wizard_house,house_x-17,house_y+2);
            wizard_house_x = house_x-17;
            wizard_house_y = house_y+2;
        }else{
            image(this.wizard_house,house_x-10,house_y+3);
            wizard_house_x = house_x-10;
            wizard_house_y = house_y+3;
        }
        entryNum = entry_number;
    }

    /**
     * display wave count at the top left corner of GUI.
     */
    public void wave(){
        if (sleep_count1 == (int) (pre_wave_pause*60) && wave_count == 1 && sleep_count == 31){
            image(this.back1, 0, 0);
        }else if (sleep_count1 == 59){
            image(this.back1, 0, 0);
        }
        textSize(25);
        fill(0);
        text(" Wave "+wave_count+" starts: "+(int) pre_wave_pause,0,32);
        sleep_count += 1;
    }

    public void setWaveValue(){
        JSONArray ls = json.getJSONArray("waves");
        config_size = ls.size();
        JSONObject ls1 = ls.getJSONObject(q);
        pre_wave_pause = ls1.getFloat("pre_wave_pause");
    }

    /**
     * controlling the selected and unselected state of buttons at right side bar.
     */
    public void right_side_bar(){
        float mouse_x = mouseX;
        float mouse_y = mouseY;
        if (right_bar.get_accelerate_flag() == true){
            image(this.right_side_bar2,650,45);
        }else{
            if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 45 && mouse_y <= 87){
                image(this.right_side_bar3,650,45);
            }else{
                image(this.right_side_bar1,650,45);
            }
        }

        if (right_bar.get_pause_flag() == true){
            image(this.right_side_bar2,650,95);
        }else{
            if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 95 && mouse_y <= 137){
                image(this.right_side_bar3,650,95);
            }else{
                image(this.right_side_bar1,650,95);
            }
        }

        if (right_bar.get_build_tower_flag() == true){
            image(this.right_side_bar2,650,145);
        }else{
            if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 145 && mouse_y <= 187){
                image(this.right_side_bar3,650,145);
                fill(255);
                stroke(0);
                rect(560,145,70,22);
                fill(0);
                textSize(13);
                text("Cost: "+100,564,162);
            }else{
                image(this.right_side_bar1,650,145);
            }
        }

        if (right_bar.get_upgrade_range_flag() == true){
            image(this.right_side_bar2,650,195);
        }else{
            if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 195 && mouse_y <= 237){
                image(this.right_side_bar3,650,195);
            }else{
                image(this.right_side_bar1,650,195);
            }
        }

        if (right_bar.get_upgrade_speed_flag() == true){
            image(this.right_side_bar2,650,245);
        }else{
            if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 245 && mouse_y <= 287){
                image(this.right_side_bar3,650,245);
            }else{
                image(this.right_side_bar1,650,245);
            }
        }

        if (right_bar.get_upgrade_damage_flag() == true){
            image(this.right_side_bar2,650,295);
        }else{
            if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 295 && mouse_y <= 337){
                image(this.right_side_bar3,650,295);
            }else{
                image(this.right_side_bar1,650,295);
            }
        }

        if (mouse_x <= 692 && mouse_x >= 651 && mouse_y >= 345 && mouse_y <= 387){
            image(this.right_side_bar3,650,345);
            fill(255);
            stroke(0);
            rect(560,345,70,22);
            fill(0);
            textSize(13);
            text("Cost: "+mana_pool_spell_cost,564,362);
        }else{
            image(this.right_side_bar1,650,345);
        }

        textSize(27);
        fill(0);
        text("FF",655, 80);
        text("P",655,130);
        text("T",655,180);
        text("U1",655,230);
        text("U2",655,280);
        text("U3",655,330);
        text("M",655,380);
        textSize(12);
        text("2x speed",700,65);
        text("PAUSE",700,115);
        text("Build",700,165);
        text("tower",700,180);
        text("Upgrade",700,215);
        text("range",700,230);
        text("Upgrade",700,265);
        text("speed",700,280);
        text("Upgrade",700,315);
        text("damage",700,330);
        text("Mana pool",700,365);
        text("cost: "+mana_pool_spell_cost,700,380);
    }


    public static void main(String[] args) {
        PApplet.main("WizardTD.App");
    }

    /**
     * Source: https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * @param pimg The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = this.createImage(newWidth, newHeight, ARGB);
        //BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }

        return result;
    }
}
