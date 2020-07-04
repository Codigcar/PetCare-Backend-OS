Feature: La app permite crear suscripciones
  Scenario Outline: Crear suscripciones
    Given Se crea una suscripcion con name <name>, description <description>, duration <duration>, price <price>
    Then Verificar si se ha creado una suscripcion

    Examples:
      | name   | description | duration | price |
      | Basico | plan Basico | 5        | 29.99 |
      | Premium| plan Premium| 6        | 39.99 |