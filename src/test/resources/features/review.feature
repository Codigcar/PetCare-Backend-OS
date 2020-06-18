Feature: La app permite realizar comentarios a una veterinaria
  Scenario Outline: Realizar comentarios a una veterinaria
    Given El usuario ingresa a la aplicacion web
    When El usuario realiza un comentario con commentary <commentary>, qualifiaction <qualification>
    Then Verificar que se ha realizado un comentario

    Examples:
       |   commentary   |  qualification    |
       | Buen servicio  | 5                 |