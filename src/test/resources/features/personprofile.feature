Feature: La app permite el registro de un usuario
  Yo como usuario quiero registrar mis datos para poder acceder a los servicios del sitio
  Scenario Outline: Registro de un usuario
    Given El usuario ingresa a la aplicacion web
    When El usuario se registra con name <name>, password <password>,lastName <lastName>, document <document>, email <email>, phone<phone>, age<age>
    Then Verificar que se ha registrado el usuario

    Examples:
      | id  | name   | password | lastName | document | email           | phone     | age
      | 1   | carlos | carlos123 |castilla | 76050041 | carlos@gmail.com| 946100691 | 55