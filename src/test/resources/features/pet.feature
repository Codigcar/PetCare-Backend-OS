
Feature: La app permite el registro de una mascota
  Yo como dueño de una mascota quiero registrar los datos de mi mascota para poder acceder a los servicios del sitio
  Scenario Outline: Registro de una mascota
    Given El usuario ingresa a la aplicacion web
    And Se crea un rol con name <namerol>
    And Se crea una suscripcion con name <sname>, description <sdescription>, duration <sduration>, price <sprice>
    And EL usuario crea su perfil con name <name>, password <password>, lastname <lastname>, document <document>, email <email>, phone <phone> , age <age>

    When El usuario registra una mascota con name <petname>, age <petage>, breed <petbreed>, photo <petphoto>, gender <petgender>
    Then Verificar que se ha registrado la nueva mascota


    Examples:
      | name   | password   | lastname  | document    | email                    | phone     | age       |namerol         | sname  | sdescription| sduration  | sprice | petname  | petage | petbreed    | petphoto    | petgender |
      | name2 | password11 | lastname2  | 76151141   | carlos2323a333s@gmail.com | 943400391 | 55        |cliente         | Basico | plan Basico | 5          | 29.99  | nuevamascota2 | 2   | pitbull  | asdasswwasdss  | macho  |
      | chino | csfdfsdfsd |leyva       | 7605334041 | cfas@gmail.com            | 946860691 | 58        |  veterinario   | Premium|plan Premium | 6          | 39.99  |nuevamascota1 | 5   | pastor alemna | xdxdxd | hembra |