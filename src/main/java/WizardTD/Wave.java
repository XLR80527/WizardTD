package WizardTD;

import java.io.*;
import java.util.*;

public class Wave{
    private ArrayList<Monster> monster_ls;

    public Wave(ArrayList<Monster> monster_ls){
        this.monster_ls = monster_ls;
    }

    /**
     * get the arraylist which contain all the monsters within this wave
     * @return monster_ls
     */
    public ArrayList<Monster> get_monster_ls(){
        return (this.monster_ls);
    }
}