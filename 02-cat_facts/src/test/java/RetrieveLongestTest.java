import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import zest.CatFactsRetriever;
import zest.HttpUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class RetrieveLongestTest {


    MockedStatic<HttpUtil> httpUtil = mockStatic(HttpUtil.class);

    // T1
    @Test
    void retrieveRandomTestGetReturnsNull()  {
        String httpUtilGetResponse = null;

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/facts?limit=" + "1"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        assertThrows(NullPointerException.class, () -> {
            catFactsRetriever.retrieveLongest(1);
        });
    }

    // T2
    @Test
    void retrieveRandomTestGetReturnsEmptyJSONObject()  {
        String httpUtilGetResponse = "{}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/facts?limit=" + "1"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            catFactsRetriever.retrieveLongest(1);
        });

        String expectedMessage = "An empty JSON Object has been returned by the get() method";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T3
    @Test
    void retrieveLongestTestOneFact() {
        String httpUtilGetResponse = "{\"current_page\":1,\"data\":[{\"fact\":\"Unlike dogs, cats do not have a sweet tooth. Scientists believe this is due to a mutation in a key taste receptor.\",\"length\":114}]}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/facts?limit=" + "1"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        try {
            assertEquals(catFactsRetriever.retrieveLongest(1), "Unlike dogs, cats do not have a sweet tooth. Scientists believe this is due to a mutation in a key taste receptor.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // T4
    @Test
    void retrieveLongestTestTwoFacts() {
        String httpUtilGetResponse = "{\"current_page\":1,\"data\":[{\"fact\":\"Unlike dogs, cats do not have a sweet tooth. Scientists believe this is due to a mutation in a key taste receptor.\",\"length\":114}, {\"fact\":\"When a cat chases its prey, it keeps its head level. Dogs and humans bob their heads up and down. AAAAAAAAAAAA\",\"length\":113}]}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/facts?limit=" + "2"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        try {
            assertEquals(catFactsRetriever.retrieveLongest(2), "Unlike dogs, cats do not have a sweet tooth. Scientists believe this is due to a mutation in a key taste receptor.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // T5
    @Test
    void retrieveLongestTestFiveFacts() {
        String httpUtilGetResponse = "{\"current_page\":1,\"data\":[{\"fact\":\"Unlike dogs, cats do not have a sweet tooth. Scientists believe this is due to a mutation in a key taste receptor.\",\"length\":114}, {\"fact\":\"When a cat chases its prey, it keeps its head level. Dogs and humans bob their heads up and down. AAAAAAAAAAAA\",\"length\":113}, {\"fact\":\"fact3\",\"length\":100}, {\"fact\":\"fact4\",\"length\":50}, {\"fact\":\"fact5\",\"length\":200}]}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/facts?limit=" + "5"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        try {
            assertEquals(catFactsRetriever.retrieveLongest(5), "fact5");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // T6
    @Test
    void retrieveLongestTestLimitIsZero() {
        String httpUtilGetResponse = "{}";

        try {
            httpUtil.when(() -> HttpUtil.get("https://catfact.ninja/facts?limit=" + "0"))
                    .thenReturn(httpUtilGetResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            catFactsRetriever.retrieveLongest(0);
        });

        String expectedMessage = "Limit can't be 0";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

}
