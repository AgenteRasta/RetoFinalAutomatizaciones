Feature:Log Out en Product Store

  Scenario: verificar logout
    Given que el usuario esta en la pagina principal
    When el usuario inicia sesion y presiona el boton logout
    Then vuelve a la pagina principal