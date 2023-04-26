Feature: Ver la informacion de todas las preguntas
  yo como administrador que usa los servicios rest de la web
  quiero relaizar peticiones al servicio get-all
  para ver un listado con todas las preguntas

  Scenario: Ver informacion de todas las preguntas
    Given que estoy apuntando con un endpoint al servicio rest
    When envio la peticion get con el recurso del servicio
    Then recibo todas las preguntas
