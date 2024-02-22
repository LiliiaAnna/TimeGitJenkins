package time;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class TimeTest {

	@Test
	public void testGetTotalSecondsGood(){
	int seconds =
	Time.getTotalSeconds("05:05:05:00");
	assertTrue("The seconds were not calculated properly", seconds==18305);
	}

	@Test
	public void testGetTotalSecondsBad()
	{
	assertThrows(
	StringIndexOutOfBoundsException.class,
	()-> {Time.getTotalSeconds("10:00");});
	}
	
	
	//GetMilliseconds
	
	 @Test
	    public void testGetMillisecondsGood() {
	        int milliseconds = Time.getMilliSeconds("12:05:05:05");
	        assertEquals(5, milliseconds, "The milliseconds were not calculated properly");
	    }

	 @ParameterizedTest
	    @ValueSource(strings = {"00:00:00:00", "23:59:59:00"})
	    void testGetMillisecondsBoundary(String candidate) {
	        int milliseconds = Time.getMilliSeconds(candidate);
	        assertTrue(milliseconds >= 0 && milliseconds <= 999, "Milliseconds should be between 0 and 999");
	    }

	    @ParameterizedTest
	    @ValueSource(strings = { "12:34:56:01", "23:59:59:00"})
	    void testGetMillisecondsBad(String candidate) {
	        assertThrows(NumberFormatException.class, () -> {
	            Time.getMilliSeconds("00:00:00:xx");
	        });
	    }
	 


    @ParameterizedTest
    @ValueSource(strings = {"02:30:45:01", "00:59:59:00"})
    void testGetTotalMinutes(String candidate) {
        int totalMinutes = Time.getTotalMinutes(candidate);
        assertEquals(30, 59, totalMinutes, "The total minutes were not calculated properly");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"02:30:45:01", "00:59:00:00"})
    void testGetTotalMinutesBad(String candidate) {
        assertThrows(NumberFormatException.class, () -> {
            Time.getTotalMinutes("01:xx");
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"12:34:56:32"})
    void testGetSeconds(String candidate) {
        int seconds = Time.getSeconds(candidate);
        assertEquals(56, seconds, "The seconds were not calculated properly");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12:34:56:01", "00:00:00:01", "00:00:59:01", "invalidInput", "60:00:00:01"})
    void testGetSecondsBad(String candidate) {
        assertThrows(NumberFormatException.class, () -> {
            Time.getSeconds("00:01:xx");
        });
    }
    

    
    @ParameterizedTest
    @ValueSource(strings = {"05:00:00", "05:15:15", "05:59:59", "05:46:42"})
    void testGetTotalHours(String candidate) {
        int hours = Time.getTotalHours(candidate);
        assertEquals(5, hours, "The hours were not calculated properly");
    }
	
    @ParameterizedTest
    @ValueSource(strings = {"05:00:00", "05:15:15", "05:59:59", "24:00:00"})
    void testGetTotalHoursBad(String candidate) {
        assertThrows(NumberFormatException.class, () -> {
            Time.getTotalHours("x:10");
        });
    }


}
