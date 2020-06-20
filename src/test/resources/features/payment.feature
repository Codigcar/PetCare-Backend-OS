Feature: La app permite registrar una tarjeta afiliada
  Yo como veterinario quiero registrar una tarjeta para poder realizar una subscripcion
  Scenario Outline: Registrar tarjeta
    Given El veterinario ingresa a la aplicacion web
    And Se crea un rol con name <name_rol>
    And Se crea una suscripcion con name <sname>, description <sdescription>, duration <sduration>, price <sprice>
    And EL business perteneciente a una veterinaria crea su perfil con name <pname>, password <provider_password>, lastname <plastname>, document <pdocument>, email <pemail>, phone <provider_phone> , age <p_age>, owner <p_owner>

    When El veterinario registra su tarjeta con card number<card_number>, card name <card_name>, cvv number <cvv_number>, expiry date <expiry_date>
    Then Verificar si se ha registrado la tarjeta

    Examples:
      | card_number      | card_name    | cvv_number  | expiry_date    | name_rol    | sname  | sdescription | sduration | sprice | pname     | provider_password      | plastname   | pdocument | pemail                | provider_phone | p_age | p_owner|
      | 491246100427     | guillermo    | 097         | 0324           | veterinario | Basico | plan Basico  | 5         | 29.99  | business1 | password               | lastname1   | 00151141  | business331@gmail.com | 943400301      | 30    | true   |

