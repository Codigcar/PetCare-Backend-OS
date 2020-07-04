Feature: La app permite el registro de un tipo de producto
  prueba review
  Scenario Outline: Registro de un tipo de producto
    Given El usuario registra su tipo de producto con su nombre <name>

    Examples:
      | name  |
      | baño |