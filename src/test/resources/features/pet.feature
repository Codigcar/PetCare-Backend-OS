Feature: La app permite el registro de una mascota
  Yo como dueño de una mascota quiero registrar los datos de mi mascota para poder acceder a los servicios del sitio
  Scenario Outline: Registro de una mascota
    Given El usuario se haya registrado
    When El usuario registra una mascota con name <name>, age <age>, breed <breed>, photo <photo>, gender <gender>
    Then Verificar que se ha registrado la nueva mascota


    Examples:
        | name  | age | breed    | photo    | gender |
        | nuevamascota2 | 2   | pitbull  | asdasswwasdss  | macho  |