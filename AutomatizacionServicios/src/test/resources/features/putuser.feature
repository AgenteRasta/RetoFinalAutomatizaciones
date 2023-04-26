Feature: Modificar informacion de un usuarios en la base de datos
  yo como usuario de la API
  quiero realizar una peticion Put
  para modificar la informacion de un usuario

  @GetId
  Scenario Outline: Peticion exitosa
    Given que estoy en el API-Reto-Q
    When envio la peticion put con el <id> del usuario con los datos <fullName> <email> <level> <avaliable> <values> <role>
    Then recibo <statuscode> como respuesta
    Examples:
      | id                         | fullName  | email                    | level | avaliable | values | role    | statuscode |
      | "64481f6cfbc80a45d9c97945" | "Andres"  | "esogaviria@gmail.com"   | "3"   | "true"    | "true" | "User"  | 200        |
      | "6448009e290ed9efcf934420" | "Efrains" | "efsolora@gmail.com"     | "2"   | "true"    | "true" | "User"  | 200        |
      | "64483a9bfbc80a45d9c97a4a" | "laura"   | "earrieta5928@gmail.com" | "3"   | "true"    | "true" | "Admin" | 200        |
      | "64483a9bfbc80a45d9c97a4a" | "laura"   | "earrieta5928@gmail.com" | "3"   | "true"    | "true" | "User"  | 200        |