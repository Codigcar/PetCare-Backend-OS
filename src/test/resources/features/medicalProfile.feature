Feature: La app permite el registro de un historal medico
  Scenario Outline: Registro de un historal medico
    Given El veterinario ingresa a la aplicacion web
    When El veterinario registra un historal medico con name <name>, weight <weight>, height <height>, length <length>, eyes <eyes>, breed <breed>, gender <gender>, color <color>, description <description>, photo <photo>, age <age>
    Then Verificar que se ha registrado un historial medico

    Examples:
      |   name   |  weight    | height | length | eyes  | breed  | gender | color | description                 | photo  | age |
      | kled     | 15         |30      | 20     | claros| pitbul | macho  | marron| Necesita buena alimentacion | ajnjsdj| 3   |