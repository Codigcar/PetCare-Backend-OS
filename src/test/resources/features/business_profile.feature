Feature: La aplicacion permite el registro de un business
  Yo como business quiero registrar mis datos para acceder a los servicios del sitio
  Scenario Outline: Registro de un business perteneciente a una veterinaria
    Given El business ingresa a la aplicacion web
    And Se crea un rol con name <namerol>
    And Se crea una suscripcion con name <sname>, description <sdescription>, duration <sduration>, price <sprice>
    When EL business perteneciente a una veterinaria crea su perfil con name <name>, password <password>, lastname <lastname>, document <document>, email <email>, phone <phone> , age <age>, owner <owner>
    Then Verficar si se ha creado un perfil del business

    Examples:
      | name   | password   | lastname  | document | email                    | phone     | age | owner |namerol | sname   | sdescription | sduration | sprice |
      | business1 | password | lastname1   | 00151141 | business331@gmail.com | 943400301 | 30  | true |veterinario    | Basico | plan Basico | 5             | 29.99 |
      | business2 | password2 | lastname2   | 00151142 | business221@gmail.com | 943400302 | 30  | true |cliente   | Premium|plan Premium | 6          | 39.99 |