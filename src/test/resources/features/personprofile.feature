Feature: PersonProfileTest

  Scenario Outline: As a personprofile I want to create a new personprofile
    And I sending personprofile to be created with personprofile_id <id>,name <name>, password <password>,lastName <lastName>, document <document>, email <email>, phone<phone>, age<age>

    Examples:
      | id  | name   | password | lastName | document | email           | phone     | age
      | 1   | carlos | carlos123 |castilla | 76050041 | carlos@gmail.com| 946100691|55