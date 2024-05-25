package WizardTD;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {
    private App a = new App();

    @Test
    public void lost_condition_test() {
        boolean b = a.lost_condition();
        assert b == false;
    }

    @Test
    public void extract_level_test() {
        assert a.ls[63] == null;
    }

    @Test
    public void win_condition_test(){
        boolean b = a.win_condition();
        assert b == false;
    }

}
