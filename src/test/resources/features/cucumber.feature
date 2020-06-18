Feature: RolTest

  Scenario Outline: As a rol I want to create a new rol
    And I sending post to be created with rol id <rol_id>, name <name>


    Examples:
    | rol_id  | name          |
    | 1       | customer      |



