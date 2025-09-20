package com.example.alakzatgui;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AlakzatControllerTest {

    @Before
    public void tesztKezdes() {
        AlakzatController.isRunningTest = true;
    }

    @Test
    public void testAddAlakzat() throws IOException {
        AlakzatController.isRunningTest = true;

        AlakzatController.TesztSzin = "Piros";
        AlakzatController.TesztAlakzat = "Kör";
        AlakzatController.tesztIndex = 0;

        AlakzatController controller = new AlakzatController();
        controller.onHozaadClick(null);
        assert !AlakzatController.tesztLista.isEmpty();

        controller.onTorolClick(null);
        assert AlakzatController.tesztLista.isEmpty();

        controller.initialize(null, null);
        assert !AlakzatController.tesztLista.isEmpty();
        controller.onSaveButton(null);

    }

    @After
    public void tesztBef() {
        AlakzatController.isRunningTest = false;
    }
}
