package com.nttdata.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;


public class Step extends Parametros{
    RequestSpecification request;
    Response response;
    @Given("dado que me conecto a la {string}")
    public void dado_que_me_conecto_a_la(String url) {
        RestAssured.baseURI=url;
        request = RestAssured.given();
        request.header("Content-Type","application/json");

    }
    @When("ejecuto la solicitud GETcon el path {string} y los params")
    public void ejecuto_la_solicitud_ge_tcon_el_path_y_los_params(String path, io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> formParams;

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        formParams = (listaParametros(list));

        response = request.when().queryParams(formParams)
                .log().all()
                .get(path);

        System.out.print("response: "+ response.asString());
    }
    @Then("valido status {int}, el id {string} y el title {string}")
    public void valido_status_el_id_y_el_title(Integer status, String id, String title) {
        restAssuredThat(response -> response.body("'status'", equalTo(status)));
        System.out.println("Status: " + SerenityRest.lastResponse().body().path("status").toString());
        System.out.println("ID: " + SerenityRest.lastResponse().body().path("id").toString());
        System.out.println("TITLE: " + SerenityRest.lastResponse().body().path("title").toString());

    }
    @Given("que me conecto a la url {string}")
    public void que_me_conecto_a_la_url(String url) {
        RestAssured.baseURI=url;
    }
    @When("ingreso con el path {string}")
    public void ingreso_con_el_path(String path) {
        response = request.when().post(path);
        System.out.print("responde: "+ response.asString());
    }
    @Then("valido el codigo {int}")
    public void valido_el_codigo(Integer status) {
        restAssuredThat(response -> response.body("'status'", equalTo(status)));
        System.out.println("status: " + SerenityRest.lastResponse().body().path("status").toString());
    }
}
