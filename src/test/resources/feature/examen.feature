Feature: validacion Get

  @test1
  Scenario: validar status 200 e imprimir e pantalla los valores de las propiedades Id y Title del body
    Given dado que me conecto a la "https://www.freetogame.com"
    When ejecuto la solicitud GETcon el path "/api/games?platform=pc&category=shooter" y los params
      |parametros  |valor            |
      |Platform    |pc               |
      |Category    |shooter          |
    Then valido status 200, el id "id" y el title "title"

  @test2
  Scenario: caso error 400
    Given que me conecto a la url "https://www.freetogame.com"
    When ingreso con el path "/api/games?platform=pc&category=shooter"
    Then valido el codigo 400