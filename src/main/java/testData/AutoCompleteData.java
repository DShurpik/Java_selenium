package testData;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AutoCompleteData {

    private static final List<String> colors = new ArrayList<>(List.of
            ("Aqua", "Black", "Blue", "Red", "Voilet", "Yellow", "Indigo", "Green", "Magenta", "Purple", "White"));

    @DataProvider(name = "Auto complete colors provider")
    public static Object[][] autoCompleteColorsData() {

        int colorCount = ThreadLocalRandom.current().nextInt(0, colors.size() + 1);
        Collections.shuffle(colors);

        return new Object[][]{
                {colors.subList(0, colorCount)}
        };
    }

    @DataProvider(name = "Single auto complete color provider")
    public static Object[][] singleAutoCompleteColorData() {

        Collections.shuffle(colors);

        return new Object[][]{
                {colors.get(0)}
        };
    }
}
