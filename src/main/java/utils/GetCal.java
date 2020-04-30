package utils;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class GetCal {

	public double getCalByExternalCall(String foodName) {

		Response response = RestAssured.given()

				.header("Content-Type", "application/json")

				.header("x-app-id", "c1be1f98")

				.header("x-app-key", "d8bb0849c4ae4eda4d8e061897d1dc1d")

				.body("{\r\n" +

						"              \"query\":\"" + foodName + "\"\r\n" +

						"}")

				.post("https://trackapi.nutritionix.com/v2/natural/nutrients")

				.thenReturn();

		String data = response.body().asString();

		data = data.substring(data.indexOf("nf_calories"));

		return Double.valueOf(data.split(",\"")[0].split(":")[1]);

	}
	
}