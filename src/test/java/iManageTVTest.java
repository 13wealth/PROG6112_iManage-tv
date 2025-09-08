
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.streaming_company.DeleteSeries;
import com.streaming_company.JSONRightPanel;
import com.streaming_company.RightPanel;
import com.streaming_company.SearchSeries;
import com.streaming_company.Validations;


public class iManageTVTest 
{

    @Test
    void testSearchSeries() 
    {
        String[] expected = {"S6I2", "SearchSeriesTest", "18", "45"};                                //-Insert data that exists in the JSON file
        String[] result = SearchSeries.searchByID("S6I2");                                 //-Directly calls the searchByID method from SearchSeries class

    //-Expected results should the ID not be found
        assertNotNull(result, "Series should be found");
        assertEquals(expected[0], result[0], "SeriesID mismatch");
        assertEquals(expected[1], result[1], "Name mismatch");
        assertEquals(expected[2], result[2], "AgeRestriction mismatch");
        assertEquals(expected[3], result[3], "Episodes mismatch");
    }

    @Test
    void testSearchSeries_SeriesNotFound() 
    {
        String[] result = SearchSeries.searchByID("SEW2");

        assertNull(result, "Series ID does not exist");
    }

    @Test
    void testUpdateSeries() 
    {
        String[] originalData = {"SJJE", "UpdateSeriesTest", "18", "25"};                                    //-Insert data that exists in the JSON file

        RightPanel panel = new RightPanel();
        panel.setData(originalData);
        panel.storeData(originalData); 
       boolean updated = JSONRightPanel.updateSeries(                                               //-Update the data to your specifications
                                                       "SJJE", 
                                                       "UpdateSeriesTest", 
                                                       "18", 
                                                       "25"
                                                    );
        assertTrue(updated, "Data should be updated");

        String[] result = SearchSeries.searchByID("SJJE");
        assertNotNull(result, "Series should be found after update");
        assertEquals("SJJE", result[0], "SeriesID mismatch");
        assertEquals("UpdateSeriesTest", result[1], "Name was not updated correctly");
        assertEquals("18", result[2], "AgeRestriction was not updated correctly");
        assertEquals("25", result[3], "Episodes was not updated correctly");
    }

    @Test
    void testDeleteSeries() 
    {
        String seriesId = "SAIG";                                                                   //-Insert an ID that exists in the JSON file

        String[] existing = SearchSeries.searchByID(seriesId);                                      //-Searches for the series before deleting it
        assertTrue(existing != null, "Series exists and availiable for deletion");

        boolean deleted = DeleteSeries.deleteSeriesByID(seriesId);                                  //-Deletes the series
        assertTrue(deleted, "Series deleted successfully");

        String[] resultAfterDelete = SearchSeries.searchByID(seriesId);                             //-Searches for the series after deletion
        assertNull(resultAfterDelete, "No series found after deletion");
    }

    @Test
    void testDeleteSeries_SeriesNotFound() 
    {
        String nonExistentSeriesId = "ST2VX";                                                       //-This ID does not exist in the JSON file
        boolean deleted = DeleteSeries.deleteSeriesByID(nonExistentSeriesId);                       //-Attempts to delete the non-existent series

        assertFalse(deleted, "Series is non-existent, nothing to delete");
    }

    @Test
    void testSeriesAgeRestriction_AgeValid() 
    {
        String validAge = "10";                                                                     //-Age must be between 2-18
        String validEpisodes = "12";                                                                //-Has to be any numeric value
        boolean result = Validations.validateData(validAge, validEpisodes);                         //-Calls the method that validates age and episodes

        assertTrue(result, "Age and Episodes are valid");
    }

    @Test
    void testSeriesAgeRestriction_AgeInValid() {
        // Invalid ages
        String[] invalidAges = {"1", "19", "0", "-5", "abc", ""};
        String validEpisodes = "No:10";

        for (String age : invalidAges) {
            boolean result = Validations.validateData(age, validEpisodes);
            assertFalse(result, "Age '" + age + "' should be invalid and return false");
        }
    }
}


