Feature: La app permite el registro de un producto
  --
  Scenario Outline: Registro de un producto
    Given El usuario registra su producto con su nombre <name>

    Examples:
      | name  |
      | baño |

