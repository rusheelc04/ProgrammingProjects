import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("STUDENT TEST - Case #1")
    public void firstCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList();
        Location firstLoc = new Location("Location #1", 100, 400);
        Location secondLoc = new Location("Location #2", 150, 600);
        loci.add(firstLoc);
        loci.add(secondLoc);


        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);

        Set<Location> actual = Client.allocateRelief(500, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #2")
    public void secondCaseTest() {
        int budget = 1000;
        List<Location> loci = new ArrayList();
        Location firstLoc = new Location("Location #1", 150, 500);
        Location secondLoc = new Location("Location #2", 300, 800);
        Location thirdLoc = new Location("Location #3", 200, 700);
        loci.add(firstLoc);
        loci.add(secondLoc);
        loci.add(thirdLoc);

        Set<Location> expected = new HashSet<Location>();
        expected.add(secondLoc);

        Set<Location> actual = Client.allocateRelief(1000, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #3")
    public void thirdCaseTest() {
        int budget = 2000;
        List<Location> loci = new ArrayList<>();
        Location loc1 = new Location("Location #1", 100, 1000);
        Location loc2 = new Location("Location #2", 150, 500);
        Location loc3 = new Location("Location #3", 200, 800);
        Location loc4 = new Location("Location #4", 250, 900);
        loci.add(loc1);
        loci.add(loc2);
        loci.add(loc3);
        loci.add(loc4);

        Set<Location> expected = new HashSet<>();
        expected.add(loc3);
        expected.add(loc4);

        Set<Location> actual = Client.allocateRelief(2000, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }
}
