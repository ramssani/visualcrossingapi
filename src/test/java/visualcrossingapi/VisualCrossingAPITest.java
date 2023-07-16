package visualcrossingapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class VisualCrossingAPITest {

    @Test
    public void VisualCrossingGETAPITest() {

        RestAssured.baseURI = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services";

        Response response = RestAssured.given()
                .param("unitGroup", "metric")
                .param("key", "CNDXFNLWTULJKH2Y9WAL7CZTU")
                .param("contentType", "json")
                .get("/timeline/Tallinn");

        //convert JSON to string
        JsonPath resBody = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("1", resBody.getString("queryCost"), "Both queryCost are not matching");
        softAssert.assertEquals("59.4364", resBody.getString("latitude"), "Both latitude are not matching");
        softAssert.assertEquals("24.7526", resBody.getString("longitude"), "Both longitude are not matching");
        softAssert.assertEquals("Tallinn, Eesti", resBody.getString("resolvedAddress"), "Both resolvedAddress are not matching");
        softAssert.assertEquals("Tallinn", resBody.getString("address"), "Both address are not matching");
        softAssert.assertEquals("Europe/Tallinn", resBody.getString("timezone"), "Both timezone are not matching");
        softAssert.assertEquals("3.0", resBody.getString("tzoffset"), "Both tzoffset are not matching");
        softAssert.assertEquals("Similar temperatures continuing with a chance of rain multiple days.", resBody.getString("description"), "Both description are not matching");
        softAssert.assertEquals("2023-07-16", resBody.getString("days[0].datetime"), "Both datetime are not matching");
        softAssert.assertEquals("1689454800", resBody.getString("days[0].datetimeEpoch"), "Both datetimeEpoch are not matching");
        softAssert.assertEquals("rain", resBody.getString("days[0].preciptype[0]"), "Both preciptype are not matching");
        softAssert.assertEquals("Rain, Partially cloudy", resBody.getString("days[0].conditions"), "Both conditions are not matching");

        softAssert.assertEquals("Jul 16 in evening thunder, locally with intensive rain, also possible hail and heavy gusts.", resBody.getString("alerts[0].description"), "Both alerts are not matching");
        softAssert.assertEquals("2.49.0.0.233.0.EE20230716124501332", resBody.getString("alerts[0].id"), "Both id of alerts are not matching");
        softAssert.assertEquals("50", resBody.getString("stations.EETN.quality"), "Both quality of stations are not matching");
        softAssert.assertEquals("EETN", resBody.getString("stations.EETN.name"), "Both name of stations are not matching");

        softAssert.assertEquals("Overcast", resBody.getString("currentConditions.conditions"), "Both currentConditions of condition are not matching");
        softAssert.assertEquals("cloudy", resBody.getString("currentConditions.icon"), "Both currentConditions of icon are not matching");
        softAssert.assertEquals("[EEEI, EFHK, EETN]", resBody.getString("currentConditions.stations"), "Both name of stations are not matching");

        softAssert.assertEquals(200, response.getStatusCode(), "Status code are matching");

        softAssert.assertAll();
    }
}
