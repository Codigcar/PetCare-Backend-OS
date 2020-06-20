Feature: La aplicacion permite el registro de una persona
  Yo como dueño de una mascota quiero registrar mis datos para acceder a los servicios del sitio
 Scenario Outline: Registro de un usuario dueño de mascotas
   Given El usuario ingresa a la aplicacion web
   When EL usuario crea su perfil con name <name>, password <password>, lastname <lastname>, document <document>, email <email>, phone <phone> , age <age>
   Then Verficar si se ha creado un perfil de usuario

    Examples:
      | name   | password   | lastname  | document | email                    | phone     | age |
      | name2 | password11 | lastname2   | 76151141 | carlos2323a333s@gmail.com | 943400391 | 55  |