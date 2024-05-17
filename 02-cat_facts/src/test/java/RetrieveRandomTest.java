import org.eclipse.jetty.util.IO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import zest.CatFactsRetriever;
import zest.HttpUtil;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RetrieveRandomTest {

    MockedStatic<HttpUtil> httpUtil = mockStatic(HttpUtil.class);

    // T1
    @Test
    void retrieveRandomTestLongString()  {
        String httpUtilGetResponse = "{\"fact\":\"A cat's normal pulse is 140-240 beats per minute, with an average of 195.\",\"length\":73}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        try {
            assertEquals(catFactsRetriever.retrieveRandom(), "A cat's normal pulse is 140-240 beats per minute, with an average of 195.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // T2
    @Test
    void retrieveRandomTestNullString()  {
        String httpUtilGetResponse = null;

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        assertThrows(NullPointerException.class, () -> {
            catFactsRetriever.retrieveRandom();
        });;
    }

    // T3
    @Test
    void retrieveRandomTestEmptyString()  {
        String httpUtilGetResponse = "";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            catFactsRetriever.retrieveRandom();
        });

        String expectedMessage = "The response of the get call was an empty string";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T4
    @Test
    void retrieveRandomTestEmptyResponse()  {
        String httpUtilGetResponse = "{}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            catFactsRetriever.retrieveRandom();
        });

        String expectedMessage = "An empty JSON Object has been returned by the get() method";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T5
    @Test
    void retrieveRandomTestFactEmpty()  {
        String httpUtilGetResponse = "{\"fact\":\"\",\"length\":73}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        try {
            assertEquals(catFactsRetriever.retrieveRandom(), "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // T6
    @Test
    void retrieveRandomTestFactLenghtOne()  {
        String httpUtilGetResponse = "{\"fact\":\"A\",\"length\":73}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        try {
            assertEquals(catFactsRetriever.retrieveRandom(), "A");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // T7
    @Test
    void retrieveRandomTestFactLenghtTwo()  {
        String httpUtilGetResponse = "{\"fact\":\"A \",\"length\":73}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        try {
            assertEquals(catFactsRetriever.retrieveRandom(), "A ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // T8
    @Test
    void retrieveRandomTestExceptionThrownWhenGetCalled()  {
        Exception httpUtilGetResponse = new IOException();

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/fact"))
                    .thenThrow(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        assertThrows(IOException.class, () -> {
            catFactsRetriever.retrieveRandom();
        });
    }



}
