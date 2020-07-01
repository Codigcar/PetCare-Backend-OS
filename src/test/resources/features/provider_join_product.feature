Feature: La app permite seleccionar un tipo de producto
  prueba review
  Scenario Outline: Seleccionar un tipo de producto
    Given El usuario slecciona su tipo de producto con provider_id <provider_id> y product_id <product_id>

    Examples:
      | provider_id | product_id  |
      | 1           | 2           |