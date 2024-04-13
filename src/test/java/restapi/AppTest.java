package restapi;

// import org.junit.FixMethodOrder;
// import org.junit.Test;
// import org.junit.runner.OrderWith;
// import org.junit.runners.MethodSorters;
// import org.junit.runners.Parameterized.Parameter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.utils.ExcelUtils;

import org.testng.Assert;
//import junit.framework.*;
import org.testng.annotations.*;




public class AppTest {
    
    
    public static int id;
    ExcelUtils excel;

    // Constructor
    public AppTest(){
        excel = new ExcelUtils();
    }

    // Get data from API
    @Test(priority = 2)
    
    public void getDataFromAPI(){
        RestAssured.baseURI = excel.getCellData("baseURI");
        String getPathParam = excel.getCellData("getPathParam");
        String status = excel.getCellData("getPutPatchStatusCode");
        System.out.println(status);
        int statusCode = Integer.parseInt(status);

        Response response = RestAssured.given().contentType(ContentType.JSON)
                            .when().get(getPathParam).then().extract().response();


        System.out.println(response);
        Assert.assertTrue(response.getStatusCode()==statusCode);
        System.out.println(response.getStatusCode());
        


    }

    // Post data to API
    @Test(priority = 1)
    public void postDataToAPI(){
        RestAssured.baseURI = excel.getCellData("baseURI");
        String token = excel.getCellData("token");
        System.out.println(token);
        String name = excel.getCellData("name");
        String email = excel.getCellData("email");
        String gender = excel.getCellData("gender");
        String status = excel.getCellData("status");
        String putPathParam = excel.getCellData("pathParameter");
        String code = excel.getCellData("postStatusCode");
        int StatusCode = Integer.parseInt(code);
        Response response = RestAssured.given().header("Authorization",token).contentType(ContentType.JSON).body(
            "{\"name\": \""+name+"\"\n\r" +
            ",\"email\": \""+email+"\",\n\r" +
            "\"gender\": \""+gender+"\",\n\r" +
            "\"status\": \""+status+"\"}").when().post(putPathParam).then().extract().response();

        System.out.println(response);
        System.out.println( "Status Code: " + response.getStatusCode());

        Assert.assertTrue(response.getStatusCode()==StatusCode);
        id = response.jsonPath().getInt("id");
        System.out.println(response.jsonPath().getInt("id"));


    }

    @Test(priority = 3)
    public void putDataToAPI(){
        RestAssured.baseURI = excel.getCellData("baseURI");
        String token = excel.getCellData("token");
        System.out.println("Id: " + id);
        String name = excel.getCellData("putName");
        String gender = excel.getCellData("putGender");
        String status = excel.getCellData("putStatus");
        String putPathParam = excel.getCellData("pathParameter");
        String code = excel.getCellData("getPutPatchStatusCode");
        int statusCode = Integer.parseInt(code);
        Response response = RestAssured.given().header("Authorization",token).contentType(ContentType.JSON).body(
            "{\"name\": \""+name+"\",\n\r" +
            "\"gender\": \""+gender+"\",\n\r" +
            "\"status\": \""+status+"\"}").when().put(putPathParam+id).then().extract().response();

            //System.out.println(response.jsonPath().getInt("id"));
            System.out.println("Status Code: " +response.getStatusCode());

            Assert.assertTrue(response.getStatusCode()==statusCode);
    }


    @Test(priority = 4)
    public void patchDataToAPI(){
        RestAssured.baseURI = excel.getCellData("baseURI");
        String token = excel.getCellData("token");
        String patchPathParam = excel.getCellData("pathParameter");
        String code = excel.getCellData("getPutPatchStatusCode");
        int statusCode = Integer.parseInt(code);
        String name = excel.getCellData("patchName");
        Response response = RestAssured.given().header("Authorization",token).contentType(ContentType.JSON).body(
            "{\"name\":\""+name+"\"}").when().patch(patchPathParam+id).then().extract().response();

        System.out.println(response.jsonPath().getString("email"));
        Assert.assertTrue(response.statusCode()==statusCode);
    }

    @Test(priority = 5)
    public void deleteDataFromAPI(){
        RestAssured.baseURI = excel.getCellData("baseURI");
        String token = excel.getCellData("token");
        String deletechPathParam = excel.getCellData("pathParameter");
        String code = excel.getCellData("deleteStatusCode");
        int statusCode = Integer.parseInt(code);

        Response response = RestAssured.given().header("Authorization", token).contentType(ContentType.JSON).when().delete(deletechPathParam+id).then().extract().response();

        System.out.println(response.getStatusCode());
        Assert.assertTrue(response.getStatusCode()==statusCode);
    }

    



}
    