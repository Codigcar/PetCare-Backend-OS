Feature: La aplicacion permite el registro de un usuario
  Yo como usuario quiero registrar mis datos para acceder a los servicios del sitio
  Scenario Outline: Registro de un usuario perteneciente a una veterinaria
    Given El usuario ingresa a la aplicacion web
    When EL usuario perteneciente a una veterinaria crea su perfil con name <name>, password <password>, lastname <lastname>, document <document>, email <email>, phone <phone> , age <age>, owner <owner>
    Then Verficar si se ha creado un perfil del usuario

    Examples:
      | name   | password   | lastname  | document | email                    | phone     | age | owner |
      | business1 | password | lastname1   | 00151141 | business331@gmail.com | 943400301 | 30  | true |