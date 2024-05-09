package zest;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class CatFactsRetriever {

    /**
     * Returns a String containing a random fact about cats
     * as retrieved from the catfact.ninja API.
     *
     * @return      a random fact about cats
     */
    public String retrieveRandom() throws IOException {
        String response = HttpUtil.get("https://catfact.ninja/fact");
        JSONObject jo = new JSONObject(response);
        return jo.getString("fact");
    }

    /**
     * Returns a String containing the longest fact about cats
     * as retrieved by querying a list of limit facts from the
     * catfact.ninja API.
     *
     * @param limit the maximum number of facts to retrieve from
     *              the API
     * @return      the longest fact from the list
     */
    public String retrieveLongest(int limit) throws IOException {
        String response = HttpUtil.get("https://catfact.ninja/facts?limit=" + String.valueOf(limit));
        JSONArray ja = new JSONObject(response).getJSONArray("data");

        int length = 0;
        String longestFact = "";
        for (Object e: ja) {
            if (e instanceof JSONObject) {
                JSONObject jo = (JSONObject) e;
                if (jo.getInt("length") > length) {
                    longestFact = jo.getString("fact");
                    length = jo.getInt("length");
                }
            }
        }

        return longestFact;
    }

}

