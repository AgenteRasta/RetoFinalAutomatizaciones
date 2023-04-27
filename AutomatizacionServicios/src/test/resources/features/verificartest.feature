Feature: Verificar test
  yo como administrador que usa los servicios rest de la web
  quiero relaizar peticiones al servicio post de test
  para verificar que el test cumpla los requerimientos

  @CrearPregunta
  Scenario Outline: Crear un test
    Given que estoy apuntando con un endpoint de test a la api
    When cuando envio el <token> que corrsponde a un usuario de <nivel>
    Then recibo el <code> de codigo de respuesta
    And la informacion de la pregunta creada o un mensaje de error
    Examples:
      | token       | nivel | code |
      | "XFNH-7268" | 1     | 200  |
      | "EEPJ-7593" | 2     | 200  |
      | "NKDR-7943" | 3     | 200  |
      | "NKDR-794"  | 3     | 404  |
      | ""          | 3     | 404  |
