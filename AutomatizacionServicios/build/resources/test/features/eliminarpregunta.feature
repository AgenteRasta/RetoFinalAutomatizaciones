Feature: Eliminar pregunta
  yo como administrador que usa los servicios rest de la web
  quiero relaizar peticiones al servicio delete de questions
  para eliminar una pregunta

  @EliminarPregunta
  Scenario Outline: Eliminar pregunta
    Given que estoy apuntando con un endpoint a la apirest
    When envio la peticion delete con el id de la pregunta mediante su <index>
    Then recibo un <code> de respuesta y elimino la pregunta
    Examples:
      | index | code |
      | 0     | 200  |
      | 1     | 200  |
      | 2     | 200  |
      | 3     | 200  |
      | 4     | 200  |
      | 5     | 200  |
      | 6     | 200  |
      | 7     | 200  |
      | 8     | 200  |
      | 9     | 200  |
      | 10    | 200  |
      | 11    | 200  |
      | 12    | 200  |
      | 13    | 200  |
      | 14    | 200  |
      | 15    | 200  |
      | 16    | 200  |
      | 17    | 200  |
      | 18    | 200  |
      | 19    | 200  |
      | 20    | 200  |