Feature: Ver informacion de un usuarios en la base de datos
  yo como usuario de la API
  quiero realizar una peticion Get
  para ver mi la informacion

  @GetId
  Scenario Outline: Peticion exitosa
    Given que estoy en el API
    When envio la peticion get con el <id> del usuario
    Then recibo <code> de codigo de respuesta y la informacion del usuario <nombre>, <correo> y <role>
    Examples:
      | id                         | code | nombre   | correo                     | role    |
      | "6448009e290ed9efcf934420" | 200  | "Efrain" | "efsolora@gmail.com"       | "User"  |
      | "64481f6cfbc80a45d9c97945" | 200  | "Andres" | "esogaviria@gmail.com.com" | "Admin" |
      | "64483a9bfbc80a45d9c97a4a" | 200  | "lisa"   | "earrieta58@gmail.com.com" | "User"  |

  Scenario Outline: Peticion fallida
    Given que estoy en el API-Q
    When envio la peticion get con el <id> invalido
    Then recibo <code> de codigo de respuesta
    Examples:
      | id                           | code |
      | "  64481f6cfbc80a45d9c97945" | 500  |
      | ""                           | 404  |
      | "oiuoiuodiuwaid"             | 500  |