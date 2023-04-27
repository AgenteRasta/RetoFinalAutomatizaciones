Feature: Actualizar pregunta
  yo como administrador que usa los servicios rest de la web
  quiero relaizar peticiones al servicio post de questions
  para actualizar la info de una pregunta

  Scenario Outline: Actualizar la informacion de una pregunta
    Given que estoy apuntando con un endpoint a la api para actualizar preguntas
    When cuando envio el recurso con <id> de la pregunta que deseo actualizar y la informacion mediante el <index>
    Then recibo un <code> de codigo de respuesta
    And verifico la informacion actualizada <atributo>
    Examples:
      | index | code | id                         | atributo   |
      | 0     | 200  | "6449da276eaf93b08b2a3e8f" | "topic"    |
      | 1     | 200  | "6449da276eaf93b08b2a3e8f" | "level"    |
      | 2     | 200  | "6449da276eaf93b08b2a3e8f" | "type"     |
      | 3     | 200  | "6449da276eaf93b08b2a3e8f" | "sentence" |
      | 4     | 200  | "6449da276eaf93b08b2a3e8f" | "options"  |
      | 5     | 200  | "6449da276eaf93b08b2a3e8f" | "answer"   |
      | 6     | 400  | "6449da276eaf93b08b2a3e8f" | "topic"    |
      | 7     | 400  | "6449da276eaf93b08b2a3e8f" | "level"    |
      | 8     | 400  | "6449da276eaf93b08b2a3e8f" | "type"     |
      | 9     | 400  | "6449da276eaf93b08b2a3e8f" | "sentence" |
      | 10    | 400  | "6449da276eaf93b08b2a3e8f" | "options"  |
      | 11    | 400  | "6449da276eaf93b08b2a3e8f" | "answer"   |
      | 12    | 400  | "6449da276eaf93b08b2a3e8f" | "level"    |


