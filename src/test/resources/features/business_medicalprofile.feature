Feature: La app permite el registro de un productos
  --
  Scenario Outline: Registross de un productos
    Given El usuario registra su producto con su nombres <name>

    Examples:
      | name  |
      | baño |
