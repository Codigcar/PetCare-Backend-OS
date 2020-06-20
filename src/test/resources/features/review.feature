Feature: La app permite registrar un review
  prueba review
  Scenario Outline: Registro de un review
    Given El usuario registra su review con commentary <commentary>, qualification <qualification>
    Examples:
    | commentary  | qualification |
    | excelente | 3             |
