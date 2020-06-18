Feature: La app permite el registro de una veterinaria
  Yo como veterinario quiero registrar los datos de mi vetirinaria para poder acceder a los servicios del sitio
  Scenario Outline: Registro de una veterinaria
    Given El veterinario ingresa a la aplicacion web
    When El veterinario registra una veterinaria con address <address>, business name <business_name>, description <description>, email <email>, field <field>, region <region>
    Then Verificar que se ha registrado la veterinaria

    Examples:
      |      address     | business_name |     description    |          email        |       field      |  region  |
      | Av. Jorge Chavez | PetHouse      | Servicio 24 horas  | pethouse@hotmail.com  | Cirugia general  | Lima     |

