Feature: Crear usuario ne la base de datos
  yo admin de la API
  quiero realizar una peticion Post
  para ver crear u usuario en a BD

  Scenario Outline: Peticion exitosa
    Given que estoy en el API-reto-Q
    When envio la peticion Post con los siguientes datos <fullName> <email> <level> <avaliable> <values> <role> del usuario
    Then recibo <statuscode> y la informacion del usuario creado
    Examples:
      | fullName | email                  | level | avaliable | values | role    | statuscode |
      | "juan"   | "juanes123@gmail.com"  | "2"   | "true"    | "true" | "User"  | 201        |
      | "juan"   | "juan123@gmail.com"    | "2"   | "true"    | "true" | "User"  | 500        |
      | "laura"  | "laurita234@gmail.com" | "3"   | "true"    | "true" | "Admin" | 201        |


  Scenario Outline: Peticion fallida
    Given que estoy en el API-reto
    When envio la peticion Post con los datos <fullName> <email> <level> <avaliable> <values> <role> del usuario
    Then recibo <statuscode>
    Examples:
      | fullName    | email                       | level | avaliable | values  | role    | statuscode |
      | ""          | "cristian1234@gmail.com"    | "2"   | "true"    | "true"  | "User"  | 400        |
      | "Cristian"  | ""                          | "2"   | "true"    | "true"  | "User"  | 400        |
      | "Cristian"  | "@gmail.com"                | "5"   | "true"    | "true"  | "User"  | 400        |
      | "Cristian"  | "12556@gmail.com"           | "2"   | "true"    | ""      | "User"  | 400        |
      | "Cristian"  | "cristian34556@.com"        | "2"   | "true"    | "false" | ""      | 400        |
      | "343243242" | "cristi234556@"             | "2"   | ""        | "true"  | "Admin" | 400       |
      | "dadwada"   | "crian1234556@gmail.com"    | " "   | "true"    | ""      | "User"  | 400        |
      | "Cristian"  | "cristi556@gmail.com"       | "rii" | "false"   | ""      | "Admin" | 400        |
      | "Cristian"  | "cristian1234556@gmail.com" | "-2"  | "332234"  | ""      | "QA"    | 400        |