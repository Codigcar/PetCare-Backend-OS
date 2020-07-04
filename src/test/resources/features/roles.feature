Feature: La app permite crear roles
  Scenario Outline: Crear roles
    Given Se crea un rol con name <name>
    Then Verificar si se ha creado un rol

    Examples:
      | name          |
      | veterinaria   |
      | dueño mascota |