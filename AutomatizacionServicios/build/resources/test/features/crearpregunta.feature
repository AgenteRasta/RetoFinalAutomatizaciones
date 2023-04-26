Feature: Crear pregunta
  yo como administrador que usa los servicios rest de la web
  quiero relaizar peticiones al servicio post de questions
  para crear una pregunta

  @CrearPregunta
  Scenario Outline: Crear una pregunta
    Given que estoy apuntando con un endpoint a la api
    When cuando envio la informacion de una pregunta mediante el <index>
    Then recibo <code> de codigo de respuesta
    And la informacion de la pregunta creada
    Examples:
      | index | code |
      | 0     | 400  |
      | 1     | 400  |
      | 2     | 400  |
      | 3     | 400  |
      | 4     | 400  |
      | 5     | 400  |
      | 6     | 400  |
      | 7     | 201  |
      | 8     | 201  |
      | 9     | 201  |
      | 10    | 201  |
      | 11    | 201  |
      | 12    | 201  |
      | 13    | 201  |
      | 14    | 201  |
      | 15    | 201  |
      | 16    | 201  |
      | 17    | 201  |
      | 18    | 201  |
      | 19    | 201  |
      | 20    | 400  |

