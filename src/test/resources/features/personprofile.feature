Feature: La app permite crear un perfil de un usuario
  Yo como usuario quiero crear un perfil con mis datos para poder acceder a los servicios del sitio
  Scenario Outline: Crear perfil de un usuario
    Given El usuario ingresa a la aplicacion web
    When EL usuario crea su perfil con name <name>, password <password>,lastName <lastName>, document <document>, email <email>, phone<phone>, age<age>
    Then Verficar si se ha creado un perfil de usuario

    Examples:
      | name   | password | lastName | document | email           | phone     | age |
      | carlos | carlos123 |castilla | 76050041 | carlos@gmail.com| 946100691 | 55  |