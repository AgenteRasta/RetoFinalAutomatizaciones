Feature: Ver informacion de un juego de pokemon
  yo como administrador que usa los servicios rest de la pokeapi
  quiero relaizar peticiones al servicio get de los juegos de pokemon
  para ver toda la informacion de un juego

  @PokeApi
  Scenario Outline: Ver informacion de un juego de pokemon
    Given que estoy apuntando con un endpoint a la pokeapi
    When envio la peticion get con el <id> del juego
    Then recibo <code> de codigo de respuesta y la informacion del juego
    Examples:
      | id    | code |
      | "1"   | 200  |
      | "2"   | 200  |
      | "3"   | 200  |
      | "200" | 404  |