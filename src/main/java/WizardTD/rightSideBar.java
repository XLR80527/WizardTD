package WizardTD;

import java.io.*;
import java.util.*;

public class rightSideBar{
    private boolean build_tower_flag;
    private boolean upgrade_speed_flag;
    private boolean upgrade_range_flag;
    private boolean upgrade_damage_flag;
    private boolean accelerate_flag;
    private boolean pause_flag;
    private boolean mana_flag;

    public void set_all_flag_false(){
        this.build_tower_flag = false;
        this.upgrade_range_flag = false;
        this.upgrade_speed_flag = false;
        this.upgrade_damage_flag = false;
        this.accelerate_flag = false;
        this.pause_flag = false;
        this.mana_flag = false;
    }


    public void set_build_tower_flag(){
        if (this.build_tower_flag == true){
            this.build_tower_flag = false;
        }else{
            this.build_tower_flag = true;
        }
    }

    public void set_upgrade_speed_flag(){
        if (this.upgrade_speed_flag == true){
            this.upgrade_speed_flag = false;
        }else{
            this.upgrade_speed_flag = true;
        }
    }
    public void set_upgrade_damage_flag(){
        if (this.upgrade_damage_flag == true){
            this.upgrade_damage_flag = false;
        }else{
            this.upgrade_damage_flag = true;
        }
    }
    public void set_upgrade_range_flag(){
        if (this.upgrade_range_flag == true){
            this.upgrade_range_flag = false;
        }else{
            this.upgrade_range_flag = true;
        }
    }

    public boolean get_build_tower_flag(){
        return (this.build_tower_flag);
    }

    public boolean get_upgrade_speed_flag(){
        return (this.upgrade_speed_flag);
    }

    public boolean get_upgrade_range_flag(){
        return (this.upgrade_range_flag);
    }

    public boolean get_upgrade_damage_flag(){
        return (this.upgrade_damage_flag);
    }

    public boolean get_accelerate_flag(){
        return (this.accelerate_flag);
    }

    public boolean get_pause_flag(){
        return (this.pause_flag);
    }

    public void set_accelerate_flag(){
        if (this.accelerate_flag == true){
            this.accelerate_flag = false;
        }else{
            this.accelerate_flag = true;
        }
    }

    public void set_pause_flag(){
        if (this.pause_flag == true){
            this.pause_flag = false;
        }else{
            this.pause_flag = true;
        }
    }

    public void set_mana_flag(){
        if (this.mana_flag == true){
            this.mana_flag = false;
        }else{
            this.mana_flag = true;
        }
    }

    public boolean get_mana_flag(){
        return (this.mana_flag);
    }
}