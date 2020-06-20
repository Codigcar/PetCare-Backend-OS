Feature: La aplicacion permite el registro de una veterinaria
  Yo como propietario de una veterinaria quiero registrar los datos para acceder a los servicios del sitio
  Scenario Outline: Registro una veterinaria
    Given El usuario se haya registrado
    When El usuario registra los datos de la veterinaria con nombre <name>, region <region>, direccion <address>, campo <field>, email <email>, descripcion <description>
    Then Verificar que los datos de la veterinaria se hayan registrado

    Examples:
    | name |  region  | address | field   |  email | description |
    | cachorros    | region1  | address1| field1  | email1@gmail.com| description1 |